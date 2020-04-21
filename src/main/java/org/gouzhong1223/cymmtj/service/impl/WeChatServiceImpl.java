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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.mapper.WechatUserMapper;
import org.gouzhong1223.cymmtj.pojo.WechatUser;
import org.gouzhong1223.cymmtj.service.WeChatService;
import org.gouzhong1223.cymmtj.util.WechatUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-21 12:07 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service.impl
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Service
public class WeChatServiceImpl implements WeChatService {


    private WechatUserMapper wechatUserMapper;

    public WeChatServiceImpl(WechatUserMapper wechatUserMapper) {
        this.wechatUserMapper = wechatUserMapper;
    }

    @Override
    public ResponseDto login(String code, String rawData, String signature, String encrypteData, String iv) {

        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return new ResponseDto(500, "签名校验失败", null);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        WechatUser user = this.wechatUserMapper.selectByPrimaryKey(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");
            String city = rawDataJson.getString("city");
            String country = rawDataJson.getString("country");
            String province = rawDataJson.getString("province");

            user = new WechatUser();
            user.setOpenid(openid);
            user.setSkey(skey);
            user.setCreatetime(LocalDateTime.now());
            user.setLastvisittime(LocalDateTime.now());
            user.setSessionkey(sessionKey);
            user.setCity(city);
            user.setProvince(province);
            user.setCountry(country);
            user.setAvatarurl(avatarUrl);
            user.setGender(Integer.parseInt(gender));
            user.setNickname(nickName);

            this.wechatUserMapper.insertSelective(user);
        } else {
            // 已存在，更新用户登录时间
            user.setLastvisittime(LocalDateTime.now());
            // 重新设置会话skey
            user.setSkey(skey);
            this.wechatUserMapper.updateByPrimaryKeySelective(user);
        }
        // encrypteData比rowData多了appid和openid
        // JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        // 6. 把新的skey返回给小程序
        ResponseDto result = new ResponseDto(200, null, skey);
        return result;
    }

    @Override
    public WechatUser selectUserBySkey(String skey) {
        WechatUser wechatUser = wechatUserMapper.selectBySkey(skey);
        return wechatUser;
    }

}
