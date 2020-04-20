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
 * @Date : create by QingSong in 2020-04-20 7:14 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@ApiModel(value="org-gouzhong1223-cymmtj-pojo-CatRegion")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatRegion implements Serializable {
    /**
    * 区域 ID
    */
    @ApiModelProperty(value="区域 ID")
    private Integer regionId;

    /**
    * 猫咪 ID
    */
    @ApiModelProperty(value="猫咪 ID")
    private Integer catId;

    private static final long serialVersionUID = 1L;
}