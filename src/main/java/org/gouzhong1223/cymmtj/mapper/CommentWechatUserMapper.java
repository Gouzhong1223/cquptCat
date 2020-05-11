package org.gouzhong1223.cymmtj.mapper;

import org.apache.ibatis.annotations.Param;
import org.gouzhong1223.cymmtj.entity.CommentWechatUser;

import java.util.List;

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
public interface CommentWechatUserMapper {
    int insert(CommentWechatUser record);

    int insertSelective(CommentWechatUser record);

    int deleteByCommentIdAndToken(@Param("commentId") Integer commentId, @Param("token") String token);

    List<CommentWechatUser> selectAllByToken(@Param("token") String token);

}