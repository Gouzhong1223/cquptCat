package org.gouzhong1223.cymmtj.mapper;

import org.apache.ibatis.annotations.Param;
import org.gouzhong1223.cymmtj.entity.ArticleComment;

import java.util.List;

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
public interface ArticleCommentMapper {
    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    List<ArticleComment> selectAllByActicleId(@Param("acticleId") Integer acticleId);

    int deleteByActicleIdAndCommentId(@Param("acticleId")Integer acticleId,@Param("commentId")Integer commentId);

    int deleteByActicleId(@Param("acticleId")Integer acticleId);

}