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

package top.charles7c.cnadmin.system.service;

import java.util.List;

/**
 * 用户和角色业务接口
 *
 * @author Charles7c
 * @since 2023/2/20 21:30
 */
public interface UserRoleService {

    /**
     * 保存
     *
     * @param roleIds
     *            角色 ID 列表
     * @param userId
     *            用户 ID
     * @return true：成功，false：无变更/失败
     */
    boolean save(List<Long> roleIds, Long userId);

    /**
     * 根据用户 ID 删除
     *
     * @param userIds
     *            用户 ID 列表
     */
    void deleteByUserIds(List<Long> userIds);

    /**
     * 根据用户 ID 查询
     *
     * @param userId
     *            用户 ID
     * @return 角色 ID 列表
     */
    List<Long> listRoleIdByUserId(Long userId);

    /**
     * 根据角色 ID 列表查询
     *
     * @param roleIds
     *            角色 ID 列表
     * @return 总记录数
     */
    Long countByRoleIds(List<Long> roleIds);
}