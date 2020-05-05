package org.gouzhong1223.cymmtj.pojo;

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
 * @Date : create by QingSong in 2020-05-05 19:05
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-pojo-PraiseWechatUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PraiseWechatUser implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public static PraiseWechatUserBuilder builder() {
        return new PraiseWechatUserBuilder();
    }
}