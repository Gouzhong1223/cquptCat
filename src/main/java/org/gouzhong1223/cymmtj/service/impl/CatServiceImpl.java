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

package org.gouzhong1223.cymmtj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.gouzhong1223.cymmtj.common.PageResult;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.CatResponse;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.dto.rep.ResultCat;
import org.gouzhong1223.cymmtj.mapper.CatMapper;
import org.gouzhong1223.cymmtj.mapper.PraiseWechatUserMapper;
import org.gouzhong1223.cymmtj.mapper.WechatUserMapper;
import org.gouzhong1223.cymmtj.pojo.Cat;
import org.gouzhong1223.cymmtj.pojo.Pic;
import org.gouzhong1223.cymmtj.pojo.PraiseWechatUser;
import org.gouzhong1223.cymmtj.pojo.WechatUser;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.gouzhong1223.cymmtj.util.RandomNumber;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : {@link CatService} 实现类，处理 Cat 增删改查业务
 * @Date : create by QingSong in 2020-04-18 8:05 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service.impl
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Service
@Transactional
public class CatServiceImpl implements CatService {


    private final CatMapper catMapper;
    private final PraiseWechatUserMapper praiseWechatUserMapper;
    private final PicService picService;
    private final WechatUserMapper wechatUserMapper;

    public CatServiceImpl(CatMapper catMapper, PraiseWechatUserMapper praiseWechatUserMapper, PicService picService, WechatUserMapper wechatUserMapper) {
        this.catMapper = catMapper;
        this.praiseWechatUserMapper = praiseWechatUserMapper;
        this.picService = picService;
        this.wechatUserMapper = wechatUserMapper;
    }

    @Override
    public void insertOrUpdateCat(Cat cat) {
        cat.setUpdateTime(LocalDateTime.now());
        int i = catMapper.insertSelective(cat);
    }

    @Override
    public PageResult<ResultCat> pagingListCat(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ResultCat> resultCats = catMapper.selectIdAndNameAndCommont();
        PageInfo<ResultCat> resultCatPageInfo = new PageInfo<>(resultCats);
        return new PageResult<>(resultCatPageInfo.getPageNum(), resultCatPageInfo.getPageSize(),
                resultCatPageInfo.getTotal(), resultCatPageInfo.getPages(), resultCatPageInfo.getList());
    }

    @Override
    public PageResult<CatResponse> selectCatIdAndName(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<ResultCat> selectPopularCats() {
        List<ResultCat> resultCats = catMapper.selectIdAndNameAndCommontOrderByPraiseDesc();
        return resultCats;
    }

    @Override
    public Cat selectCatByid(Integer id) {
        return catMapper.selectByPrimaryKey(id);
    }

    @Override
    public void thumbUp(Integer id, WechatUser wechatUser) {
        catMapper.thumbUp();
        PraiseWechatUser praiseWechatUser = new PraiseWechatUser(wechatUser.getOpenId(), id);
        praiseWechatUserMapper.insertSelective(praiseWechatUser);
    }

    @Override
    public void cancelPraise(Integer id, WechatUser wechatUser) {
        catMapper.cancelPraise();
        int i = praiseWechatUserMapper.deleteByCatIdAndOpenId(id, wechatUser.getOpenId());
    }

    @Override
    public ResponseDto contributeCat(JSONObject jsonObject, String openId) {
        JSONArray jsonFiles = jsonObject.getJSONArray("files");
        MultipartFile[] multipartFiles = (MultipartFile[]) jsonFiles.toArray();
        List<MultipartFile> files = Arrays.asList(multipartFiles);
        String name = jsonObject.getString("name");
        String color = jsonObject.getString("color");
        String sex = jsonObject.getString("sex");
        String foreignTrade = jsonObject.getString("foreignTrade");
        String character = jsonObject.getString("character");
        String type = jsonObject.getString("type");

        Integer catId = RandomNumber.createNumber();
        List<Pic> pics = picService.insertPics(files, catId);

        WechatUser wechatUser = wechatUserMapper.selectOneByOpenId(openId);

        if (CollectionUtils.isNotEmpty(pics)) {
            Cat cat = new Cat(catId, name, color, sex, foreignTrade, character, LocalDateTime.now(), type, 0, wechatUser.getNickName());
            cat.setId(catId);
            this.insertOrUpdateCat(cat);
            return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage());
        }
        return null;
    }

}
