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

package org.gouzhong1223.cymmtj.controller.admin;

import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.entity.Region;
import org.gouzhong1223.cymmtj.service.RegionService;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-20 9:34 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.controller.admin
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@RestController
@RequestMapping("admin/region")
public class AdminRegionController {

    private RegionService regionService;

    public AdminRegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping("addRegion")
    public ResponseDto addRegion(@RequestBody Region region) {
        if (region != null) {
            Region result = regionService.addRegion(region);
            return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage(), result);
        }
        return new ResponseDto(ResultCode.FAIL.getCode(), ResultMessage.FAIL.getMessaage());
    }

    @DeleteMapping("deleteRegion/{id}")
    public ResponseDto deleteRegion(@PathVariable("id") Integer id) {
        return regionService.deleteRegion(id);
    }

    @PutMapping("updateRegion")
    public ResponseDto updateRegion(@RequestBody Region region) throws CymmtjException {
        if (region.getRegionName() == null) {
            return new ResponseDto(ResultCode.FAIL.getCode(), "参数不能为空！");
        }
        return regionService.updateRegion(region);
    }


}
