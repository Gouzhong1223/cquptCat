/*
 *              Copyright 2020 By Gouzhong1223
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.gouzhong1223.cymmtj.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.gouzhong1223.cymmtj.config.OssConfig;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : Oss上传工具
 * @Date : create by QingSong in 2020-04-18 10:10 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.util
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Component
public class OssUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OssUtil.class);

    @Autowired
    private OssConfig ossConfig;

    public String upload(MultipartFile file) {
        logger.info("=========>OSS文件上传开始：" + file.getName());
        String endpoint = ossConfig.getALIYUN_OSS_ENDPOINT();
        String accessKeyId = ossConfig.getALIYUN_OSS_ACCESSKEYID();
        String accessKeySecret = ossConfig.getALIYUN_OSS_ACCESSKEYSECRET();
        String bucketName = ossConfig.getBUCKET_NAME();
        String fileHost = ossConfig.getFILE_HOST();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        if (null == file) {
            return null;
        }

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            //容器不存在，就创建
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file.getInputStream()));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (null != result) {
                logger.info("==========>OSS文件上传成功,OSS地址：" + fileUrl);
                return fileUrl;
            }
        } catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }

}
