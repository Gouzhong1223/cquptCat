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
 * @Date : create by QingSong in 2020-05-10 18:11
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 微信用户点赞记录表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-AwesomeWechatUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AwesomeWechatUser implements Serializable {
    /**
     * 微信用户 token
     */
    @ApiModelProperty(value = "微信用户 token")
    private String token;

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

    private static final long serialVersionUID = 1L;

    public static AwesomeWechatUserBuilder builder() {
        return new AwesomeWechatUserBuilder();
    }
}