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

import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 17:17
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface ArticleService {
    /**
     * 用户新增帖子
     *
     * @param fileList       图片文件
     * @param articleContext 帖子正文
     * @param token          用户 token
     * @return
     */
    ResponseDto insertArticle(List<MultipartFile> fileList, String articleContext, String token) throws CymmtjException;

    /**
     * 给帖子点赞
     *
     * @param token     微信用户 token
     * @param articleId 文章 ID
     * @return
     */
    ResponseDto awesomeArticle(String token, Integer articleId) throws CymmtjException;

    /**
     * 取消对帖子的点赞
     *
     * @param token     微信用户 token
     * @param articleId 文章 ID
     * @return
     */
    ResponseDto unAwesomeArticle(String token, Integer articleId) throws CymmtjException;

    /**
     * 获取所有的帖子
     *
     * @param token 微信用户 token
     * @return
     */
    ResponseDto listAllArticles(String token);
}
