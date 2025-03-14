/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.charles7c.cnadmin.system.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import top.charles7c.cnadmin.common.base.BaseServiceImpl;
import top.charles7c.cnadmin.common.config.properties.LocalStorageProperties;
import top.charles7c.cnadmin.common.constant.CacheConsts;
import top.charles7c.cnadmin.common.constant.FileConsts;
import top.charles7c.cnadmin.common.constant.StringConsts;
import top.charles7c.cnadmin.common.constant.SysConsts;
import top.charles7c.cnadmin.common.enums.DataTypeEnum;
import top.charles7c.cnadmin.common.enums.DisEnableStatusEnum;
import top.charles7c.cnadmin.common.service.CommonUserService;
import top.charles7c.cnadmin.common.util.ExceptionUtils;
import top.charles7c.cnadmin.common.util.FileUtils;
import top.charles7c.cnadmin.common.util.SecureUtils;
import top.charles7c.cnadmin.common.util.helper.LoginHelper;
import top.charles7c.cnadmin.common.util.validate.CheckUtils;
import top.charles7c.cnadmin.system.mapper.UserMapper;
import top.charles7c.cnadmin.system.model.entity.UserDO;
import top.charles7c.cnadmin.system.model.query.UserQuery;
import top.charles7c.cnadmin.system.model.request.UpdateBasicInfoRequest;
import top.charles7c.cnadmin.system.model.request.UpdateUserRoleRequest;
import top.charles7c.cnadmin.system.model.request.UserRequest;
import top.charles7c.cnadmin.system.model.vo.UserDetailVO;
import top.charles7c.cnadmin.system.model.vo.UserVO;
import top.charles7c.cnadmin.system.service.DeptService;
import top.charles7c.cnadmin.system.service.RoleService;
import top.charles7c.cnadmin.system.service.UserRoleService;
import top.charles7c.cnadmin.system.service.UserService;

