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
 * @Date : create by QingSong in 2020-05-10 16:56
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 猫咪和猫咪图片的中间表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-CatPic")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatPic implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 图片主键
     */
    @ApiModelProperty(value = "图片主键")
    private Integer pic_id;
    /**
     * 猫咪 id
     */
    @ApiModelProperty(value = "猫咪 id")
    private Integer cat_id;

    public static CatPicBuilder builder() {
        return new CatPicBuilder();
    }
}