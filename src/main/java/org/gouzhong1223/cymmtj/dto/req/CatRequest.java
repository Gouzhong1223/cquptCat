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

package org.gouzhong1223.cymmtj.dto.req;

import io.swagger.annotations.ApiModelProperty;
import org.gouzhong1223.cymmtj.pojo.Cat;
import org.gouzhong1223.cymmtj.pojo.Region;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 添加 Cat 请求参数
 * @Date : create by QingSong in 2020-04-18 7:45 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.dto.req
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public class CatRequest extends Cat {

    /**
     * 猫咪楼栋
     */
    @ApiModelProperty(value = "猫咪楼栋")
    private List<Region> regions;

    public CatRequest(List<Region> regions) {
        this.regions = regions;
    }

    public CatRequest(Integer id, String name, String color, String sex, String foreignTrade, String character, LocalDateTime updateTime, String type, Integer praise, String commont, List<Region> regions) {
        super(id, name, color, sex, foreignTrade, character, updateTime, type, praise, commont);
        this.regions = regions;
    }

    @Override
    public String toString() {
        return "CatRequest{" +
                "regions=" + regions +
                '}';
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
}
