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
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.PopularCat;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.pojo.Region;
import org.gouzhong1223.cymmtj.service.RegionService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-20 7:21 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.controller
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@RestController
@RequestMapping("region")
public class RegionController {


    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/catsByRegionId/{regionId}")
    public ResponseDto selectCatsByRegionId(@PathVariable("regionId") Integer regionId) {
        ArrayList<PopularCat> CatList = regionService.selectCatsByRegionId(regionId);
        return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage(), CatList);
    }

    @GetMapping("/allRegion")
    public ResponseDto listAllRegions() {
        List<Region> regions = regionService.selectAllRegions();
        if (CollectionUtils.isNotEmpty(regions)) {
            return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage(), regions);
        }
        return new ResponseDto(ResultCode.FAIL.getCode(), ResultMessage.FAIL.getMessaage(), regions);
    }

    @PostMapping("addRegion")
    public ResponseDto addRegion(@RequestBody Region region) {
        if (region != null) {
            Region result = regionService.addRegion(region);
            return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage(), result);
        }
        return new ResponseDto(ResultCode.FAIL.getCode(), ResultMessage.FAIL.getMessaage());
    }


}
