package org.gouzhong1223.cymmtj.mapper;

import org.gouzhong1223.cymmtj.pojo.CatPic;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 
 * @Date : create by QingSong in 2020-04-18 6:26 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CatPicMapper {
    int insert(CatPic record);

    int insertSelective(CatPic record);
}