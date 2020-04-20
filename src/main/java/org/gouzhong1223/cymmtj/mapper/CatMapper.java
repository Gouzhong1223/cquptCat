package org.gouzhong1223.cymmtj.mapper;

import org.gouzhong1223.cymmtj.dto.rep.PopularCat;

import org.apache.ibatis.annotations.Param;
import org.gouzhong1223.cymmtj.dto.rep.CatResponse;
import org.gouzhong1223.cymmtj.pojo.Cat;

import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-20 6:11 下午
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

    int insertList(@Param("list") List<Cat> list);

    List<Cat> selectAllCats();

    List<CatResponse> selectIdAndName();

    /**
     * 获取前四个最受欢迎的 Cat
     *
     * @return
     */
    List<PopularCat> selectIdAndNameAndCommontOrderByPraiseDesc();


    PopularCat selectIdAndNameAndCommontByPrimaryKey(@Param("id") Integer id);
}