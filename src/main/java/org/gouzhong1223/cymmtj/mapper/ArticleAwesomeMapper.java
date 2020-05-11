package org.gouzhong1223.cymmtj.mapper;

import org.apache.ibatis.annotations.Param;
import org.gouzhong1223.cymmtj.entity.ArticleAwesome;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-10 18:11
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface ArticleAwesomeMapper {
    int insert(ArticleAwesome record);

    int insertSelective(ArticleAwesome record);

    int deleteByArticleIdAndToken(@Param("articleId") Integer articleId, @Param("token") String token);

}