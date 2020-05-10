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
 * @Date : create by QingSong in 2020-05-10 16:51
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 文章评论中间表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-ArticleComment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleComment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 评论 ID
     */
    @ApiModelProperty(value = "评论 ID")
    private Integer commentId;
    /**
     * 文章 ID
     */
    @ApiModelProperty(value = "文章 ID")
    private Integer acticleId;
}