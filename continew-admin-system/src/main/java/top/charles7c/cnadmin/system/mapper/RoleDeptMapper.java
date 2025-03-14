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

package top.charles7c.cnadmin.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import top.charles7c.cnadmin.common.base.BaseMapper;
import top.charles7c.cnadmin.system.model.entity.RoleDeptDO;

/**
 * 角色和部门 Mapper
 *
 * @author Charles7c
 * @since 2023/2/18 21:57
 */
public interface RoleDeptMapper extends BaseMapper<RoleDeptDO> {

    /**
     * 根据角色 ID 查询
     *
     * @param roleId
     *            角色 ID
     * @return 部门 ID 列表
     */
    @Select("SELECT `dept_id` FROM `sys_role_dept` WHERE `role_id` = #{roleId}")
    List<Long> selectDeptIdByRoleId(@Param("roleId") Long roleId);
}
