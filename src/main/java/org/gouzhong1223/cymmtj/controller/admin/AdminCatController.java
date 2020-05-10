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

import org.apache.commons.collections.CollectionUtils;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.dto.req.CatRequest;
import org.gouzhong1223.cymmtj.entity.Cat;
import org.gouzhong1223.cymmtj.entity.Pic;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.gouzhong1223.cymmtj.service.UserService;
import org.gouzhong1223.cymmtj.util.RandomNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 管理员添加猫咪信息 Web 控制器
 * @Date : create by QingSong in 2020-04-18 6:41 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.controller.admin
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@RestController
@RequestMapping("/admin/cat")
public class AdminCatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminCatController.class);

    private final CatService catService;
    private final PicService picService;
    private final UserService userService;

    public AdminCatController(CatService catService, PicService picService, UserService userService) {
        this.catService = catService;
        this.picService = picService;
        this.userService = userService;
    }

    @PostMapping(value = "/insertCat")
    public ResponseDto insertCat(@RequestBody CatRequest catRequest, @RequestParam("files") List<MultipartFile> files) {

        if (catRequest != null && CollectionUtils.isNotEmpty(files)) {
            Integer catId = RandomNumber.createNumber();
            List<Pic> pics = picService.insertPics(files, catId);
            if (CollectionUtils.isNotEmpty(pics)) {
                Cat cat = new Cat();
                BeanUtils.copyProperties(catRequest, cat);
                cat.setId(catId);
                catService.insertOrUpdateCat(cat);
                return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage());
            }
        }
        return ResponseDto.builder().code(ResultCode.FAIL.getCode()).message(ResultMessage.FAIL.getMessaage()).build();
    }
}

