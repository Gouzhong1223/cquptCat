package org.gouzhong1223.cymmtj.mapper;

import org.apache.ibatis.annotations.Param;import org.gouzhong1223.cymmtj.pojo.PraiseWechatUser;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-06 15:02
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface PraiseWechatUserMapper {
    int insert(PraiseWechatUser record);

    int insertSelective(PraiseWechatUser record);

    int deleteByCatIdAndOpenId(@Param("catId") Integer catId, @Param("openId") String openId);
}