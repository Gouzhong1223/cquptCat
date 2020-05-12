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
import org.gouzhong1223.cymmtj.common.PageResult;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.CatIntroRep;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.dto.rep.ResultCat;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.gouzhong1223.cymmtj.service.RegionService;
import org.gouzhong1223.cymmtj.service.WeChatService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    private final CatService catService;
    private final PicService picService;
    private final RegionService regionService;
    private final WeChatService weChatService;

    public CatController(CatService catService, PicService picService, RegionService regionService, WeChatService weChatService) {
        this.catService = catService;
        this.picService = picService;
        this.regionService = regionService;
        this.weChatService = weChatService;
    }

    @GetMapping("pagingListCat")
    public ResponseDto pagingListCatInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        // 获取分页结果
        PageResult<CatIntroRep> resultCatPageResult = catService.pagingListCat(pageNum, pageSize);
        return ResponseDto.SUCCESS(resultCatPageResult);
    }

    @GetMapping("popularCat")
    public ResponseDto listPopularCats() {
        List<ResultCat> resultCats = catService.selectPopularCats();
        resultCats.forEach(e -> {
            e.setPicLink(picService.selectFirstPic(e.getId()));
        });
        return new ResponseDto<>(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage(), resultCats);
    }

    @PostMapping("catDetail")
    public ResponseDto getCatDetail(@RequestBody JSONObject jsonObject,
                                    HttpServletRequest request) {

        String token = request.getHeader("token");
        Integer catId = jsonObject.getInteger("catId");

        return catService.catDetail(token, catId);

    }

    @PostMapping("thumbUp")
    public ResponseDto thumbUp(@RequestBody JSONObject jsonObject,
                               HttpServletRequest request) throws CymmtjException {
        Integer catId = jsonObject.getInteger("catId");
        String token = request.getHeader("token");
        return catService.thumbUp(catId, token);

    }

    @PostMapping("unThumbUp")
    public ResponseDto cancelPraise(@RequestBody JSONObject jsonObject,
                                    HttpServletRequest request) throws CymmtjException {
        Integer catId = jsonObject.getInteger("catId");
        String token = request.getHeader("token");
        return catService.unThumbUp(catId, token);
    }

    @PostMapping("contribute")
    public ResponseDto contribute(@RequestBody JSONObject jsonObject,
                                  HttpServletRequest request) throws CymmtjException {
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");

        return catService.contributeCat(jsonObject, openId);
    }

    @GetMapping("listAllCatsByToken")
    public ResponseDto listAllCatsByToken(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                          HttpServletRequest request) {
        String token = request.getHeader("token");
        return catService.listAllCatsByToken(pageNum, pageSize, token);
    }


}
