package org.gouzhong1223.cymmtj.pojo;

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
 * @Description : 楼栋实体类
 * @Date : create by QingSong in 2020-04-18 6:31 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-pojo-Ban")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ban implements Serializable {
    /**
     * 楼栋 id
     */
    @ApiModelProperty(value = "楼栋 id")
    private Integer id;

    /**
     * 楼栋名称
     */
    @ApiModelProperty(value = "楼栋名称")
    private String banName;

    private static final long serialVersionUID = 1L;

    public static BanBuilder builder() {
        return new BanBuilder();
    }
}