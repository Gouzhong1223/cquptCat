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

import org.apache.commons.collections.CollectionUtils;
import org.gouzhong1223.cymmtj.common.PageResult;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.ResultCat;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.pojo.Cat;
import org.gouzhong1223.cymmtj.pojo.Pic;
import org.gouzhong1223.cymmtj.pojo.Region;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.gouzhong1223.cymmtj.service.RegionService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    private RegionService regionService;

    public CatController(CatService catService, PicService picService, RegionService regionService) {
        this.catService = catService;
        this.picService = picService;
        this.regionService = regionService;
    }

    @GetMapping("pagingListCat")
    public ResponseDto pagingListCatInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        // 获取分页结果
        PageResult<ResultCat> resultCatPageResult = catService.pagingListCat(pageNum, pageSize);

        if (CollectionUtils.isNotEmpty(resultCatPageResult.getList())) {
            // 设置首图
            resultCatPageResult.getList().forEach(e -> {
                e.setPicLink(picService.selectFirstPic(e.getId()));
            });
            return ResponseDto.builder().code(ResultCode.SUCCESS.getCode()).message(ResultMessage.SUCCESS.getMessaage()).data(resultCatPageResult).build();
        }
        return ResponseDto.builder().code(ResultCode.SUCCESS.getCode()).message(ResultMessage.SUCCESS.getMessaage()).build();
    }

    @GetMapping("/popularCat")
    public ResponseDto listPopularCats() {
        List<ResultCat> resultCats = catService.selectPopularCats();
        resultCats.forEach(e -> {
            e.setPicLink(picService.selectFirstPic(e.getId()));
        });
        return new ResponseDto<>(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage(), resultCats);
    }

    @GetMapping("catDetail/{id}")
    public ResponseDto getCatDetail(@PathVariable("id") Integer id) {
        Cat cat = catService.selectCatByid(id);
        if (cat != null) {
            List<Pic> pics = picService.selectPicsByCatId(id);
            List<Region> regions = regionService.selectRegionsByCatId(id);
            HashMap hashMap = new HashMap();
            hashMap.put("cat", cat);
            hashMap.put("pics", pics);
            hashMap.put("regions", regions);
            return new ResponseDto<>(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage(), hashMap);
        }
        return new ResponseDto<>(ResultCode.FAIL.getCode(), ResultMessage.FAIL.getMessaage());
    }

}
