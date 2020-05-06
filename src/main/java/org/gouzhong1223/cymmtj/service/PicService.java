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

package org.gouzhong1223.cymmtj.service;

import org.gouzhong1223.cymmtj.entity.Cat;
import org.gouzhong1223.cymmtj.entity.Pic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-04-18 10:56 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.service
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
public interface PicService {

    /**
     * 插入多张图片
     *
     * @param files {@link MultipartFile} 文件
     * @param catId 猫咪 Id
     * @return {@link Pic}
     */
    List<Pic> insertPics(List<MultipartFile> files, Integer catId);

    /**
     * 根据 Cat ID 查询所属第一张图片
     *
     * @param id
     * @return
     */
    String selectFirstPic(Integer id);

    /**
     * 根据 CatId 查询所有的图片
     *
     * @param id {@link Cat#getId()}
     * @return {@link List<Pic>} 该 ID 相关联的所有图片
     */
    List<Pic> selectPicsByCatId(Integer id);
}
