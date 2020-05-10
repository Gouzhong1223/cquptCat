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
 * @Date : create by QingSong in 2020-05-10 16:51
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
/**
    * 文章图片中间表
    */
@ApiModel(value="org-gouzhong1223-cymmtj-entity-ArticlePic")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePic implements Serializable {
    /**
    * 图片 ID
    */
    @ApiModelProperty(value="图片 ID")
    private Integer picId;

    /**
    * 文章 ID
    */
    @ApiModelProperty(value="文章 ID")
    private Integer articleId;

    private static final long serialVersionUID = 1L;
}