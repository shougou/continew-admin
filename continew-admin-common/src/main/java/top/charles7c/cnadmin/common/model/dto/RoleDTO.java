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

package top.charles7c.cnadmin.common.model.dto;

import java.io.Serializable;

import lombok.Data;

import top.charles7c.cnadmin.common.enums.DataScopeEnum;

/**
 * 角色信息
 *
 * @author Charles7c
 * @since 2023/3/7 22:08
 */
@Data
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 数据权限（1：全部数据权限，2：本部门及以下数据权限，3：本部门数据权限，4：仅本人数据权限，5：自定义数据权限）
     */
    private DataScopeEnum dataScope;
}
