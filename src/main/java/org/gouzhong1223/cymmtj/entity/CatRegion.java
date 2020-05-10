package org.gouzhong1223.cymmtj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 16:53
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 猫咪和猫咪活动区域的中间表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-CatRegion")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 猫咪 ID
     */
    @ApiModelProperty(value = "猫咪 ID")
    private Integer catId;
    /**
     * 区域ID
     */
    @ApiModelProperty(value = "区域ID")
    private Integer reginId;

    public static CatRegionBuilder builder() {
        return new CatRegionBuilder();
    }
}