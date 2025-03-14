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

package top.charles7c.cnadmin.common.config.properties;

import lombok.Data;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.spring.SpringUtil;

/**
 * 项目配置属性
 *
 * @author Charles7c
 * @since 2022/12/11 19:26
 */
@Data
@Component
@ConfigurationProperties(prefix = "continew-admin")
public class ContiNewAdminProperties {

    /**
     * 名称
     */
    private String name;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 版本
     */
    private String version;

    /**
     * 描述
     */
    private String description;

    /**
     * URL
     */
    private String url;

    /**
     * 基本包
     */
    private String basePackage;

    /**
     * 作者信息
     */
    @NestedConfigurationProperty
    private Contact author;

    /**
     * 许可协议信息
     */
    @NestedConfigurationProperty
    private License license;

    /**
     * 是否本地解析 IP 归属地
     */
    public static final boolean IP_ADDR_LOCAL_PARSE_ENABLED;

    static {
        IP_ADDR_LOCAL_PARSE_ENABLED = Convert.toBool(SpringUtil.getProperty("continew-admin.ipAddrLocalParseEnabled"));
    }
}
