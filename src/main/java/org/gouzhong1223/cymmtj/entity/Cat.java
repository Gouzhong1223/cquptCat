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
 * @Date : create by QingSong in 2020-05-06 15:01
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 猫咪信息
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-Cat")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cat implements Serializable {
    /**
     * 猫咪主键
     */
    @ApiModelProperty(value = "猫咪主键")
    private Integer id;

    /**
     * 猫咪名字
     */
    @ApiModelProperty(value = "猫咪名字")
    private String name;

    /**
     * 猫咪毛色
     */
    @ApiModelProperty(value = "猫咪毛色")
    private String color;

    /**
     * 猫咪性别
     */
    @ApiModelProperty(value = "猫咪性别")
    private String sex;

    /**
     * 猫咪外貌
     */
    @ApiModelProperty(value = "猫咪外貌")
    private String foreignTrade;

    /**
     * 猫咪性格
     */
    @ApiModelProperty(value = "猫咪性格")
    private String character;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 猫咪所属分类
     */
    @ApiModelProperty(value = "猫咪所属分类")
    private String type;

    /**
     * 是否可见 1-可见 0-不可见
     */
    @ApiModelProperty(value = "是否可见 1-可见 0-不可见")
    private Integer visible;

    /**
     * 推荐人昵称
     */
    @ApiModelProperty(value = "推荐人昵称")
    private String referrer;

    /**
     * 是否已经审核 1-是 0-否
     */
    @ApiModelProperty(value = "是否已经审核 1-是 0-否")
    private Integer audit;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;

    public static CatBuilder builder() {
        return new CatBuilder();
    }
}