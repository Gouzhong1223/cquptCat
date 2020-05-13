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

import org.gouzhong1223.cymmtj.entity.ArticlePic;
import org.gouzhong1223.cymmtj.entity.CatPic;
import org.gouzhong1223.cymmtj.entity.Pic;
import org.gouzhong1223.cymmtj.mapper.ArticlePicMapper;
import org.gouzhong1223.cymmtj.mapper.CatPicMapper;
import org.gouzhong1223.cymmtj.mapper.PicMapper;
import org.gouzhong1223.cymmtj.service.PicService;
import org.gouzhong1223.cymmtj.util.OssUtil;
import org.gouzhong1223.cymmtj.util.RandomNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : {@link PicService} 实现类
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PicServiceImpl.class);
    private final OssUtil ossUtil;
    private final PicMapper picMapper;
    private final CatPicMapper catPicMapper;
    private final ArticlePicMapper articlePicMapper;


    public PicServiceImpl(OssUtil ossUtil, PicMapper picMapper, CatPicMapper catPicMapper, ArticlePicMapper articlePicMapper) {
        this.ossUtil = ossUtil;
        this.picMapper = picMapper;
        this.catPicMapper = catPicMapper;
        this.articlePicMapper = articlePicMapper;
    }

    @Override
    public List<Pic> insertPics(List<MultipartFile> files, Integer catId, Integer articleId) {

        // 线程池执行结果
        ArrayList<Future<HashMap<String, String>>> futures = new ArrayList<>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        files.forEach(file -> {
            // 执行上传图片的任务并得到返回结果
            Future<HashMap<String, String>> submit = executorService.submit(() -> {
                LOGGER.info("开始上传图片");
                HashMap<String, String> resultMap = null;
                try {
                    resultMap = ossUtil.upload(file);
                } catch (Exception e) {
                    LOGGER.error("{}线程上传图片名为{}的图片失败", Thread.currentThread().getId(), file.getName());
                    e.printStackTrace();
                    return null;
                }
                return resultMap;
            });
            futures.add(submit);
        });

        ArrayList<Pic> pics = new ArrayList<>();
        futures.forEach(e -> {
            try {
                pics.add(new Pic(RandomNumber.createNumber(), e.get().get("link"), e.get().get("url"), LocalDateTime.now()));
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

        if (catId != null && articleId == null) {
            pics.forEach(e -> {
                catPicMapper.insertSelective(new CatPic(catId, e.getId()));
            });
        } else {
            pics.forEach(e -> {
                articlePicMapper.insertSelective(new ArticlePic(e.getId(), articleId));
            });
        }

        return pics;
    }

    @Override
    public String selectFirstPic(Integer id) {
        String picLink = picMapper.selectFirstLinkById(id);
        return picLink;
    }

    @Override
    public List<Pic> selectPicsByCatId(Integer id) {
        List<CatPic> catPics = catPicMapper.selectAllByCatId(id);
        ArrayList<Pic> pics = new ArrayList<>();
        catPics.forEach(e -> {
            pics.add(picMapper.selectByPrimaryKey(e.getPicId()));
        });
        return pics;
    }

}
