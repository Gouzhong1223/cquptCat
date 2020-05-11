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

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 18:41
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CommentService {
    /**
     * 添加评论
     *
     * @param token          微信用户 token
     * @param commentContext 评论正文
     * @param catId          猫咪 ID
     * @param articleId      帖子 ID
     * @return
     */
    ResponseDto addComment(String token, String commentContext, Integer catId, Integer articleId) throws CymmtjException;

    /**
     * 给评论点赞
     *
     * @param commentId 评论 id
     * @param token     微信用户 token
     * @return
     */
    ResponseDto awesomeComment(Integer commentId, String token);

    /**
     * 取消对评论的点赞
     *
     * @param commentId 评论 ID
     * @param token     微信用户 token
     * @return
     */
    ResponseDto unAwesomeComment(Integer commentId, String token);

    /**
     * 根据 CatID 获取所有的评论
     *
     * @param catId
     * @param token
     * @return
     */
    ResponseDto listCommentByCatId(Integer catId, String token) throws CymmtjException;
}
