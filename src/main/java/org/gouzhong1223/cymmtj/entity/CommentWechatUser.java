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
 * @Date : create by QingSong in 2020-05-10 18:12
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 用户评论中间表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-CommentWechatUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentWechatUser implements Serializable {
    /**
     * 评论 id
     */
    @ApiModelProperty(value = "评论 id")
    private Integer commentId;

    /**
     * 微信用户 token
     */
    @ApiModelProperty(value = "微信用户 token")
    private String token;

    private static final long serialVersionUID = 1L;

    public static CommentWechatUserBuilder builder() {
        return new CommentWechatUserBuilder();
    }
}