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
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.dto.req.CatRequest;
import org.gouzhong1223.cymmtj.pojo.Cat;
import org.gouzhong1223.cymmtj.service.CatService;
import org.gouzhong1223.cymmtj.service.PicService;
import org.gouzhong1223.cymmtj.util.RandomNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


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

    private CatService catService;
    private PicService picService;

    public AdminCatController(CatService catService, PicService picService) {
        this.catService = catService;
        this.picService = picService;
    }

    @PostMapping(value = "/insert")
    public ResponseDto insertCat(@RequestBody CatRequest catRequest, @RequestParam("files") List<MultipartFile> files) {

        if (catRequest != null && CollectionUtils.isNotEmpty(files)) {
            Integer catId = RandomNumber.createNumber();
            picService.insertPics(files,catId);
            Cat cat = new Cat();
            BeanUtils.copyProperties(catRequest, cat);
            cat.setId(catId);
            catService.insertOrUpdateCat(cat);
        }

        return null;
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futureArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Future<String> future = executorService.submit(() -> {
                Thread.sleep(10000);
                return String.valueOf(finalI);
            });
            futureArrayList.add(future);
        }

        ArrayList<String> stringArrayList = new ArrayList<>();

        futureArrayList.forEach(e -> {
            try {
                stringArrayList.add(e.get());
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });

        executorService.shutdown();

        System.out.println(stringArrayList);
    }

}

