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

import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.gouzhong1223.cymmtj.common.PageResult;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.PopularCat;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.pojo.Cat;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-19 12:11 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.controller
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@RestController
@RequestMapping("/cat")
public class CatController {

    private CatService catService;
    private PicService picService;

    public CatController(CatService catService, PicService picService) {
        this.catService = catService;
        this.picService = picService;
    }

    @GetMapping("pagingListCat")
    public ResponseDto pagingListCatInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        PageResult<Cat> catPageResult = catService.pagingListCat(pageNum, pageSize);
        if (CollectionUtils.isNotEmpty(catPageResult.getList())) {
            return ResponseDto.builder().code(ResultCode.SUCCESS.getCode()).message(ResultMessage.SUCCESS.getMessaage()).data(catPageResult).build();
        }

        return null;
    }

    @GetMapping("/popularCat")
    public ResponseDto listPopularCats() {
        List<PopularCat> popularCats = catService.selectPopularCats();
        popularCats.forEach(e -> {
            e.setPicLink(picService.selectFirstPic(e.getId()));
        });
        return new ResponseDto<>(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage(), popularCats);
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        ResponseDto<Cat> catResponseDto = new ResponseDto<>(100, "100", cat);
        JSONObject jsonObject = JSONObject.fromObject(catResponseDto);
        System.out.println(jsonObject.toString());

    }

}
