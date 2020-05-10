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
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.service.ArticleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 帖子 Web 控制器
 * @Date : create by QingSong in 2020-05-10 17:05
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.controller
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@RestController
@RequestMapping("article")
public class ArticleController {


    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @PostMapping("insertArticle")
    public ResponseDto insertArticle(@RequestBody JSONObject jsonObject,
                                     @RequestParam("files") MultipartFile[] multipartFiles) {
        List<MultipartFile> fileList = Arrays.asList(multipartFiles);
        String articleContext = jsonObject.getString("context");

//        articleService.insertArticle(fileList,articleContext,)

        return null;
    }

    @GetMapping("articleDetails/{articleId}")
    public ResponseDto articleDetails(@PathVariable("articleId") Integer articleId) {
        return null;
    }

}
