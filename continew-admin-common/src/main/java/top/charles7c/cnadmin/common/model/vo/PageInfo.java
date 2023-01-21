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

package top.charles7c.cnadmin.common.model.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

import io.swagger.v3.oas.annotations.media.Schema;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;

/**
 * 分页信息
 *
 * @param <V>
 *            列表数据类型
 * @author Charles7c
 * @since 2023/1/14 23:40
 */
@Data
@Accessors(chain = true)
@Schema(description = "分页信息")
public class PageInfo<V> {

    /**
     * 列表数据
     */
    @Schema(description = "列表数据")
    private List<V> list;

    /**
     * 总记录数
     */
    @Schema(description = "总记录数")
    private long total;

    /**
     * 基于 MyBatis Plus 分页数据构建分页信息，并将源数据转换为指定类型数据
     *
     * @param page
     *            MyBatis Plus 分页数据
     * @param targetClass
     *            目标类型 Class 对象
     * @param <T>
     *            源列表数据类型
     * @param <V>
     *            目标列表数据类型
     * @return 分页信息
     */
    public static <T, V> PageInfo<V> build(IPage<T> page, Class<V> targetClass) {
        if (page == null) {
            return null;
        }
        PageInfo<V> pageInfo = new PageInfo<>();
        pageInfo.setList(BeanUtil.copyToList(page.getRecords(), targetClass));
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    /**
     * 基于 MyBatis Plus 分页数据构建分页信息
     *
     * @param page
     *            MyBatis Plus 分页数据
     * @param <V>
     *            列表数据类型
     * @return 分页信息
     */
    public static <V> PageInfo<V> build(IPage<V> page) {
        if (page == null) {
            return null;
        }
        PageInfo<V> pageInfo = new PageInfo<>();
        pageInfo.setList(page.getRecords());
        pageInfo.setTotal(pageInfo.getTotal());
        return pageInfo;
    }

    /**
     * 基于列表数据构建分页信息
     *
     * @param page
     *            页码
     * @param size
     *            每页记录数
     * @param list
     *            列表数据
     * @param <V>
     *            列表数据类型
     * @return 分页信息
     */
    public static <V> PageInfo<V> build(int page, int size, List<V> list) {
        PageInfo<V> pageInfo = new PageInfo<>();
        if (CollUtil.isEmpty(list)) {
            return pageInfo;
        }

        pageInfo.setTotal(list.size());
        // 对列表数据进行分页
        int fromIndex = (page - 1) * size;
        int toIndex = page * size + size;
        if (fromIndex > list.size()) {
            pageInfo.setList(new ArrayList<>());
        } else if (toIndex >= list.size()) {
            pageInfo.setList(list.subList(fromIndex, list.size()));
        } else {
            pageInfo.setList(list.subList(fromIndex, toIndex));
        }
        return pageInfo;
    }
}
