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

package org.gouzhong1223.cymmtj.service.impl;

import org.gouzhong1223.cymmtj.dto.rep.PopularCat;
import org.gouzhong1223.cymmtj.mapper.CatMapper;
import org.gouzhong1223.cymmtj.mapper.CatRegionMapper;
import org.gouzhong1223.cymmtj.mapper.PicMapper;
import org.gouzhong1223.cymmtj.mapper.RegionMapper;
import org.gouzhong1223.cymmtj.pojo.CatRegion;
import org.gouzhong1223.cymmtj.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : {@link RegionService}实现类 处理区域相关业务
 * @Date : create by QingSong in 2020-04-20 7:26 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service.impl
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Service
@Transactional
public class RegionServiceImpl implements RegionService {


    private RegionMapper regionMapper;
    private CatRegionMapper catRegionMapper;
    private CatMapper catMapper;
    private PicMapper picMapper;

    public RegionServiceImpl(RegionMapper regionMapper, CatRegionMapper catRegionMapper, CatMapper catMapper, PicMapper picMapper) {
        this.regionMapper = regionMapper;
        this.catRegionMapper = catRegionMapper;
        this.catMapper = catMapper;
        this.picMapper = picMapper;
    }

    @Override
    public ArrayList<PopularCat> selectCatsByRegionId(Integer regionId) {
        java.util.List<CatRegion> catRegions = catRegionMapper.selectAllByRegionId(regionId);
        ArrayList<PopularCat> popularCats = new ArrayList<>();
        catRegions.forEach(e -> {
            PopularCat popularCat = catMapper.selectIdAndNameAndCommontByPrimaryKey(e.getCatId());
            popularCat.setPicLink(picMapper.selectFirstLinkById(e.getCatId()));
            popularCats.add(popularCat);
        });
        return popularCats;
    }
}