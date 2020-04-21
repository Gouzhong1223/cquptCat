package org.gouzhong1223.cymmtj.mapper;

import org.gouzhong1223.cymmtj.pojo.WechatUser;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-21 11:24 上午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface WechatUserMapper {
    int deleteByPrimaryKey(String open_id);

    int insert(WechatUser record);

    int insertSelective(WechatUser record);

    WechatUser selectByPrimaryKey(String open_id);

    int updateByPrimaryKeySelective(WechatUser record);

    int updateByPrimaryKey(WechatUser record);
}