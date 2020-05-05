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
 * @Date : create by QingSong in 2020-05-05 21:34
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@ApiModel(value="org-gouzhong1223-cymmtj-pojo-CatRefrrer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatRefrrer implements Serializable {
    /**
    * 猫咪 ID
    */
    @ApiModelProperty(value="猫咪 ID")
    private Integer catId;

    /**
    * 推荐人邮箱
    */
    @ApiModelProperty(value="推荐人邮箱")
    private String referrerEmail;

    private static final long serialVersionUID = 1L;
}