package org.gouzhong1223.cymmtj.mapper;

import org.apache.ibatis.annotations.Param;
import org.gouzhong1223.cymmtj.entity.AwesomeCatWechatUser;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-11 15:37
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface AwesomeCatWechatUserMapper {
    int insert(AwesomeCatWechatUser record);

    int insertSelective(AwesomeCatWechatUser record);

    AwesomeCatWechatUser selectOneByCatIdAndToken(@Param("catId") Integer catId, @Param("token") String token);

    int deleteByCatIdAndToken(@Param("catId") Integer catId, @Param("token") String token);


}