package org.gouzhong1223.cymmtj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 
 * @Date : create by QingSong in 2020-05-10 16:57
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
/**
    * 用户收藏猫咪中间表
    */
@ApiModel(value="org-gouzhong1223-cymmtj-entity-CollectWechatUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectWechatUser implements Serializable {
    /**
    * 猫咪 ID
    */
    @ApiModelProperty(value="猫咪 ID")
    private Integer catId;

    /**
    * 微信用户 openId
    */
    @ApiModelProperty(value="微信用户 openId")
    private String openId;

    private static final long serialVersionUID = 1L;
}