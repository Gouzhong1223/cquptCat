package org.gouzhong1223.cymmtj.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.gouzhong1223.cymmtj.pojo.Cat;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 
 * @Date : create by QingSong in 2020-04-18 6:18 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cat record);

    int insertSelective(Cat record);

    Cat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cat record);

    int updateByPrimaryKey(Cat record);

    int insertList(@Param("list")List<Cat> list);


}