package org.gouzhong1223.cymmtj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-06 15:02
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 微信用户点赞记录表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-PraiseWechatUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PraiseWechatUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 微信用户 openId
     */
    @ApiModelProperty(value = "微信用户 openId")
    private String openId;
    /**
     * 猫咪 ID
     */
    @ApiModelProperty(value = "猫咪 ID")
    private Integer catId;
    /**
     * 点赞时间
     */
    @ApiModelProperty(value = "点赞时间")
    private LocalDateTime praiseTime;

    public static PraiseWechatUserBuilder builder() {
        return new PraiseWechatUserBuilder();
    }
}