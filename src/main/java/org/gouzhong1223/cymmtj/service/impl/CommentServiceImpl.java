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

import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.dto.rep.CommentRep;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.entity.*;
import org.gouzhong1223.cymmtj.mapper.*;
import org.gouzhong1223.cymmtj.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 18:42
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service.impl
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final WechatUserMapper wechatUserMapper;
    private final CommentMapper commentMapper;
    private final CatCommentMapper catCommentMapper;
    private final ArticleCommentMapper articleCommentMapper;
    private final CommentWechatUserMapper commentWechatUserMapper;
    private final AwesomeCommentWechatUserMapper awesomeCommentWechatUserMapper;

    public CommentServiceImpl(WechatUserMapper wechatUserMapper, CommentMapper commentMapper,
                              CatCommentMapper catCommentMapper, ArticleCommentMapper articleCommentMapper,
                              CommentWechatUserMapper commentWechatUserMapper, AwesomeCommentWechatUserMapper awesomeCommentWechatUserMapper) {
        this.wechatUserMapper = wechatUserMapper;
        this.commentMapper = commentMapper;
        this.catCommentMapper = catCommentMapper;
        this.articleCommentMapper = articleCommentMapper;
        this.commentWechatUserMapper = commentWechatUserMapper;
        this.awesomeCommentWechatUserMapper = awesomeCommentWechatUserMapper;
    }

    @Override
    public ResponseDto addComment(String token, String commentContext, Integer catId, Integer articleId) throws CymmtjException {
        WechatUser wechatUser = wechatUserMapper.selectOneByToken(token);
        if (wechatUser.getNickName() == null) {
            return new ResponseDto(ResultCode.FAIL.getCode(), "评论出错啦！");
        }

        Comment comment = new Comment(null, commentContext, LocalDateTime.now(), 0, token, wechatUser.getNickName(), wechatUser.getAvatarUrl());

        commentMapper.insertSelective(comment);
        commentWechatUserMapper.insertSelective(new CommentWechatUser(comment.getId(), wechatUser.getToken()));

        if (catId != null && articleId == null) {
            try {
                catCommentMapper.insertSelective(new CatComment(comment.getId(), catId));
            } catch (Exception e) {
                e.printStackTrace();
                throw new CymmtjException(ResultCode.FAIL.getCode(), "评论出错误啦！");
            }
        }
        try {
            articleCommentMapper.insertSelective(new ArticleComment(comment.getId(), articleId));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "评论出错误啦！");
        }

        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto awesomeComment(Integer commentId, String token) {
        WechatUser wechatUser = wechatUserMapper.selectOneByToken(token);
        try {
            commentMapper.awesomeComment(commentId);
            awesomeCommentWechatUserMapper.insertSelective(new AwesomeCommentWechatUser(commentId, wechatUser.getToken()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto(ResultCode.FAIL.getCode(), "评论点赞出错啦！");
        }
        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto unAwesomeComment(Integer commentId, String token) {
        WechatUser wechatUser = wechatUserMapper.selectOneByToken(token);
        try {
            commentMapper.unAwesomeComment(commentId);
            awesomeCommentWechatUserMapper.deleteByCommentIdAndToken(commentId, wechatUser.getToken());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto(ResultCode.FAIL.getCode(), "取消点赞出错啦！");
        }
        return ResponseDto.SUCCESS();
    }

    @Override
    public ResponseDto listCommentByCatId(Integer catId, String token) throws CymmtjException {

        List<CommentRep> commentReps = null;
        try {
            List<CatComment> catComments = catCommentMapper.selectAllByCatId(catId);
            List<AwesomeCommentWechatUser> awesomeCommentWechatUsers = awesomeCommentWechatUserMapper.selectAllByToken(token);
            ArrayList<Comment> comments = getAllCommentsByCatCommentInfo(catComments);
            commentReps = dealCommentsWithToken(comments, awesomeCommentWechatUsers);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "获取评论失败啦！");
        }

        return ResponseDto.SUCCESS(commentReps);
    }


    /**
     * 处理所有的评论，重新封装评论对象集合
     *
     * @param comments                  所有的评论
     * @param awesomeCommentWechatUsers 该用户所赞过的评论
     * @return
     */
    public static List<CommentRep> dealCommentsWithToken(ArrayList<Comment> comments, List<AwesomeCommentWechatUser> awesomeCommentWechatUsers) {

        ArrayList<CommentRep> commentReps = new ArrayList<>();

        for (Comment comment : comments) {
            Boolean awesome = false;
            for (AwesomeCommentWechatUser awesomeCommentWechatUser : awesomeCommentWechatUsers) {
                if (Objects.equals(comment.getId(), awesomeCommentWechatUser.getCommentId())) {
                    awesome = true;
                }
            }
            commentReps.add(new CommentRep(comment.getId(), comment.getContent(),
                    comment.getCreateTime(), comment.getAwesomeCount(), comment.getToken(),
                    comment.getNickName(), comment.getAvaterUrl(), awesome));
        }
        return commentReps;
    }

    /**
     * 根据 catComment 信息获取该 Cat 对应的所有 Comments
     *
     * @param catComments
     * @return
     */
    private ArrayList<Comment> getAllCommentsByCatCommentInfo(List<CatComment> catComments) {
        ArrayList<Comment> comments = new ArrayList<>();
        catComments.forEach(e -> {
            comments.add(commentMapper.selectByPrimaryKey(e.getCommentId()));
        });
        return comments;
    }

}
