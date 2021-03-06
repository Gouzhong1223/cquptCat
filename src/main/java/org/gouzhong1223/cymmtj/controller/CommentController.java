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

package org.gouzhong1223.cymmtj.controller;

import com.alibaba.fastjson.JSONObject;
import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 评论 Web 控制器
 * @Date : create by QingSong in 2020-05-10 18:41
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.controller
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("addComment")
    public ResponseDto addComment(@RequestBody JSONObject jsonObject,
                                  HttpServletRequest request) throws CymmtjException {
        String token = request.getHeader("token");
        String commentContext = jsonObject.getString("commentContext");
        Integer catId = jsonObject.getInteger("catId");
        Integer articleId = jsonObject.getInteger("articleId");
        return commentService.addComment(token, commentContext, catId, articleId);
    }

    @PostMapping("awesomeComment")
    public ResponseDto awesomeComment(HttpServletRequest request,
                                      @RequestBody JSONObject jsonObject) {
        Integer commentId = jsonObject.getInteger("commentId");
        String token = request.getHeader("token");
        return commentService.awesomeComment(commentId, token);
    }

    @PostMapping("unAwesomeComment")
    public ResponseDto unAwesomeComment(HttpServletRequest request,
                                        @RequestBody JSONObject jsonObject) {
        Integer commentId = jsonObject.getInteger("commentId");
        String token = request.getHeader("token");
        return commentService.unAwesomeComment(commentId, token);
    }

    @PostMapping("listCommentByCatId")
    public ResponseDto listCommentByCatId(HttpServletRequest request,
                                          @RequestBody JSONObject jsonObject) throws CymmtjException {
        Integer catId = jsonObject.getInteger("catId");
        String token = request.getHeader("token");
        return commentService.listCommentByCatId(catId, token);
    }

    @DeleteMapping("delectComment")
    public ResponseDto deleteComment(@RequestBody JSONObject jsonObject,
                                     HttpServletRequest request) throws CymmtjException {
        String token = request.getHeader("token");
        Integer commentId = jsonObject.getInteger("commentId");
        Integer catId = jsonObject.getInteger("catId");
        Integer articleId = jsonObject.getInteger("articleId");
        return commentService.deleteComment(token, commentId, catId, articleId);
    }

}
