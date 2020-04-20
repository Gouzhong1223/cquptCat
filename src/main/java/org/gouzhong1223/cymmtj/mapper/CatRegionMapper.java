package org.gouzhong1223.cymmtj.mapper;

import org.gouzhong1223.cymmtj.pojo.CatRegion;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 
 * @Date : create by QingSong in 2020-04-20 7:14 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CatRegionMapper {
    int insert(CatRegion record);

    int insertSelective(CatRegion record);
}