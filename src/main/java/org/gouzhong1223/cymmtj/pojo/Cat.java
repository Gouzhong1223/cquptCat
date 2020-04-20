/*
 *              Copyright 2020 By Gouzhong1223
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
 * @Date : create by QingSong in 2020-04-20 6:11 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-pojo-Cat")
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
     * 获赞数量
     */
    @ApiModelProperty(value = "获赞数量")
    private Integer praise;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String commont;

    private static final long serialVersionUID = 1L;

    public static CatBuilder builder() {
        return new CatBuilder();
    }
}