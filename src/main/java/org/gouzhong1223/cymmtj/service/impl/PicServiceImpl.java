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

package org.gouzhong1223.cymmtj.service.impl;

import org.gouzhong1223.cymmtj.mapper.CatPicMapper;
import org.gouzhong1223.cymmtj.mapper.PicMapper;
import org.gouzhong1223.cymmtj.pojo.CatPic;
import org.gouzhong1223.cymmtj.pojo.Pic;
import org.gouzhong1223.cymmtj.service.PicService;
import org.gouzhong1223.cymmtj.util.OssUtil;
import org.gouzhong1223.cymmtj.util.RandomNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-18 10:58 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service.impl
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Service
@Transactional
public class PicServiceImpl implements PicService {

    private OssUtil ossUtil;
    private PicMapper picMapper;
    private CatPicMapper catPicMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(PicServiceImpl.class);


    @Autowired
    public PicServiceImpl(OssUtil ossUtil, PicMapper picMapper, CatPicMapper catPicMapper) {
        this.ossUtil = ossUtil;
        this.picMapper = picMapper;
        this.catPicMapper = catPicMapper;
    }

    @Override
    public List<Pic> insertPics(List<MultipartFile> files, Integer catId) {

        // 线程池执行结果
        ArrayList<Future<String>> futures = new ArrayList<>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        files.forEach(file -> {
            // 执行上传图片的任务并得到返回结果
            Future<String> submit = executorService.submit(() -> {
                LOGGER.info("开始上传图片");
                String uploadUrl = null;
                try {
                    uploadUrl = ossUtil.upload(file);
                } catch (Exception e) {
                    LOGGER.error("{}线程上传图片名为{}的图片失败", Thread.currentThread().getId(), file.getName());
                    e.printStackTrace();
                    return null;
                }
                return uploadUrl;
            });
            futures.add(submit);
        });

        ArrayList<Pic> pics = new ArrayList<>();
        futures.forEach(e -> {
            try {
                pics.add(new Pic(RandomNumber.createNumber(), e.get()));
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });

        try {
            LOGGER.info("开始把图片路径写入数据库");
            picMapper.insertList(pics);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("数据库写入图片路径失败");
            return null;
        }

        pics.forEach(e -> {
            try {
                catPicMapper.insertSelective(new CatPic(catId, e.getId()));
            } catch (Exception exception) {
                exception.printStackTrace();
                return;
            }
        });
        return pics;
    }

    @Override
    public String selectFirstPic(Integer id) {
        String picLink = picMapper.selectFirstLinkById(id);
        return picLink;
    }

    @Override
    public List<Pic> selectPicsByCatId(Integer id) {
        List<CatPic> catPics = catPicMapper.selectAllByCat_id(id);
        ArrayList<Pic> pics = new ArrayList<>();
        catPics.forEach(e -> {
            pics.add(picMapper.selectByPrimaryKey(e.getPic_id()));
        });
        return pics;
    }
}
