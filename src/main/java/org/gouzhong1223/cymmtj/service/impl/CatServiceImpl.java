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
import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.common.PageResult;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.*;
import org.gouzhong1223.cymmtj.entity.*;
import org.gouzhong1223.cymmtj.mapper.*;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.service.CommentService;
import org.gouzhong1223.cymmtj.service.MailService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.gouzhong1223.cymmtj.util.CheakEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    private final UserMapper userMapper;
    private final CommentService commentService;

    @Value("${spring.mail.from}")
    private String from;

    public CatServiceImpl(CatMapper catMapper, PicService picService, WechatUserMapper wechatUserMapper,
                          MailService mailService, CatRefrrerMapper catRefrrerMapper,
                          MailLogMapper mailLogMapper, CatCommentMapper catCommentMapper, CommentMapper commentMapper,
                          AwesomeCatWechatUserMapper awesomeCatWechatUserMapper, CollectWechatUserMapper collectWechatUserMapper,
                          AwesomeCommentWechatUserMapper awesomeCommentWechatUserMapper, CatPicMapper catPicMapper,
                          PicMapper picMapper, CatRegionMapper catRegionMapper, RegionMapper regionMapper,
                          UserMapper userMapper, CommentService commentService) {
        this.catMapper = catMapper;
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
        this.userMapper = userMapper;
        this.commentService = commentService;
    }

    @Override
    public void insertOrUpdateCat(Cat cat) {
        cat.setUpdateTime(LocalDateTime.now());
        catMapper.insertSelective(cat);
    }

    @Override
    public PageResult<CatIntroRep> pagingListCat(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Cat> cats = catMapper.selectAllCats();
        ArrayList<CatIntroRep> catIntroReps = generateCatIntroRepByCats(cats);
        PageInfo<CatIntroRep> resultCatPageInfo = new PageInfo<>(catIntroReps);
        return new PageResult<>(resultCatPageInfo.getPageNum(), resultCatPageInfo.getPageSize(),
                resultCatPageInfo.getTotal(), resultCatPageInfo.getPages(), resultCatPageInfo.getList());
    }

    @Override
    public PageResult<CatResponse> selectCatIdAndName(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public Cat selectCatByid(Integer id) {
        return catMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResponseDto thumbUp(Integer catId, String token) throws CymmtjException {
        catMapper.thumbUp(catId);
        try {
            awesomeCatWechatUserMapper.insertSelective(new AwesomeCatWechatUser(token, catId, LocalDateTime.now()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "点赞失败啦！");
        }
        return ResponseDto.SUCCESS();
    }


    @Override
    public ResponseDto contributeCat(JSONObject jsonObject, String token) {

        // 获取上传的文件数组
        JSONArray jsonFiles = jsonObject.getJSONArray("files");
        List<MultipartFile> files = jsonFiles.toJavaList(MultipartFile.class);

        // 获取邮箱地址
        String email = jsonObject.getString("email");
        if (email == null || "".equals(email)) {
            return new ResponseDto(ResultCode.FAIL.getCode(), ResultMessage.EMAILCANNOTBEEMPTY.getMessaage());
        }

        // 检查邮箱格式是否正确
        if (!CheakEmail.isEmail(email)) {
            return new ResponseDto(ResultCode.FAIL.getCode(), ResultMessage.EMAILFORMATISINCORRECT.getMessaage());
        }
        // 根据 token 查询出微信用户
        WechatUser wechatUser = wechatUserMapper.selectOneByToken(token);

        String mailContent = "尊敬的" + wechatUser.getNickName() +
                ":\n您的推荐申请我们已经收到，请耐心等待管理员审核，审核结果我们将会以邮件形式发送给您";
        try {

            // generateCatInfo
            Cat cat = generateSimpleCat(jsonObject);

            // set the cat referrer->wechatUser NickName
            cat.setReferrer(wechatUser.getNickName());

            catMapper.insertSelective(cat);

            // 生成推荐人和 Cat 的关联信息
            CatRefrrer catRefrrer = new CatRefrrer(cat.getId(), email, token);

            // 开始插入图片
            List<Pic> pics = picService.insertPics(files, cat.getId(), null);

            // 插入推荐人关联信息
            catRefrrerMapper.insertSelective(catRefrrer);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto(ResultCode.FAIL.getCode(), ResultMessage.UPLOADIMAGEFAILED.getMessaage());
        }

        // 发送邮件
        mailService.sendSimpleMail(email, MAILSUBJECT, mailContent);
        mailService.sendSimpleMail("1162864960@qq.com", MAILSUBJECT, "微信昵称为：" + wechatUser.getNickName() + "的用户刚刚投稿啦，快去审核吧！");

        // 将邮件内容插入数据库
        MailLog mailLog = new MailLog(null, from, email, LocalDateTime.now(), MAILSUBJECT, mailContent);
        mailLogMapper.insertSelective(mailLog);

        return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage());
    }

    @Override
    public ResponseDto auditCat(Integer id, Integer auditStatus, String reasonForFailure) throws CymmtjException {

        Integer audited = 1;
        int visible;
        String mailContent;

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

        boolean awesome = false;
        boolean collect = false;

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

        CatDetailRep catDetailRep = new CatDetailRep(cat.getId(), cat.getName(), cat.getColor(), cat.getSex(),
                cat.getForeignTrade(), cat.getCharacter(), cat.getUpdateTime(), cat.getType(),
                cat.getVisible(), cat.getReferrer(), cat.getAudit(), cat.getCreateTime(), cat.getAwesomeCount(),
                cat.getCollectCount(), pics, commentReps.size(),
                commentReps, awesome, collect, regions);

        return ResponseDto.SUCCESS(catDetailRep);
    }

    @Override
    public ResponseDto listAllCatsByToken(Integer pageNum, Integer pageSize, String token) {
        PageHelper.startPage(pageNum, pageSize);
        List<CatRefrrer> catRefrrers = catRefrrerMapper.selectAllByToken(token);
        ArrayList<Cat> cats = listAllCatByCatRefrrers(catRefrrers);
        ArrayList<CatIntroRep> catIntroReps = generateCatIntroRepByCats(cats);
        PageInfo<CatIntroRep> resultCatPageInfo = new PageInfo<>(catIntroReps);
        PageResult<CatIntroRep> catIntroRepPageResult = new PageResult<>(resultCatPageInfo.getPageNum(), resultCatPageInfo.getPageSize(),
                resultCatPageInfo.getTotal(), resultCatPageInfo.getPages(), resultCatPageInfo.getList());
        return ResponseDto.SUCCESS(catIntroRepPageResult);

    }

    @Override
    public ResponseDto unThumbUp(Integer catId, String token) throws CymmtjException {
        catMapper.unThumbUp(catId);
        try {
            awesomeCatWechatUserMapper.deleteByCatIdAndToken(catId, token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "取消点赞失败啦！");
        }
        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto indexResult(Integer pageNum, Integer pageSize) throws CymmtjException {

        List<Cat> cats = catMapper.selectAllCats();

        HashMap<String, ArrayList<CatIntroRep>> resultMap = generateIndexInfo(cats);

        ArrayList<CatIntroRep> popularCatIntroReps = resultMap.get("popularCatIntroReps");

        PageResult pageResult = generatePageResult(pageNum, pageSize, resultMap);

        return ResponseDto.SUCCESS(new IndexRep(pageResult, popularCatIntroReps));
    }

    @Override
    public ResponseDto listCatsByRegion(Integer pageNum, Integer pageSize, Integer regionId) throws CymmtjException {
        List<CatRegion> catRegions = catRegionMapper.selectAllByRegionId(regionId);
        ArrayList<Cat> cats = listAllCatsByCatRegionInfo(catRegions);
        HashMap<String, ArrayList<CatIntroRep>> resultMap = generateIndexInfo(cats);
        ArrayList<CatIntroRep> popularCatIntroReps = resultMap.get("popularCatIntroReps");
        PageResult pageResult = generatePageResult(pageNum, pageSize, resultMap);

        return ResponseDto.SUCCESS(new IndexRep(pageResult, popularCatIntroReps));

    }

    @Override
    public ResponseDto listCatsOrderByAwesomeCount(Integer pageNum, Integer pageSize, Integer regionId) throws CymmtjException {
        List<Cat> cats;
        if (regionId == 0) {
            cats = catMapper.selectAllOrderByAwesomeCount();
        } else {
            cats = listAllCatsByRegionId(regionId);
            cats.sort((o1, o2) -> {
                if (o1.getAwesomeCount() > o2.getAwesomeCount()) {
                    return 1;
                }
                return -1;
            });
        }
        HashMap<String, ArrayList<CatIntroRep>> resultMap = generateIndexInfo(cats);

        ArrayList<CatIntroRep> popularCatIntroReps = resultMap.get("popularCatIntroReps");

        PageResult pageResult = generatePageResult(pageNum, pageSize, resultMap);

        return ResponseDto.SUCCESS(new IndexRep(pageResult, popularCatIntroReps));
    }

    @Override
    public ResponseDto listCatsOrderByCollectCount(Integer pageNum, Integer pageSize, Integer regionId) throws CymmtjException {
        List<Cat> cats;
        if (regionId == 0) {
            cats = catMapper.selectAllOrderByCollectCount();
        } else {
            cats = listAllCatsByRegionId(regionId);
            cats.sort((o1, o2) -> {
                if (o1.getCollectCount() > o2.getCollectCount()) {
                    return 1;
                }
                return -1;
            });
        }
        HashMap<String, ArrayList<CatIntroRep>> resultMap = generateIndexInfo(cats);

        ArrayList<CatIntroRep> popularCatIntroReps = resultMap.get("popularCatIntroReps");

        PageResult pageResult = generatePageResult(pageNum, pageSize, resultMap);

        return ResponseDto.SUCCESS(new IndexRep(pageResult, popularCatIntroReps));
    }

    @Override
    public ResponseDto listCatsOrderByCreateTime(Integer pageNum, Integer pageSize, Integer regionId) throws CymmtjException {

        List<Cat> cats;
        if (regionId == 0) {
            cats = catMapper.selectAllOrderByCreateTime();
        } else {
            cats = listAllCatsByRegionId(regionId);
            cats.sort((o1, o2) -> {
                if (o1.getCreateTime().isAfter(o2.getCreateTime())) {
                    return 1;
                }
                return -1;
            });
        }
        HashMap<String, ArrayList<CatIntroRep>> resultMap = generateIndexInfo(cats);

        ArrayList<CatIntroRep> popularCatIntroReps = resultMap.get("popularCatIntroReps");

        PageResult pageResult = generatePageResult(pageNum, pageSize, resultMap);

        return ResponseDto.SUCCESS(new IndexRep(pageResult, popularCatIntroReps));
    }

    @Override
    public ResponseDto collectCat(Integer catId, String token) throws CymmtjException {
        try {
            catMapper.collect(catId);
            collectWechatUserMapper.insertSelective(new CollectWechatUser(catId, token));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "收藏失败");
        }
        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto unCollect(Integer catId, String token) throws CymmtjException {
        try {
            catMapper.unCollect(catId);
            collectWechatUserMapper.deleteByCatIdAndToken(catId, token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "取消收藏失败");
        }
        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto insertCat(JSONObject jsonObject, List<MultipartFile> files, String token) throws CymmtjException {

        Cat cat = generateSimpleCat(jsonObject);
        cat.setReferrer("管理员");
        cat.setAudit(1);
        cat.setVisible(1);

        try {
            catMapper.insertSelective(cat);
            picService.insertPics(files, cat.getId(), null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "增加 Cat 失败！");
        }
        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto deleteCatByCatId(Integer catId, String token) throws CymmtjException {
        // 判断是否是管理员
        User user = userMapper.selectOneByToken(token);
        if (user.getUsername() == null) {
            return ResponseDto.FAIL();
        }

        try {
            // 删除 Cat
            catMapper.deleteByPrimaryKey(catId);

            // 获取相关评论
            List<CatComment> catComments = catCommentMapper.selectAllByCatId(catId);
            ArrayList<Comment> comments = listAllCommentsByCatCommentInfo(catComments);

            // 删除评论
            commentService.batchDeleteComments(comments);

            // 删除 Cat 和评论的中间记录
            catCommentMapper.deleteByCatId(catId);

        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "删除猫咪失败");
        }
        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto listAllAwesomeCatsByToken(String token) {
        // 查询所有赞过的 Cat 主键
        List<AwesomeCatWechatUser> awesomeCatWechatUsers = awesomeCatWechatUserMapper.selectAllByToken(token);
        // 获取所有的 Cats
        ArrayList<Cat> cats = listAllCatsByAwesomeCatWechatUser(awesomeCatWechatUsers);
        // 重新封装 Cats
        ArrayList<CatIntroRep> catIntroReps = generateCatIntroRepByCats(cats);
        return ResponseDto.SUCCESS(catIntroReps);
    }

    @Override
    public ResponseDto listAllCollectCatsByToken(String token) {
        // 获取所有收藏的 Cat 主键
        List<CollectWechatUser> collectWechatUsers = collectWechatUserMapper.selectAllByToken(token);
        // 获取所有的 Cats
        ArrayList<Cat> cats = listAllCatsByCollectWechatUser(collectWechatUsers);
        // 重新封装 Cats
        ArrayList<CatIntroRep> catIntroReps = generateCatIntroRepByCats(cats);
        return ResponseDto.SUCCESS(catIntroReps);
    }

    /**
     * 根据 List<CollectWechatUser> collectWechatUsers 获取所有的 Cats
     *
     * @param collectWechatUsers
     * @return ArrayList<Cat>
     */
    public ArrayList<Cat> listAllCatsByCollectWechatUser(List<CollectWechatUser> collectWechatUsers) {
        ArrayList<Cat> cats = new ArrayList<>();
        for (CollectWechatUser collectWechatUser : collectWechatUsers) {
            cats.add(catMapper.selectByPrimaryKey(collectWechatUser.getCatId()));
        }
        return cats;
    }

    /**
     * 根据List<AwesomeCatWechatUser> awesomeCatWechatUsers 获取左右的 Cats
     *
     * @param awesomeCatWechatUsers
     * @return ArrayList<Cat>
     */
    public ArrayList<Cat> listAllCatsByAwesomeCatWechatUser(List<AwesomeCatWechatUser> awesomeCatWechatUsers) {
        ArrayList<Cat> cats = new ArrayList<>();
        for (AwesomeCatWechatUser awesomeCatWechatUser : awesomeCatWechatUsers) {
            cats.add(catMapper.selectByPrimaryKey(awesomeCatWechatUser.getCatId()));
        }
        return cats;
    }

    /**
     * 根据传过来的 json 数据简单封装一个 Cat POJO
     *
     * @param jsonObject json 数据流
     * @return {@link Cat}
     */
    public Cat generateSimpleCat(JSONObject jsonObject) {

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

        return new Cat(null, name, color, sex, foreignTrade, character,
                LocalDateTime.now(), type, DEFAULTVIEWED,
                null, DEFAULTAUDITED, LocalDateTime.now(), 0, 0);
    }

    /**
     * 直接封装分页结果集
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return
     * @throws CymmtjException
     */
    public PageResult generatePageResult(Integer pageNum, Integer pageSize, HashMap<String, ArrayList<CatIntroRep>> resultMap) {
        ArrayList<CatIntroRep> catIntroReps = resultMap.get("catIntroReps");
        return dealPageResult(pageNum, pageSize, catIntroReps);
    }

    /**
     * 根据catRegions获取所有的猫咪
     *
     * @param catRegions catRegions
     * @return
     */
    public ArrayList<Cat> listAllCatsByCatRegionInfo(List<CatRegion> catRegions) {
        ArrayList<Cat> cats = new ArrayList<>();
        for (CatRegion catRegion : catRegions) {
            cats.add(catMapper.selectByPrimaryKey(catRegion.getCatId()));
        }
        return cats;
    }

    /**
     * 预处理分页结果
     *
     * @param pageNum      当前页码
     * @param pageSize     每页大小
     * @param catIntroReps cat 源
     * @return
     */
    public PageResult dealPageResult(Integer pageNum, Integer pageSize, ArrayList<CatIntroRep> catIntroReps) {
        PageHelper.startPage(pageNum, pageSize);

        PageInfo<CatIntroRep> resultCatPageInfo = new PageInfo<>(catIntroReps);

        return new PageResult<>(resultCatPageInfo.getPageNum(), resultCatPageInfo.getPageSize(),
                resultCatPageInfo.getTotal(), resultCatPageInfo.getPages(), resultCatPageInfo.getList());
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
     * 根据 RegionId 直接获取 Cats
     *
     * @param regionId
     * @return
     */
    public ArrayList<Cat> listAllCatsByRegionId(Integer regionId) {
        List<CatRegion> catRegions = catRegionMapper.selectAllByRegionId(regionId);
        return listAllCatsByCatRegionInfo(catRegions);
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

    /**
     * 根据List<CatRefrrer> catRefrrers获取所有的 Cats
     *
     * @param catRefrrers
     * @return
     */
    public ArrayList<Cat> listAllCatByCatRefrrers(List<CatRefrrer> catRefrrers) {
        ArrayList<Cat> cats = new ArrayList<>();
        for (CatRefrrer catRefrrer : catRefrrers) {
            cats.add(catMapper.selectByPrimaryKey(catRefrrer.getCatId()));
        }
        return cats;
    }

    /**
     * 根据 cats 信息重新组装 CatIntroRep
     *
     * @param cats
     * @return
     */
    public ArrayList<CatIntroRep> generateCatIntroRepByCats(List<Cat> cats) {
        ArrayList<CatIntroRep> catIntroReps = new ArrayList<>();
        for (Cat cat : cats) {
            List<CatPic> catPics = catPicMapper.selectAllByCatId(cat.getId());
            ArrayList<Pic> pics = listAllPicsByCatPicInfo(catPics);
            catIntroReps.add(new CatIntroRep(cat.getId(), cat.getName(), pics.get(0)));
        }
        return catIntroReps;
    }

    /**
     * 根据 cat 封装首页展示的数据
     *
     * @param cats
     * @return
     * @throws CymmtjException
     */
    public HashMap<String, ArrayList<CatIntroRep>> generateIndexInfo(List<Cat> cats) throws CymmtjException {
        ArrayList<CatIntroRep> catIntroReps;
        ArrayList<CatIntroRep> popularCatIntroReps;
        try {
            List<Cat> popularCats = catMapper.selectPopularCats();
            popularCatIntroReps = generateCatIntroRepByCats(popularCats);
            catIntroReps = generateCatIntroRepByCats(cats);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "服务器打瞌睡啦！");
        }

        HashMap<String, ArrayList<CatIntroRep>> resultMap = new HashMap<>();
        resultMap.put("catIntroReps", catIntroReps);
        resultMap.put("popularCatIntroReps", popularCatIntroReps);
        return resultMap;

    }

}
