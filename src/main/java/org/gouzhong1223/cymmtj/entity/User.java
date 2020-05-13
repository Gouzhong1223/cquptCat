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
 * @Date : create by QingSong in 2020-05-13 19:35
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 管理员信息表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private Integer id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String password;
    /**
     * 用户创建时间
     */
    @ApiModelProperty(value = "用户创建时间")
    private LocalDateTime createTime;
    /**
     * 上次登录时间
     */
    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLoginTime;
    /**
     * 管理员 token
     */
    @ApiModelProperty(value = "管理员 token")
    private String token;

    public static UserBuilder builder() {
        return new UserBuilder();
    }
}