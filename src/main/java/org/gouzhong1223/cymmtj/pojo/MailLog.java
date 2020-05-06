package org.gouzhong1223.cymmtj.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 
 * @Date : create by QingSong in 2020-05-06 15:02
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
/**
    * 邮件发送日志
    */
@ApiModel(value="org-gouzhong1223-cymmtj-pojo-MailLog")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailLog implements Serializable {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 发送人
    */
    @ApiModelProperty(value="发送人")
    private String sendFrom;

    /**
    * 收件人
    */
    @ApiModelProperty(value="收件人")
    private String sendTo;

    /**
    * 发送时间
    */
    @ApiModelProperty(value="发送时间")
    private LocalDateTime sendTime;

    /**
    * 邮件标题
    */
    @ApiModelProperty(value="邮件标题")
    private String subject;

    /**
    * 邮件正文
    */
    @ApiModelProperty(value="邮件正文")
    private String content;

    private static final long serialVersionUID = 1L;
}