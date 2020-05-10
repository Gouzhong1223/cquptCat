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
 * 用户和文章点赞中间表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-ArticleAwesome")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAwesome implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 文章 ID
     */
    @ApiModelProperty(value = "文章 ID")
    private Integer articleId;
    /**
     * 微信用户 openId
     */
    @ApiModelProperty(value = "微信用户 openId")
    private String openId;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    public static ArticleAwesomeBuilder builder() {
        return new ArticleAwesomeBuilder();
    }
}