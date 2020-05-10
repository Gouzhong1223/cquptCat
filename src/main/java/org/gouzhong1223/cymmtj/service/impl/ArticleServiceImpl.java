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
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.entity.*;
import org.gouzhong1223.cymmtj.mapper.ArticleAwesomeMapper;
import org.gouzhong1223.cymmtj.mapper.ArticleMapper;
import org.gouzhong1223.cymmtj.mapper.ArticlePicMapper;
import org.gouzhong1223.cymmtj.mapper.WechatUserMapper;
import org.gouzhong1223.cymmtj.service.ArticleService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
    private final ArticleAwesomeMapper articleAwesomeMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper, WechatUserMapper wechatUserMapper,
                              PicService picService, ArticlePicMapper articlePicMapper,
                              ArticleAwesomeMapper articleAwesomeMapper) {
        this.articleMapper = articleMapper;
        this.wechatUserMapper = wechatUserMapper;
        this.picService = picService;
        this.articlePicMapper = articlePicMapper;
        this.articleAwesomeMapper = articleAwesomeMapper;
    }

    @Override
    public ResponseDto insertArticle(List<MultipartFile> fileList, String articleContext, String token) throws CymmtjException {

        WechatUser wechatUser = wechatUserMapper.selectOneByToken(token);
        if (wechatUser.getOpenId() == null) {
            return new ResponseDto(ResultCode.FAIL.getCode(), "用户不存在");
        }

        Article record = new Article(null, articleContext, 0, LocalDateTime.now(), token, wechatUser.getNickName());

        try {
            List<Pic> pics = picService.insertPics(fileList, null);
            articleMapper.insertSelective(record);
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
            articleAwesomeMapper.insertSelective(new ArticleAwesome(articleId, token, wechatUser.getNickName()));
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
            articleAwesomeMapper.deleteByArticleIdAndToken(articleId, token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "取消点赞的时候发生错误啦！");
        }

        return ResponseDto.SUCCESS();
    }


}
