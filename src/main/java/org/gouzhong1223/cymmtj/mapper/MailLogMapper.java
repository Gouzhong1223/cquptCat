package org.gouzhong1223.cymmtj.mapper;

import org.gouzhong1223.cymmtj.entity.MailLog;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 
 * @Date : create by QingSong in 2020-05-06 15:02
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.mapper
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface MailLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MailLog record);

    int insertSelective(MailLog record);

    MailLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MailLog record);

    int updateByPrimaryKey(MailLog record);
}