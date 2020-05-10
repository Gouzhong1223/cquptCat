package org.gouzhong1223.cymmtj.mapper;

import org.apache.ibatis.annotations.Param;
import org.gouzhong1223.cymmtj.entity.CatRegion;

import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 16:53
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CatRegionMapper {
    int insert(CatRegion record);

    int insertSelective(CatRegion record);

    List<CatRegion> selectAllByRegionId(@Param("regionId") Integer regionId);

    List<CatRegion> selectAll();

    List<CatRegion> selectAllByCatId(@Param("catId") Integer catId);
}