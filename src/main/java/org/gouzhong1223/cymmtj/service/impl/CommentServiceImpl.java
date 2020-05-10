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
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.entity.ArticleComment;
import org.gouzhong1223.cymmtj.entity.CatComment;
import org.gouzhong1223.cymmtj.entity.Comment;
import org.gouzhong1223.cymmtj.entity.WechatUser;
import org.gouzhong1223.cymmtj.mapper.ArticleCommentMapper;
import org.gouzhong1223.cymmtj.mapper.CatCommentMapper;
import org.gouzhong1223.cymmtj.mapper.CommentMapper;
import org.gouzhong1223.cymmtj.mapper.WechatUserMapper;
import org.gouzhong1223.cymmtj.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    public CommentServiceImpl(WechatUserMapper wechatUserMapper, CommentMapper commentMapper,
                              CatCommentMapper catCommentMapper, ArticleCommentMapper articleCommentMapper) {
        this.wechatUserMapper = wechatUserMapper;
        this.commentMapper = commentMapper;
        this.catCommentMapper = catCommentMapper;
        this.articleCommentMapper = articleCommentMapper;
    }

    @Override
    public ResponseDto addComment(String token, String commentContext, Integer catId, Integer articleId) throws CymmtjException {
        WechatUser wechatUser = wechatUserMapper.selectOneByToken(token);
        if (wechatUser.getNickName() == null) {
            return new ResponseDto(ResultCode.FAIL.getCode(), "评论出错啦！");
        }

        Comment comment = new Comment(null, commentContext, LocalDateTime.now(), 0, token, wechatUser.getNickName(), wechatUser.getAvatarUrl());
        commentMapper.insertSelective(comment);

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
}
