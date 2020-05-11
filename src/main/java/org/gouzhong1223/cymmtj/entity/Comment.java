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
 * @Date : create by QingSong in 2020-05-10 18:12
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 评论详情
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-Comment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 评论主键
     */
    @ApiModelProperty(value = "评论主键")
    private Integer id;
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;
    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间")
    private LocalDateTime createTime;
    /**
     * 点赞数量
     */
    @ApiModelProperty(value = "点赞数量")
    private Integer awesomeCount;
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
     * 头像链接
     */
    @ApiModelProperty(value = "头像链接")
    private String avaterUrl;

    public static CommentBuilder builder() {
        return new CommentBuilder();
    }
}