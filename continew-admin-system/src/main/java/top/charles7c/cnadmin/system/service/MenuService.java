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
import java.util.Set;

import top.charles7c.cnadmin.common.base.BaseService;
import top.charles7c.cnadmin.system.model.query.MenuQuery;
import top.charles7c.cnadmin.system.model.request.MenuRequest;
import top.charles7c.cnadmin.system.model.vo.MenuVO;

/**
 * 菜单业务接口
 *
 * @author Charles7c
 * @since 2023/2/15 20:30
 */
public interface MenuService extends BaseService<MenuVO, MenuVO, MenuQuery, MenuRequest> {

    /**
     * 根据用户 ID 查询
     *
     * @param userId
     *            用户 ID
     * @return 权限码集合
     */
    Set<String> listPermissionByUserId(Long userId);

    /**
     * 根据用户 ID 查询
     *
     * @param userId
     *            用户 ID
     * @return 菜单列表
     */
    List<MenuVO> listByUserId(Long userId);
}
