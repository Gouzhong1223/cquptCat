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
import org.gouzhong1223.cymmtj.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : User Web 控制器
 * @Date : create by QingSong in 2020-04-18 6:43 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.controller.admin
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    private final UserService userService;
    private final CatService catService;

    public AdminUserController(UserService userService, CatService catService) {
        this.userService = userService;
        this.catService = catService;
    }


    @PostMapping("login")
    public ResponseDto login(@RequestBody JSONObject jsonObject,
                             HttpServletRequest request) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        return userService.login(username, password, request);
    }

    @GetMapping("loginOut")
    public ResponseDto loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("adminUser");
        return ResponseDto.SUCCESS(null);
    }

    @PostMapping("auditCat")
    public ResponseDto additCat(@RequestBody JSONObject jsonObject) throws CymmtjException {
        Integer id = jsonObject.getInteger("id");
        Integer auditStatus = jsonObject.getInteger("auditStatus");
        // 未通过原因，所以这个参数可能为空也可能不为空
        String reasonForFailure = jsonObject.getString("reasonForFailure");
        return catService.auditCat(id, auditStatus, reasonForFailure);
    }
}
