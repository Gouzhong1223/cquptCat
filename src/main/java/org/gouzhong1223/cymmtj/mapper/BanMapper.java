package org.gouzhong1223.cymmtj.mapper;

import org.gouzhong1223.cymmtj.pojo.Ban;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-18 6:31 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface BanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ban record);

    int insertSelective(Ban record);

    Ban selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ban record);

    int updateByPrimaryKey(Ban record);
}