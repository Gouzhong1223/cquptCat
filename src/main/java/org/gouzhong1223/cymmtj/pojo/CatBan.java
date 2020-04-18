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
 * @Date : create by QingSong in 2020-04-18 6:26 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.pojo
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@ApiModel(value="org-gouzhong1223-cymmtj-pojo-CatBan")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatBan implements Serializable {
    /**
    * 楼栋 id
    */
    @ApiModelProperty(value="楼栋 id")
    private Integer ban_id;

    /**
    * 猫咪 id
    */
    @ApiModelProperty(value="猫咪 id")
    private Integer cat_id;

    private static final long serialVersionUID = 1L;
}