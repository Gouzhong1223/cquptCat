package org.gouzhong1223.cymmtj.mapper;

import org.gouzhong1223.cymmtj.entity.AwesomeWechatUser;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 16:56
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface AwesomeWechatUserMapper {
    int insert(AwesomeWechatUser record);

    int insertSelective(AwesomeWechatUser record);
}