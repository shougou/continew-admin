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

package top.charles7c.cnadmin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import top.charles7c.cnadmin.common.base.BaseEnum;

/**
 * 数据权限枚举
 *
 * @author Charles7c
 * @since 2023/2/8 22:58
 */
@Getter
@RequiredArgsConstructor
public enum DataScopeEnum implements BaseEnum<Integer, String> {

    /** 全部数据权限 */
    ALL(1, "全部数据权限"),

    /** 本部门及以下数据权限 */
    DEPT_AND_CHILD(2, "本部门及以下数据权限"),

    /** 本部门数据权限 */
    DEPT(3, "本部门数据权限"),

    /** 仅本人数据权限 */
    SELF(4, "仅本人数据权限"),

    /** 自定数据权限 */
    CUSTOM(5, "自定数据权限"),;

    private final Integer value;
    private final String description;
}
