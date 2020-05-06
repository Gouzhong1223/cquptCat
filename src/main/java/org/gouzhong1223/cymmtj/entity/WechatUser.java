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

package org.gouzhong1223.cymmtj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-05 18:19
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 微信用户信息
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-WechatUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WechatUser implements Serializable {
    /**
     * open_id
     */
    @ApiModelProperty(value = "open_id")
    private String openId;

    /**
     * skey
     */
    @ApiModelProperty(value = "skey")
    private String skey;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastVisitTime;

    /**
     * session_key
     */
    @ApiModelProperty(value = "session_key")
    private String sessionKey;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 国
     */
    @ApiModelProperty(value = "国")
    private String country;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;

    /**
     * 网名
     */
    @ApiModelProperty(value = "网名")
    private String nickName;

    private static final long serialVersionUID = 1L;

    public static WechatUserBuilder builder() {
        return new WechatUserBuilder();
    }
}