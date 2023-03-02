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

package top.charles7c.cnadmin.auth.service.impl;

import java.util.Set;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import cn.hutool.core.collection.CollUtil;

import top.charles7c.cnadmin.auth.service.PermissionService;
import top.charles7c.cnadmin.common.consts.Constants;
import top.charles7c.cnadmin.system.service.MenuService;
import top.charles7c.cnadmin.system.service.RoleService;

/**
 * 权限业务实现类
 *
 * @author Charles7c
 * @since 2023/3/2 20:40
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final MenuService menuService;
    private final RoleService roleService;

    @Override
    public Set<String> listPermissionsByUserId(Long userId) {
        Set<String> roleCodeSet = this.listRoleCodesByUserId(userId);
        // 超级管理员赋予全部权限
        if (roleCodeSet.contains(Constants.SUPER_ADMIN)) {
            return CollUtil.newHashSet(Constants.ALL_PERMISSION);
        }
        return menuService.listPermissionsByUserId(userId);
    }

    @Override
    public Set<String> listRoleCodesByUserId(Long userId) {
        return roleService.listRoleCodesByUserId(userId);
    }
}
