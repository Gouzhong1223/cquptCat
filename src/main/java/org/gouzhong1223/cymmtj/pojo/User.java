package org.gouzhong1223.cymmtj.pojo;

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
 * @Date : create by QingSong in 2020-05-06 15:03
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 管理员信息表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-pojo-User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public static UserBuilder builder() {
        return new UserBuilder();
    }
}