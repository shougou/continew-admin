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

package top.charles7c.cnadmin.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.redisson.codec.JsonJacksonCodec;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Redisson 配置
 *
 * @author Charles7c
 * @since 2022/12/28 23:17
 */
@Slf4j
@EnableCaching
@Configuration
@RequiredArgsConstructor
public class RedissonConfiguration {

    private final ObjectMapper objectMapper;

    /**
     * Redisson 自定义配置
     */
    @Bean
    public RedissonAutoConfigurationCustomizer redissonCustomizer() {
        // 解决序列化乱码问题
        return config -> config.setCodec(new JsonJacksonCodec(objectMapper));
    }
}
