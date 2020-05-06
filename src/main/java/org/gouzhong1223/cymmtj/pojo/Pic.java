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
 * 图片信息
 */
@ApiModel(value = "org-gouzhong1223-cymmtj-pojo-Pic")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pic implements Serializable {
    /**
     * 图片主键
     */
    @ApiModelProperty(value = "图片主键")
    private Integer id;

    /**
     * 图片链接
     */
    @ApiModelProperty(value = "图片链接")
    private String fileLink;

    /**
     * 图片 URI
     */
    @ApiModelProperty(value = "图片 URI")
    private String fileUri;

    /**
     * 图片上传时间
     */
    @ApiModelProperty(value = "图片上传时间")
    private LocalDateTime uploadTime;

    private static final long serialVersionUID = 1L;

    public static PicBuilder builder() {
        return new PicBuilder();
    }
}