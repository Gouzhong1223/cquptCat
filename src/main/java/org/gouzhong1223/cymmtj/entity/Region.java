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
 * @Date : create by QingSong in 2020-05-06 15:02
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.entity
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */

/**
 * 区域信息表
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-Region")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Region implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 区域 ID
     */
    @ApiModelProperty(value = "区域 ID")
    private Integer id;
    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String regionName;
    /**
     * 区域创建时间
     */
    @ApiModelProperty(value = "区域创建时间")
    private LocalDateTime createTime;
    /**
     * 区域更新时间
     */
    @ApiModelProperty(value = "区域更新时间")
    private LocalDateTime updateTime;

    public static RegionBuilder builder() {
        return new RegionBuilder();
    }
}