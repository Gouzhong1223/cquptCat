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

package org.gouzhong1223.cymmtj.service.impl;

import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.mapper.UserMapper;
import org.gouzhong1223.cymmtj.entity.User;
import org.gouzhong1223.cymmtj.service.UserService;
import org.gouzhong1223.cymmtj.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-19 2:23 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service.impl
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ResponseDto login(String username, String password, HttpServletRequest request) {

        User result = userMapper.selectOneByUsername(username);
        if (result == null) {
            return new ResponseDto(ResultCode.FAIL.getCode(), "管理员账户不存在");
        }
        // 验证密码是否正确
        if (MD5Util.code(password).equals(result.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("adminUser", request);
            return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultMessage.SUCCESS.getMessaage());
        }
        // 密码验证不通过！
        return new ResponseDto(ResultCode.FAIL.getCode(), "密码错误！");
    }
}
