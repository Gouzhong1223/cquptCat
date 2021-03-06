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

package org.gouzhong1223.cymmtj.service;

import org.gouzhong1223.cymmtj.common.CymmtjException;
import org.gouzhong1223.cymmtj.dto.rep.ResponseDto;
import org.gouzhong1223.cymmtj.dto.rep.ResultCat;
import org.gouzhong1223.cymmtj.entity.Cat;
import org.gouzhong1223.cymmtj.entity.Region;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-20 7:25 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface RegionService {

    /**
     * 根据区域 Id 查询活动猫咪
     *
     * @param regionId
     * @return
     */
    ArrayList<ResultCat> selectCatsByRegionId(Integer regionId);

    /**
     * 查询所有区域
     *
     * @return
     */
    List<Region> selectAllRegions();

    /**
     * 新增区域
     *
     * @param region {@link Region}
     */
    Region addRegion(Region region);

    /**
     * 根据 catId 查询所有的活动区域
     *
     * @param id {@link Cat#getId()}
     * @return {@link List<Region>} 和该 CatId 相关联的所有活动区域
     */
    List<Region> selectRegionsByCatId(Integer id);

    /**
     * 根据 id 删除区域
     *
     * @param id
     * @return
     */
    ResponseDto deleteRegion(Integer id);

    /**
     * 更新区域信息
     *
     * @param region
     * @return
     */
    ResponseDto updateRegion(Region region) throws CymmtjException;
}
