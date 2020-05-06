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
import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.common.PageResult;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.CatResponse;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.dto.rep.ResultCat;
import org.gouzhong1223.cymmtj.mapper.*;
import org.gouzhong1223.cymmtj.pojo.*;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.service.MailService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.gouzhong1223.cymmtj.util.CheakEmail;
import org.gouzhong1223.cymmtj.util.RandomNumber;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
    private final MailService mailService;
    private final CatRefrrerMapper catRefrrerMapper;

    public CatServiceImpl(CatMapper catMapper, PraiseWechatUserMapper praiseWechatUserMapper,
                          PicService picService, WechatUserMapper wechatUserMapper,
                          MailService mailService, CatRefrrerMapper catRefrrerMapper) {
        this.catMapper = catMapper;
        this.praiseWechatUserMapper = praiseWechatUserMapper;
        this.picService = picService;
        this.wechatUserMapper = wechatUserMapper;
        this.mailService = mailService;
        this.catRefrrerMapper = catRefrrerMapper;
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
        PraiseWechatUser praiseWechatUser = new PraiseWechatUser(wechatUser.getOpenId(), id, LocalDateTime.now());
        praiseWechatUserMapper.insertSelective(praiseWechatUser);
    }

    @Override
    public void cancelPraise(Integer id, WechatUser wechatUser) {
        catMapper.cancelPraise();
        int i = praiseWechatUserMapper.deleteByCatIdAndOpenId(id, wechatUser.getOpenId());
    }

    @Override
    public ResponseDto contributeCat(JSONObject jsonObject, String openId) throws CymmtjException {

        // 获取上传的文件数组
        JSONArray jsonFiles = jsonObject.getJSONArray("files");
        List<MultipartFile> files = jsonFiles.toJavaList(MultipartFile.class);

        // 获取邮箱地址
        String email = jsonObject.getString("email");
        if (email == null || "".equals(email)) {
            return new ResponseDto(ResultCode.FAIL.getCode(), "邮箱不能为空！");
        }

        if (!CheakEmail.isEmail(email)) {
            return new ResponseDto(ResultCode.FAIL.getCode(), "邮箱格式不正确！");
        }
        // 为猫咪生成 ID
        Integer catId = RandomNumber.createNumber();

        // 根据 openID 查询出微信用户
        WechatUser wechatUser = wechatUserMapper.selectOneByOpenId(openId);

        mailService.sendSimpleMail(email, "重游猫咪图鉴", "尊敬的" + wechatUser.getNickName() +
                ",您的推荐申请我们已经收到，请耐心等待管理员审核，审核结果我们将会以邮件形式发送给您");

        CatRefrrer catRefrrer = new CatRefrrer(catId, email, openId);
        try {
            catRefrrerMapper.insertSelective(catRefrrer);
        } catch (Exception e) {
            throw new CymmtjException(ResultCode.FAIL.getCode(), "将邮箱插入数据库失败,请重试！");
        }


        // 获取猫咪名字
        String name = jsonObject.getString("name");
        // 获取猫咪颜色
        String color = jsonObject.getString("color");
        // 获取猫咪性别
        String sex = jsonObject.getString("sex");
        // 获取猫咪外貌
        String foreignTrade = jsonObject.getString("foreignTrade");
        // 获取猫咪性格
        String character = jsonObject.getString("character");
        // 获取猫咪分类
        String type = jsonObject.getString("type");


        // 开始插入图片
        List<Pic> pics = picService.insertPics(files, catId);


        if (CollectionUtils.isNotEmpty(pics)) {
            // 开始生成 Cat 对象
            Cat cat = new Cat(catId, name, color, sex, foreignTrade, character, LocalDateTime.now(), type, 0, wechatUser.getNickName(), 0, LocalDateTime.now());
            cat.setId(catId);
            // 插入 Cat
            this.insertOrUpdateCat(cat);
            return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage());
        } else {
            return new ResponseDto(ResultCode.FAIL.getCode(), "上传图片失败！");
        }
    }

    @Override
    public ResponseDto auditCat(Integer id, Integer auditStatus) {
        return null;
    }

}
