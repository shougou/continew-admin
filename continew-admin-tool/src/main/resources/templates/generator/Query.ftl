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

import java.io.Serializable;
<#if hasLocalDateTime>
import java.time.LocalDateTime;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
<#if hasListQueryField>
import java.util.List;
</#if>

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.cnadmin.common.annotation.Query;
import top.charles7c.cnadmin.common.enums.QueryTypeEnum;

/**
 * ${businessName}查询条件
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@Schema(description = "${businessName}查询条件")
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;
<#if fieldConfigs??>
  <#list fieldConfigs as fieldConfig>
    <#if fieldConfig.showInQuery>

    /**
     * ${fieldConfig.comment}
     */
    @Schema(description = "${fieldConfig.comment}")
    @Query(type = QueryTypeEnum.${fieldConfig.queryType})
    <#if fieldConfig.queryType == 'IN' || fieldConfig.queryType == 'NOT_IN' || fieldConfig.queryType == 'BETWEEN'>
    private List<${fieldConfig.fieldType}> ${fieldConfig.fieldName};
    <#else>
    private ${fieldConfig.fieldType} ${fieldConfig.fieldName};
    </#if>
    </#if>
  </#list>
</#if>
}