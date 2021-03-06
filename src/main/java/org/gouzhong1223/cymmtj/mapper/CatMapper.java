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

package org.gouzhong1223.cymmtj.mapper;

import org.apache.ibatis.annotations.Param;
import org.gouzhong1223.cymmtj.dto.rep.CatResponse;
import org.gouzhong1223.cymmtj.dto.rep.ResultCat;
import org.gouzhong1223.cymmtj.entity.Cat;

import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 16:51
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cat record);

    int insertSelective(Cat record);

    Cat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cat record);

    int updateByPrimaryKey(Cat record);

    /**
     * 批量插入 Cat
     *
     * @param list Cat 集合
     * @return
     */
    int insertList(@Param("list") List<Cat> list);

    /**
     * 查询所有 Cat
     *
     * @return
     */
    List<Cat> selectAllCats();

    List<CatResponse> selectIdAndName();


    /**
     * 根据 id 查询 id，name，commont
     *
     * @param id
     * @return
     */
    ResultCat selectIdAndNameAndCommontByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询所有的 id ，name，commont
     *
     * @return
     */
    List<ResultCat> selectIdAndNameAndCommont();

    /**
     * 点赞数量+1
     *
     * @param id
     * @return
     */
    int thumbUp(@Param("id") Integer id);

    /**
     * 点赞数量-1
     *
     * @param id
     * @return
     */
    int unThumbUp(@Param("id") Integer id);

    /**
     * 根据猫咪主键更新审核状态
     *
     * @param updatedAudit 审核状态 1-已审核 0-未审核
     * @param id           猫咪 ID
     * @return
     */
    int updateAuditById(@Param("updatedAudit") Integer updatedAudit, @Param("id") Integer id);

    /**
     * 根据猫咪主键更新是否可见
     *
     * @param updatedVisible 可见状态 1-可见 0-不可见
     * @param id             猫咪主键
     * @return
     */
    int updateVisibleById(@Param("updatedVisible") Integer updatedVisible, @Param("id") Integer id);

    /**
     * 将 cat 收藏数量+1
     *
     * @param id catId
     * @return
     */
    int collect(@Param("id") Integer id);

    /**
     * 将 cat 收藏数量-1
     *
     * @param id catId
     * @return
     */
    int unCollect(@Param("id") Integer id);

    /**
     * 根据获赞数量查询前三个 Cat
     *
     * @return
     */
    List<Cat> selectPopularCats();

    /**
     * 根据获赞数量降序查询所有 Cats
     *
     * @return
     */
    List<Cat> selectAllOrderByAwesomeCount();

    /**
     * 根据收藏数量降序查询所有 Cats
     *
     * @return
     */
    List<Cat> selectAllOrderByCollectCount();

    /**
     * 根据创建时间降序查询所有 Cats
     *
     * @return
     */
    List<Cat> selectAllOrderByCreateTime();


}