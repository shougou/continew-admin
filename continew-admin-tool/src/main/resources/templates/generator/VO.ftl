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

package ${packageName}.${subPackageName};

<#if hasLocalDateTime>
import java.time.LocalDateTime;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.cnadmin.common.base.BaseVO;

/**
 * ${businessName}信息
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@Schema(description = "${businessName}信息")
public class ${className} extends BaseVO {

    private static final long serialVersionUID = 1L;
<#if fieldConfigs??>
  <#list fieldConfigs as fieldConfig>
    <#if fieldConfig.showInList>

    /**
     * ${fieldConfig.comment}
     */
    @Schema(description = "${fieldConfig.comment}")
    private ${fieldConfig.fieldType} ${fieldConfig.fieldName};
    </#if>
  </#list>
</#if>
}