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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : Pic实体类
 * @Date : create by QingSong in 2020-04-18 6:26 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-pojo-Pic")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pic implements Serializable {
    /**
     * 图片主键
     */
    @ApiModelProperty(value = "图片主键")
    private Integer id;

    /**
     * 图片链接
     */
    @ApiModelProperty(value = "图片链接")
    private String link;

    private static final long serialVersionUID = 1L;
}