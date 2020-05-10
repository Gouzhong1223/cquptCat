package org.gouzhong1223.cymmtj.mapper;
import org.apache.ibatis.annotations.Param;

import org.gouzhong1223.cymmtj.entity.CollectWechatUser;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 18:12
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface CollectWechatUserMapper {
    int insert(CollectWechatUser record);

    int insertSelective(CollectWechatUser record);

    int deleteByCatIdAndToken(@Param("catId")Integer catId,@Param("token")String token);


}