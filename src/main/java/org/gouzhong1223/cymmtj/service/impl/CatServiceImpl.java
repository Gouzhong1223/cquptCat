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
import org.gouzhong1223.cymmtj.dto.rep.*;
import org.gouzhong1223.cymmtj.entity.*;
import org.gouzhong1223.cymmtj.mapper.*;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.service.MailService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.gouzhong1223.cymmtj.util.CheakEmail;
import org.gouzhong1223.cymmtj.util.RandomNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
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


    private static final String MAILSUBJECT = "重游猫咪图鉴";
    private static final Integer DEFAULTVIEWED = 0;
    private static final Integer DEFAULTAUDITED = 0;
    private final CatMapper catMapper;
    private final PraiseWechatUserMapper praiseWechatUserMapper;
    private final PicService picService;
    private final WechatUserMapper wechatUserMapper;
    private final MailService mailService;
    private final CatRefrrerMapper catRefrrerMapper;
    private final MailLogMapper mailLogMapper;
    private final CatCommentMapper catCommentMapper;
    private final CommentMapper commentMapper;
    private final AwesomeCatWechatUserMapper awesomeCatWechatUserMapper;
    private final CollectWechatUserMapper collectWechatUserMapper;
    private final AwesomeCommentWechatUserMapper awesomeCommentWechatUserMapper;
    private final CatPicMapper catPicMapper;
    private final PicMapper picMapper;
    private final CatRegionMapper catRegionMapper;
    private final RegionMapper regionMapper;

    @Value("${spring.mail.from}")
    private String from;

    public CatServiceImpl(CatMapper catMapper, PraiseWechatUserMapper praiseWechatUserMapper,
                          PicService picService, WechatUserMapper wechatUserMapper,
                          MailService mailService, CatRefrrerMapper catRefrrerMapper,
                          MailLogMapper mailLogMapper, CatCommentMapper catCommentMapper, CommentMapper commentMapper,
                          AwesomeCatWechatUserMapper awesomeCatWechatUserMapper, CollectWechatUserMapper collectWechatUserMapper,
                          AwesomeCommentWechatUserMapper awesomeCommentWechatUserMapper, CatPicMapper catPicMapper,
                          PicMapper picMapper, CatRegionMapper catRegionMapper, RegionMapper regionMapper) {
        this.catMapper = catMapper;
        this.praiseWechatUserMapper = praiseWechatUserMapper;
        this.picService = picService;
        this.wechatUserMapper = wechatUserMapper;
        this.mailService = mailService;
        this.catRefrrerMapper = catRefrrerMapper;
        this.mailLogMapper = mailLogMapper;
        this.catCommentMapper = catCommentMapper;
        this.commentMapper = commentMapper;
        this.awesomeCatWechatUserMapper = awesomeCatWechatUserMapper;
        this.collectWechatUserMapper = collectWechatUserMapper;
        this.awesomeCommentWechatUserMapper = awesomeCommentWechatUserMapper;
        this.catPicMapper = catPicMapper;
        this.picMapper = picMapper;
        this.catRegionMapper = catRegionMapper;
        this.regionMapper = regionMapper;
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
            return new ResponseDto(ResultCode.FAIL.getCode(), ResultMessage.EMAILCANNOTBEEMPTY.getMessaage());
        }

        if (!CheakEmail.isEmail(email)) {
            return new ResponseDto(ResultCode.FAIL.getCode(), ResultMessage.EMAILFORMATISINCORRECT.getMessaage());
        }
        // 为猫咪生成 ID
        Integer catId = RandomNumber.createNumber();

        // 根据 openID 查询出微信用户
        WechatUser wechatUser = wechatUserMapper.selectOneByOpenId(openId);

        String mailContent = "尊敬的" + wechatUser.getNickName() +
                ":\n您的推荐申请我们已经收到，请耐心等待管理员审核，审核结果我们将会以邮件形式发送给您";

        mailService.sendSimpleMail(email, MAILSUBJECT, mailContent);

        // 将邮件内容插入数据库
        MailLog mailLog = new MailLog(null, from, email, LocalDateTime.now(), MAILSUBJECT, mailContent);
        mailLogMapper.insertSelective(mailLog);

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
            Cat cat = new Cat(catId, name, color, sex, foreignTrade, character,
                    LocalDateTime.now(), type, DEFAULTVIEWED,
                    wechatUser.getNickName(), DEFAULTAUDITED, LocalDateTime.now(), 0, 0);
            cat.setId(catId);
            // 插入 Cat
            this.insertOrUpdateCat(cat);
            return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage());
        } else {
            return new ResponseDto(ResultCode.FAIL.getCode(), ResultMessage.UPLOADIMAGEFAILED.getMessaage());
        }
    }

    @Override
    public ResponseDto auditCat(Integer id, Integer auditStatus, String reasonForFailure) throws CymmtjException {

        Integer audited = 1;
        Integer visible;
        String mailContent = "";

        CatRefrrer catRefrrer = catRefrrerMapper.selectOneByCatId(id);
        Cat cat = catMapper.selectByPrimaryKey(id);

        // 先判断是否通过审核
        if (auditStatus == 1) {
            // 通过审核
            visible = 1;
            mailContent = "尊敬的" + cat.getReferrer() + ":\n您于" + cat.getCreateTime() +
                    "向我们推荐的猫咪已经通过管理员审核了，感谢您对社区做出的贡献。";
        } else {
            // 未通过审核
            visible = 0;
            mailContent = "尊敬的" + cat.getReferrer() + ":\n您于" + cat.getCreateTime() +
                    "向我们推荐的猫咪未通过管理员审核，未通过原因是：" + reasonForFailure + ",感谢您对社区做出的贡献。";

        }
        try {
            catMapper.updateVisibleById(visible, id);
            catMapper.updateAuditById(audited, id);

            mailService.sendSimpleMail(catRefrrer.getReferrerEmail(), MAILSUBJECT, mailContent);
            MailLog mailLog = new MailLog(null, from, catRefrrer.getReferrerEmail(), LocalDateTime.now(), MAILSUBJECT, mailContent);
            mailLogMapper.insertSelective(mailLog);
        } catch (Exception e) {
            throw new CymmtjException(ResultCode.FAIL.getCode(), ResultMessage.FAIL.getMessaage());
        }
        return ResponseDto.SUCCESS(null);
    }

    @Override
    public ResponseDto catDetail(String token, Integer catId) {

        Boolean awesome = false;
        Boolean collect = false;

        // 查询对应猫咪信息
        Cat cat = selectCatByid(catId);
        // 查询该猫咪对应的所有评论主键
        List<CatComment> catComments = catCommentMapper.selectAllByCatId(catId);
        ArrayList<Comment> comments = listAllCommentsByCatCommentInfo(catComments);
        // 判断用户是否已经赞过该帖
        AwesomeCatWechatUser awesomeCatWechatUser = awesomeCatWechatUserMapper.selectOneByCatIdAndToken(catId, token);
        if (awesomeCatWechatUser.getToken() != null) {
            awesome = true;
        }
        // 判断用户是否已经收藏
        CollectWechatUser collectWechatUser = collectWechatUserMapper.selectOneByCatIdAndToken(catId, token);
        if (collectWechatUser.getToken() != null) {
            collect = true;
        }
        List<AwesomeCommentWechatUser> awesomeCommentWechatUsers = awesomeCommentWechatUserMapper.selectAllByToken(token);
        // 封装评论
        List<CommentRep> commentReps = CommentServiceImpl.dealCommentsWithToken(comments, awesomeCommentWechatUsers);

        List<CatRegion> catRegions = catRegionMapper.selectAllByCatId(catId);
        ArrayList<Region> regions = listAllRegionsByCatRegionsInfo(catRegions);

        List<CatPic> catPics = catPicMapper.selectAllByCatId(catId);
        ArrayList<Pic> pics = listAllPicsByCatPicInfo(catPics);

        CatRep catRep = new CatRep(cat.getId(), cat.getName(), cat.getColor(), cat.getSex(), cat.getForeignTrade(), cat.getCharacter(), cat.getUpdateTime(), cat.getType(),
                cat.getVisible(), cat.getReferrer(), cat.getAudit(), cat.getCreateTime(), cat.getAwesomeCount(), cat.getCollectCount(), pics, commentReps.size(),
                commentReps, awesome, collect, regions);

        return ResponseDto.SUCCESS(catRep);
    }

    /**
     * 根据catComments获取所有的评论
     *
     * @param catComments
     * @return
     */
    public ArrayList<Comment> listAllCommentsByCatCommentInfo(List<CatComment> catComments) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (CatComment catComment : catComments) {
            comments.add(commentMapper.selectByPrimaryKey(catComment.getCommentId()));
        }
        return comments;
    }

    /**
     * 根据 catpic 获取所有的图片
     *
     * @param catPics
     * @return
     */
    public ArrayList<Pic> listAllPicsByCatPicInfo(List<CatPic> catPics) {
        ArrayList<Pic> pics = new ArrayList<>();
        for (CatPic catPic : catPics) {
            pics.add(picMapper.selectByPrimaryKey(catPic.getPicId()));
        }
        return pics;
    }

    /**
     * 根据 catRegions 获取所有的 Regions
     *
     * @param catRegions
     * @return
     */
    public ArrayList<Region> listAllRegionsByCatRegionsInfo(List<CatRegion> catRegions) {
        ArrayList<Region> regions = new ArrayList<>();
        for (CatRegion catRegion : catRegions) {
            regions.add(regionMapper.selectByPrimaryKey(catRegion.getReginId()));
        }
        return regions;
    }

}
