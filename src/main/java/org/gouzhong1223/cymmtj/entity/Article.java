package org.gouzhong1223.cymmtj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 16:55
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
    private static final long serialVersionUID = 1L;
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
     * 微信用户 openID
     */
    @ApiModelProperty(value = "微信用户 openID")
    private String openId;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    public static ArticleBuilder builder() {
        return new ArticleBuilder();
    }
}