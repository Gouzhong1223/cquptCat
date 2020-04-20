package org.gouzhong1223.cymmtj.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 
 * @Date : create by QingSong in 2020-04-20 7:01 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@ApiModel(value="org-gouzhong1223-cymmtj-pojo-Region")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Region implements Serializable {
    /**
    * 区域
    */
    @ApiModelProperty(value="区域")
    private Integer id;

    /**
    * 区域名称
    */
    @ApiModelProperty(value="区域名称")
    private String banName;

    private static final long serialVersionUID = 1L;
}