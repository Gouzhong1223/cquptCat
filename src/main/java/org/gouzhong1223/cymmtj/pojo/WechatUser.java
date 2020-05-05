package org.gouzhong1223.cymmtj.pojo;

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
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 微信用户信息
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-pojo-WechatUser")
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