/**
 * 用户业务实现
 *
 * @author Charles7c
 * @since 2022/12/21 21:49
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheConsts.USER_KEY_PREFIX)
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserDO, UserVO, UserDetailVO, UserQuery, UserRequest>
    implements UserService, CommonUserService {

    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final LocalStorageProperties localStorageProperties;
    @Resource
    private DeptService deptService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(UserRequest request) {
        String username = request.getUsername();
        CheckUtils.throwIf(this.checkNameExists(username, null), "新增失败，[{}] 已存在", username);
        String email = request.getEmail();
        CheckUtils.throwIf(StrUtil.isNotBlank(email) && this.checkEmailExists(email, null), "新增失败，[{}] 已存在", email);
        String phone = request.getPhone();
        CheckUtils.throwIf(StrUtil.isNotBlank(phone) && this.checkPhoneExists(phone, null), "新增失败，[{}] 已存在", phone);

        // 新增信息
        request.setStatus(DisEnableStatusEnum.ENABLE);
        Long userId = super.add(request);
        baseMapper.lambdaUpdate()
            .set(UserDO::getPassword, SecureUtils.md5Salt(SysConsts.DEFAULT_PASSWORD, userId.toString()))
            .set(UserDO::getPwdResetTime, LocalDateTime.now()).eq(UserDO::getId, userId).update();
        // 保存用户和角色关联
        userRoleService.save(request.getRoleIds(), userId);
        return userId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserRequest request, Long id) {
        String username = request.getUsername();
        CheckUtils.throwIf(this.checkNameExists(username, id), "修改失败，[{}] 已存在", username);
        String email = request.getEmail();
        CheckUtils.throwIf(StrUtil.isNotBlank(email) && this.checkEmailExists(email, id), "修改失败，[{}] 已存在", email);
        String phone = request.getPhone();
        CheckUtils.throwIf(StrUtil.isNotBlank(phone) && this.checkPhoneExists(phone, id), "修改失败，[{}] 已存在", phone);
        DisEnableStatusEnum newStatus = request.getStatus();
        CheckUtils.throwIf(
            DisEnableStatusEnum.DISABLE.equals(newStatus) && ObjectUtil.equal(id, LoginHelper.getUserId()),
            "不允许禁用当前用户");
        UserDO oldUser = super.getById(id);
        if (DataTypeEnum.SYSTEM.equals(oldUser.getType())) {
            CheckUtils.throwIfEqual(DisEnableStatusEnum.DISABLE, newStatus, "[{}] 是系统内置用户，不允许禁用",
                oldUser.getNickname());
            Collection<Long> disjunctionRoleIds =
                CollUtil.disjunction(request.getRoleIds(), userRoleService.listRoleIdByUserId(id));
            CheckUtils.throwIfNotEmpty(disjunctionRoleIds, "[{}] 是系统内置用户，不允许变更所属角色", oldUser.getNickname());
        }

        // 更新信息
        super.update(request, id);
        // 保存用户和角色关联
        userRoleService.save(request.getRoleIds(), id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        CheckUtils.throwIf(CollUtil.contains(ids, LoginHelper.getUserId()), "不允许删除当前用户");
        List<UserDO> list =
            baseMapper.lambdaQuery().select(UserDO::getNickname, UserDO::getType).in(UserDO::getId, ids).list();
        Optional<UserDO> isSystemData = list.stream().filter(u -> DataTypeEnum.SYSTEM.equals(u.getType())).findFirst();
        CheckUtils.throwIf(isSystemData::isPresent, "所选用户 [{}] 是系统内置用户，不允许删除",
            isSystemData.orElseGet(UserDO::new).getNickname());

        // 删除用户和角色关联
        userRoleService.deleteByUserIds(ids);
        // 删除用户
        super.delete(ids);
    }

    @Override
    public void fillDetail(Object detailObj) {
        super.fillDetail(detailObj);
        if (detailObj instanceof UserDetailVO) {
            UserDetailVO detailVO = (UserDetailVO)detailObj;
            detailVO.setDeptName(ExceptionUtils.exToNull(() -> deptService.get(detailVO.getDeptId()).getName()));
            List<Long> roleIdList = userRoleService.listRoleIdByUserId(detailVO.getId());
            detailVO.setRoleIds(roleIdList);
            detailVO.setRoleNames(String.join(StringConsts.CHINESE_COMMA, roleService.listNameByIds(roleIdList)));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadAvatar(MultipartFile avatarFile, Long id) {
        Long avatarMaxSizeInMb = localStorageProperties.getAvatarMaxSizeInMb();
        CheckUtils.throwIf(avatarFile.getSize() > avatarMaxSizeInMb * 1024 * 1024, "请上传小于 {}MB 的图片", avatarMaxSizeInMb);
        String avatarImageType = FileNameUtil.extName(avatarFile.getOriginalFilename());
        String[] avatarSupportImgTypes = FileConsts.AVATAR_SUPPORTED_IMG_TYPES;
        CheckUtils.throwIf(!StrUtil.equalsAnyIgnoreCase(avatarImageType, avatarSupportImgTypes), "头像仅支持 {} 格式的图片",
            String.join(StringConsts.CHINESE_COMMA, avatarSupportImgTypes));

        UserDO user = super.getById(id);
        // 上传新头像
        String avatarPath = localStorageProperties.getPath().getAvatar();
        File newAvatarFile = FileUtils.upload(avatarFile, avatarPath, false);
        CheckUtils.throwIfNull(newAvatarFile, "上传头像失败");
        assert null != newAvatarFile;

        // 更新用户头像
        String newAvatar = newAvatarFile.getName();
        baseMapper.lambdaUpdate().set(UserDO::getAvatar, newAvatar).eq(UserDO::getId, id).update();

        // 删除原头像
        String oldAvatar = user.getAvatar();
        if (StrUtil.isNotBlank(oldAvatar)) {
            FileUtil.del(avatarPath + oldAvatar);
        }
        return newAvatar;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBasicInfo(UpdateBasicInfoRequest request, Long id) {
        super.getById(id);
        baseMapper.lambdaUpdate().set(UserDO::getNickname, request.getNickname())
            .set(UserDO::getGender, request.getGender()).eq(UserDO::getId, id).update();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(String oldPassword, String newPassword, Long id) {
        CheckUtils.throwIfEqual(newPassword, oldPassword, "新密码不能与当前密码相同");
        UserDO user = super.getById(id);
        CheckUtils.throwIfNotEqual(SecureUtils.md5Salt(oldPassword, id.toString()), user.getPassword(), "当前密码错误");

        // 更新密码和密码重置时间
        LocalDateTime now = LocalDateTime.now();
        baseMapper.lambdaUpdate().set(UserDO::getPassword, SecureUtils.md5Salt(newPassword, id.toString()))
            .set(UserDO::getPwdResetTime, now).eq(UserDO::getId, id).update();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String newEmail, String currentPassword, Long id) {
        UserDO user = super.getById(id);
        CheckUtils.throwIfNotEqual(SecureUtils.md5Salt(currentPassword, id.toString()), user.getPassword(), "当前密码错误");
        Long count = baseMapper.lambdaQuery().eq(UserDO::getEmail, newEmail).count();
        CheckUtils.throwIf(count > 0, "邮箱已绑定其他账号，请更换其他邮箱");
        CheckUtils.throwIfEqual(newEmail, user.getEmail(), "新邮箱不能与当前邮箱相同");

        // 更新邮箱
        baseMapper.lambdaUpdate().set(UserDO::getEmail, newEmail).eq(UserDO::getId, id).update();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(Long id) {
        UserDO user = super.getById(id);
        user.setPassword(SecureUtils.md5Salt(SysConsts.DEFAULT_PASSWORD, id.toString()));
        user.setPwdResetTime(LocalDateTime.now());
        baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(UpdateUserRoleRequest request, Long id) {
        super.getById(id);
        // 保存用户和角色关联
        userRoleService.save(request.getRoleIds(), id);
    }

    @Override
    public UserDO getByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    @Override
    public Long countByDeptIds(List<Long> deptIds) {
        return baseMapper.lambdaQuery().in(UserDO::getDeptId, deptIds).count();
    }

    @Override
    @Cacheable(key = "#id")
    public String getNicknameById(Long id) {
        return baseMapper.selectNicknameById(id);
    }

    /**
     * 检查名称是否存在
     *
     * @param name
     *            名称
     * @param id
     *            ID
     * @return 是否存在
     */
    private boolean checkNameExists(String name, Long id) {
        return baseMapper.lambdaQuery().eq(UserDO::getUsername, name).ne(null != id, UserDO::getId, id).exists();
    }

    /**
     * 检查邮箱是否存在
     *
     * @param email
     *            邮箱
     * @param id
     *            ID
     * @return 是否存在
     */
    private boolean checkEmailExists(String email, Long id) {
        return baseMapper.lambdaQuery().eq(UserDO::getEmail, email).ne(null != id, UserDO::getId, id).exists();
    }

    /**
     * 检查手机号码是否存在
     *
     * @param phone
     *            手机号码
     * @param id
     *            ID
     * @return 是否存在
     */
    private boolean checkPhoneExists(String phone, Long id) {
        return baseMapper.lambdaQuery().eq(UserDO::getPhone, phone).ne(null != id, UserDO::getId, id).exists();
    }
}
