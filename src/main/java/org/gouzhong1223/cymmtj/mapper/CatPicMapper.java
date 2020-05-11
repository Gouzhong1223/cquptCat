package org.gouzhong1223.cymmtj.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.gouzhong1223.cymmtj.entity.CatPic;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 
 * @Date : create by QingSong in 2020-05-11 22:23
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CatPicMapper {
    int insert(CatPic record);

    int insertSelective(CatPic record);

    List<CatPic> selectAllByCatId(@Param("catId")Integer catId);


}