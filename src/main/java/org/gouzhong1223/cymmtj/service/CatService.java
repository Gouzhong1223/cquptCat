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

import com.alibaba.fastjson.JSONObject;
import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.common.PageResult;
import org.gouzhong1223.cymmtj.dto.rep.CatIntroRep;
import org.gouzhong1223.cymmtj.dto.rep.CatResponse;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.entity.Cat;

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
     * @return {@link PageResult<CatIntroRep>} 分页查询结果
     */
    PageResult<CatIntroRep> pagingListCat(Integer pageNum, Integer pageSize);

    /**
     * @param pageNum  当前页码
     * @param pageSize 每一页最大数量
     * @return
     */
    PageResult<CatResponse> selectCatIdAndName(Integer pageNum, Integer pageSize);


    /**
     * 根据 id 查询 Cat 详情
     *
     * @param id {@link Cat#getId()} id
     * @return {@link Cat} 根据 id 查询到的 Cat
     */
    Cat selectCatByid(Integer id);

    /**
     * 用户给猫咪点赞
     *
     * @param catId 猫咪主键
     * @param token 微信用户 token
     */
    ResponseDto thumbUp(Integer catId, String token) throws CymmtjException;


    /**
     * 小程序用户推荐 Cat
     *
     * @param jsonObject
     * @param openId
     * @return
     */
    ResponseDto contributeCat(JSONObject jsonObject, String openId) throws CymmtjException;

    /**
     * 审核 Cat
     *
     * @param id          猫咪主键
     * @param auditStatus 前端发过来的审核状态 1->通过 0->不通过
     * @return
     */
    ResponseDto auditCat(Integer id, Integer auditStatus, String reasonForFailure) throws CymmtjException;

    /**
     * 获取猫咪详情
     *
     * @param token 微信用户 token
     * @param catId 猫咪 ID
     * @return
     */
    ResponseDto catDetail(String token, Integer catId);

    /**
     * 根据微信用户token 获取 cat 简介
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param token    微信用户 token
     * @return
     */
    ResponseDto listAllCatsByToken(Integer pageNum, Integer pageSize, String token);

    /**
     * 微信用户取消对 Cat 的点赞
     *
     * @param catId 猫咪主键
     * @param token 微信用户 token
     * @return
     */
    ResponseDto unThumbUp(Integer catId, String token) throws CymmtjException;

    /**
     * 获取首页结果集
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return
     */
    ResponseDto indexResult(Integer pageNum, Integer pageSize) throws CymmtjException;

    /**
     * 获取指定区域的 Cat
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param regionId 区域id
     * @return
     */
    ResponseDto listCatsByRegion(Integer pageNum, Integer pageSize, Integer regionId) throws CymmtjException;

    /**
     * 根据获赞数量查询所有 Cats
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return
     */
    ResponseDto listCatsOrderByAwesomeCount(Integer pageNum, Integer pageSize) throws CymmtjException;

    /**
     * 根据收藏数量查询所有 Cats
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return
     */
    ResponseDto listCatsOrderByCollectCount(Integer pageNum, Integer pageSize) throws CymmtjException;

    /**
     * 根据发表时间查询所有 Cats
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return
     */
    ResponseDto listCatsOrderByCreateTime(Integer pageNum, Integer pageSize) throws CymmtjException;
}
