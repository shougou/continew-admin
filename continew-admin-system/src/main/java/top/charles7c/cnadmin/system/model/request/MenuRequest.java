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

package top.charles7c.cnadmin.system.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.cnadmin.common.base.BaseRequest;
import top.charles7c.cnadmin.common.constant.RegexConsts;
import top.charles7c.cnadmin.common.enums.DisEnableStatusEnum;
import top.charles7c.cnadmin.common.enums.MenuTypeEnum;

/**
 * 创建或修改菜单信息
 *
 * @author Charles7c
 * @since 2023/2/15 20:21
 */
@Data
@Schema(description = "创建或修改菜单信息")
public class MenuRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单类型（1目录 2菜单 3按钮）
     */
    @Schema(description = "菜单类型（1目录 2菜单 3按钮）", type = "Integer", allowableValues = {"1", "2", "3"}, example = "2")
    @NotNull(message = "菜单类型非法")
    private MenuTypeEnum type;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标", example = "user")
    private String icon;

    /**
     * 菜单标题
     */
    @Schema(description = "菜单标题", example = "用户管理")
    @NotBlank(message = "菜单标题不能为空")
    @Pattern(regexp = RegexConsts.GENERAL_NAME, message = "菜单标题长度为 1 到 20 位，可以包含中文、字母、数字、下划线，短横线")
    private String title;

    /**
     * 菜单排序
     */
    @Schema(description = "菜单排序", example = "1")
    @NotNull(message = "菜单排序不能为空")
    private Integer sort;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识", example = "system:user:list")
    private String permission;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址", example = "/system/user")
    private String path;

    /**
     * 组件名称
     */
    @Schema(description = "组件名称", example = "User")
    private String name;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径", example = "/system/user/index")
    private String component;

    /**
     * 是否外链
     */
    @Schema(description = "是否外链", example = "false")
    private Boolean isExternal;

    /**
     * 是否缓存
     */
    @Schema(description = "是否缓存", example = "false")
    private Boolean isCache;

    /**
     * 是否隐藏
     */
    @Schema(description = "是否隐藏", example = "false")
    private Boolean isHidden;

    /**
     * 上级菜单 ID
     */
    @Schema(description = "上级菜单 ID", example = "1000")
    private Long parentId;

    /**
     * 状态（1启用 2禁用）
     */
    @Schema(description = "状态（1启用 2禁用）", type = "Integer", allowableValues = {"1", "2"}, example = "1")
    private DisEnableStatusEnum status;
}
