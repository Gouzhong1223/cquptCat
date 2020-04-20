package org.gouzhong1223.cymmtj.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

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

    List<CatRegion> selectAllByRegionId(@Param("regionId")Integer regionId);

    List<CatRegion> selectAll();

    List<CatRegion> selectAllByCatId(@Param("catId")Integer catId);


}