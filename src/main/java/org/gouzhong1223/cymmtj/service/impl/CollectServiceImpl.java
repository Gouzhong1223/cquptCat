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

import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.common.ResultCode;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.entity.CollectWechatUser;
import org.gouzhong1223.cymmtj.entity.WechatUser;
import org.gouzhong1223.cymmtj.mapper.CatMapper;
import org.gouzhong1223.cymmtj.mapper.CollectWechatUserMapper;
import org.gouzhong1223.cymmtj.mapper.WechatUserMapper;
import org.gouzhong1223.cymmtj.service.CollectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 20:48
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service.impl
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Service
@Transactional
public class CollectServiceImpl implements CollectService {

    private final CatMapper catMapper;
    private final CollectWechatUserMapper collectWechatUserMapper;
    private final WechatUserMapper wechatUserMapper;

    public CollectServiceImpl(CatMapper catMapper, CollectWechatUserMapper collectWechatUserMapper, WechatUserMapper wechatUserMapper) {
        this.catMapper = catMapper;
        this.collectWechatUserMapper = collectWechatUserMapper;
        this.wechatUserMapper = wechatUserMapper;
    }

    @Override
    public ResponseDto collect(Integer catId, String token) throws CymmtjException {
        WechatUser wechatUser = wechatUserMapper.selectOneByToken(token);
        catMapper.collect(catId);
        try {
            collectWechatUserMapper.insertSelective(new CollectWechatUser(catId, wechatUser.getToken()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CymmtjException(ResultCode.FAIL.getCode(), "收藏失败！");
        }
        return ResponseDto.SUCCESS();
    }
}
