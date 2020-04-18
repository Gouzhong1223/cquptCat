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

import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.dto.req.CatRequest;
import org.gouzhong1223.cymmtj.pojo.Cat;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.util.RandomNumber;
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


    private CatService catService;

    public AdminCatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping(value = "/insert")
    public ResponseDto insertCat(@RequestBody CatRequest catRequest, @RequestParam("files") List<MultipartFile> files) {
        files.forEach(e->{

        });
        Cat cat = new Cat();
        BeanUtils.copyProperties(catRequest, cat);
        cat.setId(RandomNumber.createNumber());
        catService.insertOrUpdateCat(cat);
        return null;
    }

}

