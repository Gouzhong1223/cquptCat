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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gouzhong1223.cymmtj.common.PageResult;
import org.gouzhong1223.cymmtj.dto.rep.CatResponse;
import org.gouzhong1223.cymmtj.dto.rep.ResultCat;
import org.gouzhong1223.cymmtj.mapper.CatMapper;
import org.gouzhong1223.cymmtj.pojo.Cat;
import org.gouzhong1223.cymmtj.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : {@link CatService} 实现类，处理 Cat 增删改查业务
 * @Date : create by QingSong in 2020-04-18 8:05 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service.impl
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Service
public class CatServiceImpl implements CatService {


    @Autowired
    private CatMapper catMapper;

    @Override
    public void insertOrUpdateCat(Cat cat) {
        cat.setUpdateTime(LocalDateTime.now());
        int i = catMapper.insertSelective(cat);
    }

    @Override
    public PageResult<ResultCat> pagingListCat(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ResultCat> resultCats = catMapper.selectIdAndNameAndCommont();
        PageInfo<ResultCat> resultCatPageInfo = new PageInfo<>(resultCats);
        return new PageResult<>(resultCatPageInfo.getPageNum(), resultCatPageInfo.getPageSize(), resultCatPageInfo.getTotal(), resultCatPageInfo.getPages(), resultCatPageInfo.getList());
    }

    @Override
    public PageResult<CatResponse> selectCatIdAndName(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<ResultCat> selectPopularCats() {
        List<ResultCat> resultCats = catMapper.selectIdAndNameAndCommontOrderByPraiseDesc();
        return resultCats;
    }

    @Override
    public Cat selectCatByid(Integer id) {
        return catMapper.selectByPrimaryKey(id);
    }


}
