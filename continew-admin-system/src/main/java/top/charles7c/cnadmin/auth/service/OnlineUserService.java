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

package top.charles7c.cnadmin.auth.service;

import java.util.List;

import top.charles7c.cnadmin.auth.model.query.OnlineUserQuery;
import top.charles7c.cnadmin.auth.model.vo.OnlineUserVO;
import top.charles7c.cnadmin.common.model.dto.LoginUser;
import top.charles7c.cnadmin.common.model.query.PageQuery;
import top.charles7c.cnadmin.common.model.vo.PageDataVO;

/**
 * 在线用户业务接口
 *
 * @author Charles7c
 * @since 2023/3/25 22:48
 */
public interface OnlineUserService {

    /**
     * 分页查询列表
     *
     * @param query
     *            查询条件
     * @param pageQuery
     *            分页查询条件
     * @return 分页列表信息
     */
    PageDataVO<OnlineUserVO> page(OnlineUserQuery query, PageQuery pageQuery);

    /**
     * 查询列表
     *
     * @param query
     *            查询条件
     * @return 列表信息
     */
    List<LoginUser> list(OnlineUserQuery query);

    /**
     * 根据角色 ID 清除
     *
     * @param roleId
     *            角色 ID
     */
    void cleanByRoleId(Long roleId);
}
