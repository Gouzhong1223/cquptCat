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
 * @Date : create by QingSong in 2020-05-11 17:15
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 文章详情表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-Article")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    /**
     * 文章主键
     */
    @ApiModelProperty(value = "文章主键")
    private Integer id;

    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容")
    private String context;

    /**
     * 点赞数量
     */
    @ApiModelProperty(value = "点赞数量")
    private Integer awesomeCount;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 微信用户 token
     */
    @ApiModelProperty(value = "微信用户 token")
    private String token;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 用户头像 URL
     */
    @ApiModelProperty(value = "用户头像 URL")
    private String avatarUrl;

    /**
     * 评论数
     */
    @ApiModelProperty(value = "评论数")
    private Integer commentCount;

    private static final long serialVersionUID = 1L;

    public static ArticleBuilder builder() {
        return new ArticleBuilder();
    }
}