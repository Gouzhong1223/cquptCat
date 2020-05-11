package org.gouzhong1223.cymmtj.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.gouzhong1223.cymmtj.entity.AwesomeArticleWechatUser;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-11 16:55
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface AwesomeArticleWechatUserMapper {
    int insert(AwesomeArticleWechatUser record);

    int insertSelective(AwesomeArticleWechatUser record);

    int deleteByArticleIdAndToken(@Param("articleId")Integer articleId,@Param("token")String token);

    List<AwesomeArticleWechatUser> selectAllByToken(@Param("token")String token);



}