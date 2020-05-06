package org.gouzhong1223.cymmtj.mapper;

import org.apache.ibatis.annotations.Param;import org.gouzhong1223.cymmtj.entity.WechatUser;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-05 18:19
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface WechatUserMapper {
    int deleteByPrimaryKey(String openId);

    int insert(WechatUser record);

    int insertSelective(WechatUser record);

    WechatUser selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(WechatUser record);

    int updateByPrimaryKey(WechatUser record);

    WechatUser selectBySkey(@Param("skey") String skey);

    WechatUser selectOneByOpenId(@Param("openId")String openId);


}