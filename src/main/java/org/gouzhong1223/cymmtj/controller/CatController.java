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
import org.gouzhong1223.cymmtj.dto.rep.CatIntroRep;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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

    public CatController(CatService catService, PicService picService) {
        this.catService = catService;
        this.picService = picService;
    }

    @GetMapping("pagingListCat")
    public ResponseDto pagingListCatInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        // 获取分页结果
        PageResult<CatIntroRep> resultCatPageResult = catService.pagingListCat(pageNum, pageSize);
        return ResponseDto.SUCCESS(resultCatPageResult);
    }

    @GetMapping("index")
    public ResponseDto indexPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) throws CymmtjException {
        return catService.indexResult(pageNum, pageSize);
    }

    @GetMapping("listCatsByRegion/{regionId}")
    public ResponseDto listCatsByRegion(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                        @PathVariable("regionId") Integer regionId) throws CymmtjException {
        return catService.listCatsByRegion(pageNum, pageSize, regionId);
    }

    @GetMapping("listCatsOrderByAwesome/{regionId}")
    public ResponseDto listCatsOrderByAwesomeCount(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @PathVariable("regionId") Integer regionId) throws CymmtjException {
        return catService.listCatsOrderByAwesomeCount(pageNum, pageSize,regionId);
    }

    @GetMapping("listCatsOrderByCollect/{regionId}")
    public ResponseDto listCatsOrderByCollectCount(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @PathVariable("regionId") Integer regionId) throws CymmtjException {
        return catService.listCatsOrderByCollectCount(pageNum, pageSize,regionId);
    }

    @GetMapping("listCatsOrderByCreateTime/{regionId}")
    public ResponseDto listCatsOrderByCreateTime(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                 @PathVariable("regionId") Integer regionId) throws CymmtjException {
        return catService.listCatsOrderByCreateTime(pageNum, pageSize,regionId);
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
