package org.gouzhong1223.cymmtj.mapper;

import org.gouzhong1223.cymmtj.entity.AwesomeArticleWechatUser;

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
public interface AwesomeArticleWechatUserMapper {
    int insert(AwesomeArticleWechatUser record);

    int insertSelective(AwesomeArticleWechatUser record);
}