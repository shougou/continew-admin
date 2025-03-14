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

package top.charles7c.cnadmin.auth.config.satoken;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Sa-Token 安全配置属性
 *
 * @author Lion Li（RuoYi-Vue-Plus）
 * @author Charles7c
 * @since 2022/12/19 22:14
 */
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    /**
     * 排除路径配置
     */
    private String[] excludes = new String[0];
}
