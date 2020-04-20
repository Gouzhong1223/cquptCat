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

import org.gouzhong1223.cymmtj.dto.rep.PopularCat;

import org.apache.ibatis.annotations.Param;
import org.gouzhong1223.cymmtj.dto.rep.CatResponse;
import org.gouzhong1223.cymmtj.pojo.Cat;

import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-20 6:11 下午
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

    int insertList(@Param("list") List<Cat> list);

    List<Cat> selectAllCats();

    List<CatResponse> selectIdAndName();

    /**
     * 获取前四个最受欢迎的 Cat
     *
     * @return
     */
    List<PopularCat> selectIdAndNameAndCommontOrderByPraiseDesc();


    PopularCat selectIdAndNameAndCommontByPrimaryKey(@Param("id") Integer id);
}