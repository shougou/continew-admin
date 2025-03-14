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

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 修改密码信息
 *
 * @author Charles7c
 * @since 2023/1/9 23:28
 */
@Data
@Schema(description = "修改密码信息")
public class UpdatePasswordRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前密码（加密后）
     */
    @Schema(description = "当前密码（加密后）",
        example = "E7c72TH+LDxKTwavjM99W1MdI9Lljh79aPKiv3XB9MXcplhm7qJ1BJCj28yaflbdVbfc366klMtjLIWQGqb0qw==")
    @NotBlank(message = "当前密码不能为空")
    private String oldPassword;

    /**
     * 新密码（加密后）
     */
    @Schema(description = "新密码（加密后）",
        example = "Gzc78825P5baH190lRuZFb9KJxRt/psN2jiyOMPoc5WRcCvneCwqDm3Q33BZY56EzyyVy7vQu7jQwYTK4j1+5w==")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
