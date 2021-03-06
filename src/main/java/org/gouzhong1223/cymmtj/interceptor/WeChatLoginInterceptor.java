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

package org.gouzhong1223.cymmtj.interceptor;

import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.common.ResultMessage;
import org.gouzhong1223.cymmtj.entity.WechatUser;
import org.gouzhong1223.cymmtj.mapper.WechatUserMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-21 12:37 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.interceptor
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Component
public class WeChatLoginInterceptor implements HandlerInterceptor {

    private final WechatUserMapper wechatUserMapper;
    private final StringRedisTemplate stringRedisTemplate;

    public WeChatLoginInterceptor(WechatUserMapper wechatUserMapper, StringRedisTemplate stringRedisTemplate) {
        this.wechatUserMapper = wechatUserMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        if (token == null) {
            throw new CymmtjException(ResultCode.UNLOGIN.getCode(), ResultMessage.UNLOGIN.getMessaage());
        } else {
            WechatUser wechatUser = wechatUserMapper.selectOneByToken(token);
            String resultToken = stringRedisTemplate.opsForValue().get(wechatUser.getOpenId());
            if (!Objects.equals(token, resultToken)) {
                throw new CymmtjException(ResultCode.UNLOGIN.getCode(), "登录超时");
            }
        }
        return true;
    }
}
