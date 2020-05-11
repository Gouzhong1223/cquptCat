package org.gouzhong1223.cymmtj.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.gouzhong1223.cymmtj.entity.AwesomeCommentWechatUser;

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
public interface AwesomeCommentWechatUserMapper {
    int insert(AwesomeCommentWechatUser record);

    int insertSelective(AwesomeCommentWechatUser record);

    List<AwesomeCommentWechatUser> selectAllByToken(@Param("token")String token);

    int deleteByCommentIdAndToken(@Param("commentId")Integer commentId,@Param("token")String token);




}