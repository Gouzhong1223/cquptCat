package org.gouzhong1223.cymmtj.mapper;

import org.gouzhong1223.cymmtj.entity.ArticleComment;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 
 * @Date : create by QingSong in 2020-05-10 16:51
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface ArticleCommentMapper {
    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);
}