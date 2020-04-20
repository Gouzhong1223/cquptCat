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

package org.gouzhong1223.cymmtj.service;

import org.gouzhong1223.cymmtj.common.PageResult;
import org.gouzhong1223.cymmtj.dto.rep.CatResponse;
import org.gouzhong1223.cymmtj.dto.rep.PopularCat;
import org.gouzhong1223.cymmtj.pojo.Cat;

import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : CatService
 * @Date : create by QingSong in 2020-04-18 8:03 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CatService {

    /**
     * 新增或者更新 Cat info
     *
     * @param cat
     */
    void insertOrUpdateCat(Cat cat);

    /**
     * 分页查询 CatInfo
     *
     * @return {@link PageResult<Cat>} 分页查询结果
     */
    PageResult<Cat> pagingListCat(Integer pageNum, Integer pageSize);

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult<CatResponse> selectCatIdAndName(Integer pageNum, Integer pageSize);

    /**
     * 获取前四个最受欢迎的猫咪
     *
     * @return
     */
    List<PopularCat> selectPopularCats();

    /**
     * 根据 id 查询 Cat 详情
     *
     * @param id {@link Cat#getId()} id
     * @return {@link Cat} 根据 id 查询到的 Cat
     */
    Cat selectCatByid(Integer id);
}
