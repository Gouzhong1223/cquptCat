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

import org.apache.commons.collections.CollectionUtils;
import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.ArticleDetailRep;
import org.gouzhong1223.cymmtj.dto.rep.ArticleRep;
import org.gouzhong1223.cymmtj.dto.rep.CommentRep;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.entity.*;
import org.gouzhong1223.cymmtj.mapper.*;
import org.gouzhong1223.cymmtj.service.ArticleService;
import org.gouzhong1223.cymmtj.service.CommentService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 17:17
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service.impl
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final WechatUserMapper wechatUserMapper;
    private final PicService picService;
    private final ArticlePicMapper articlePicMapper;
    private final AwesomeCommentWechatUserMapper awesomeCommentWechatUserMapper;
    private final AwesomeArticleWechatUserMapper awesomeArticleWechatUserMapper;
    private final ArticleCommentMapper articleCommentMapper;
    private final CommentMapper commentMapper;
    private final CommentService commentService;

    public ArticleServiceImpl(ArticleMapper articleMapper, WechatUserMapper wechatUserMapper,
                              PicService picService, ArticlePicMapper articlePicMapper,
                              AwesomeCommentWechatUserMapper awesomeCommentWechatUserMapper,
                              AwesomeArticleWechatUserMapper awesomeArticleWechatUserMapper,
                              ArticleCommentMapper articleCommentMapper,
                              CommentMapper commentMapper, CommentService commentService) {
        this.articleMapper = articleMapper;
        this.wechatUserMapper = wechatUserMapper;
        this.picService = picService;
        this.articlePicMapper = articlePicMapper;
        this.awesomeCommentWechatUserMapper = awesomeCommentWechatUserMapper;
        this.awesomeArticleWechatUserMapper = awesomeArticleWechatUserMapper;
        this.articleCommentMapper = articleCommentMapper;
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @Override
    public ResponseDto insertArticle(List<MultipartFile> fileList, String articleContext, String token) throws CymmtjException {

        WechatUser wechatUser = wechatUserMapper.selectOneByToken(token);
        if (wechatUser.getOpenId() == null) {
            return new ResponseDto(ResultCode.FAIL.getCode(), "用户不存在");
        }

        Article record = new Article(null, articleContext, 0, LocalDateTime.now(),
                token, wechatUser.getNickName(), wechatUser.getAvatarUrl(), 0);

        try {
            articleMapper.insertSelective(record);
            List<Pic> pics = picService.insertPics(fileList, null, record.getId());
            pics.forEach(e -> {
                articlePicMapper.insertSelective(new ArticlePic(e.getId(), record.getId()));
            });
        } catch (Exception e) {
            throw new CymmtjException(ResultCode.FAIL.getCode(), "新增帖子失败！");
        }

        return new ResponseDto(ResultCode.FAIL.getCode(), ResultMessage.SUCCESS.getMessaage());
    }

    @Override
    public ResponseDto awesomeArticle(String token, Integer articleId) throws CymmtjException {
        WechatUser wechatUser = wechatUserMapper.selectOneByToken(token);
        try {
            articleMapper.awesomeArticle(articleId);
            awesomeArticleWechatUserMapper.insertSelective(new AwesomeArticleWechatUser(articleId, token, wechatUser.getNickName()));

        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "点赞的时候发生错误啦！");
        }

        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto unAwesomeArticle(String token, Integer articleId) throws CymmtjException {

        try {
            articleMapper.unAwesomeArticle(articleId);
            awesomeArticleWechatUserMapper.deleteByArticleIdAndToken(articleId, token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "取消点赞的时候发生错误啦！");
        }

        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto listAllArticles(String token) {

        List<Article> articles = articleMapper.selectAll();

        ArrayList<ArticleRep> articleReps = dealArticleWithToken(articles, token);

        return ResponseDto.SUCCESS(articleReps);
    }

    @Override
    public ResponseDto articleDetail(String token, Integer articleId) {

        // 先查询出对应的帖子
        Article article = articleMapper.selectByPrimaryKey(articleId);
        // 根据帖子 ID 查出对应的评论 ID
        List<ArticleComment> articleComments = articleCommentMapper.selectAllByActicleId(articleId);
        // 查询出所有的评论
        ArrayList<Comment> comments = listAllCommentsByArticleCommentInfo(articleComments);
        // 获取该用户所有赞过的评论
        List<AwesomeCommentWechatUser> awesomeCommentWechatUsers = awesomeCommentWechatUserMapper.selectAllByToken(token);
        // 根据评论和该用户赞过的评论重新封装评论信息
        List<CommentRep> commentReps = CommentServiceImpl.dealCommentsWithToken(comments, awesomeCommentWechatUsers);
        // 判断该用户是否赞过改帖子
        List<AwesomeArticleWechatUser> awesomeArticleWechatUsers = awesomeArticleWechatUserMapper.selectByTokenAndArticleId(token, articleId);
        Boolean awecome = false;
        if (CollectionUtils.isNotEmpty(awesomeArticleWechatUsers)) {
            awecome = true;
        }

        ArticleDetailRep articleDetailRep = new ArticleDetailRep(article.getId(), article.getContext(),
                article.getAwesomeCount(), article.getCreateTime(), article.getToken(),
                article.getNickName(), article.getAvatarUrl(), article.getCommentCount(), awecome, commentReps);


        return ResponseDto.SUCCESS(articleDetailRep);
    }

    @Override
    public ResponseDto listAllArticleByToken(String token) {
        List<Article> articles = articleMapper.selectAllByToken(token);
        ArrayList<ArticleRep> articleReps = dealArticleWithToken(articles, token);
        return ResponseDto.SUCCESS(articleReps);
    }

    @Override
    public ResponseDto deleteArticle(Integer articleId, String token) throws CymmtjException {

        try {
            // 删除文章
            articleMapper.deleteByPrimaryKey(articleId);
            // 获取文章相关评论
            List<ArticleComment> articleComments = articleCommentMapper.selectAllByActicleId(articleId);
            ArrayList<Comment> comments = listAllCommentsByArticleCommentInfo(articleComments);

            // 删除文章的评论
            commentService.batchDeleteComments(comments);
            // 删除帖子评论的关联记录
            articleCommentMapper.deleteByActicleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "删除文章失败！");
        }

        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto listAllAwesomeArticlesByToken(String token) {
        // 获取用户赞过的帖子主键
        List<AwesomeArticleWechatUser> awesomeArticleWechatUsers = awesomeArticleWechatUserMapper.selectAllByToken(token);
        // 获取用户赞过的帖子
        ArrayList<Article> articles = listAllArticlesByawesomeArticleWechatUsers(awesomeArticleWechatUsers);
        // 重新封装帖子
        ArrayList<ArticleRep> articleReps = dealArticleWithToken(articles, token);
        return ResponseDto.SUCCESS(articleReps);
    }

    /**
     * 根据 List<AwesomeArticleWechatUser> awesomeArticleWechatUsers 获取所有帖子
     *
     * @param awesomeArticleWechatUsers
     * @return
     */
    public ArrayList<Article> listAllArticlesByawesomeArticleWechatUsers(List<AwesomeArticleWechatUser> awesomeArticleWechatUsers) {
        ArrayList<Article> articles = new ArrayList<>();
        for (AwesomeArticleWechatUser awesomeArticleWechatUser : awesomeArticleWechatUsers) {
            articles.add(articleMapper.selectByPrimaryKey(awesomeArticleWechatUser.getArticleId()));
        }
        Collections.sort(articles, (o1, o2) -> {
            if (o1.getCreateTime().isAfter(o2.getCreateTime())) {
                return 1;
            }
            return -1;
        });
        return articles;
    }

    /**
     * 根据ArticleComment获取所有的评论
     *
     * @param articleComments ArticleCommentInfo
     * @return
     */
    public ArrayList<Comment> listAllCommentsByArticleCommentInfo(List<ArticleComment> articleComments) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (ArticleComment articleComment : articleComments) {
            comments.add(commentMapper.selectByPrimaryKey(articleComment.getCommentId()));
        }
        return comments;
    }

    /**
     * 根据 token 重新封装返回的帖子
     *
     * @param articles 帖子源对象
     * @param token    微信用户 token
     * @return
     */
    public ArrayList<ArticleRep> dealArticleWithToken(List<Article> articles, String token) {

        ArrayList<ArticleRep> articleReps = new ArrayList<>();
        List<AwesomeArticleWechatUser> awesomeArticleWechatUsers = awesomeArticleWechatUserMapper.selectAllByToken(token);

        // 判断用户是否已经赞过帖子了
        for (Article article : articles) {
            Boolean awesome = false;
            for (AwesomeArticleWechatUser awesomeArticleWechatUser : awesomeArticleWechatUsers) {
                if (Objects.equals(article.getId(), awesomeArticleWechatUser.getArticleId())) {
                    awesome = true;
                }
            }
            articleReps.add(new ArticleRep(article.getId(), article.getContext(),
                    article.getAwesomeCount(), article.getCreateTime(), article.getToken(),
                    article.getNickName(), article.getAvatarUrl(), article.getCommentCount(), awesome));
        }
        return articleReps;
    }


}
