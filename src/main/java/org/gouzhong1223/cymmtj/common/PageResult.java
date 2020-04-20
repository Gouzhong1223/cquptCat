/*
 *              Copyright 2020 By Gouzhong1223
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.gouzhong1223.cymmtj.common;

import lombok.Data;

import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 分页查询数据
 * @Date : create by QingSong in 2020-02-01 4:48 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : com.gouzhong1223.blog.common
 * @ProjectName : blog
 * @Version : 1.0.0
 */
@Data
public class PageResult<T> {
    private static final long serialVersionUID = 1L;

    private int pageNum;

    private int pageSize;

    private long total;

    private int totalPage;

    private List<T> list;

    public PageResult(int pageNum, int pageSize, long total, int totalPage, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = totalPage;
        this.list = list;
    }
}
