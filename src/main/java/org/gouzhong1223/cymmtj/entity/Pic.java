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
 * 图片信息
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-entity-Pic")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pic implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 图片主键
     */
    @ApiModelProperty(value = "图片主键")
    private Integer id;
    /**
     * 图片链接
     */
    @ApiModelProperty(value = "图片链接")
    private String fileLink;
    /**
     * 图片 URI
     */
    @ApiModelProperty(value = "图片 URI")
    private String fileUri;
    /**
     * 图片上传时间
     */
    @ApiModelProperty(value = "图片上传时间")
    private LocalDateTime uploadTime;

    public static PicBuilder builder() {
        return new PicBuilder();
    }
}