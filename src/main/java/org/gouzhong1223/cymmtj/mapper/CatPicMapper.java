package org.gouzhong1223.cymmtj.mapper;

import org.apache.ibatis.annotations.Param;import org.gouzhong1223.cymmtj.entity.CatPic;import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 16:56
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CatPicMapper {
    int insert(CatPic record);

    int insertSelective(CatPic record);

    int insertList(@Param("list") List<CatPic> list);

    List<CatPic> selectAllByCat_id(@Param("cat_id") Integer cat_id);
}