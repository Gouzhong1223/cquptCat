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

import com.alibaba.fastjson.JSONObject;
import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.service.CatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    public AdminCatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping(value = "insertCat")
    public ResponseDto insertCat(@RequestBody JSONObject jsonObject,
                                 @RequestPart("files") List<MultipartFile> files,
                                 HttpServletRequest request) throws CymmtjException {
        String token = request.getHeader("token");
        LOGGER.info("新增 Cat");
        return catService.insertCat(jsonObject, files, token);
    }

    @DeleteMapping("deleteCat/{catId}")
    public ResponseDto deleteCat(HttpServletRequest request,
                                 @PathVariable("catId") Integer catId) throws CymmtjException {
        String token = request.getHeader("token");
        return catService.deleteCatByCatId(catId, token);
    }
}

