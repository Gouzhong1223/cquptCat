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

package org.gouzhong1223.cymmtj.dto.rep;

import lombok.Getter;
import lombok.Setter;
import org.gouzhong1223.cymmtj.entity.Cat;
import org.gouzhong1223.cymmtj.entity.Pic;
import org.gouzhong1223.cymmtj.entity.Region;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 猫咪详情，附带图片，评论数，评论，用户是否已经点赞，用户是否已经收藏，猫咪所属区域
 * @Date : create by QingSong in 2020-05-11 21:39
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.dto.rep
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Getter
@Setter
public class CatDetailRep extends Cat {
    private List<Pic> pics;
    private Integer commentCount;
    private List<CommentRep> commentReps;
    private Boolean awesome;
    private Boolean collect;
    private List<Region> regions;

    public CatDetailRep(List<Pic> pics, Integer commentCount, List<CommentRep> commentReps, Boolean awesome, Boolean collect, List<Region> regions) {
        this.pics = pics;
        this.commentCount = commentCount;
        this.commentReps = commentReps;
        this.awesome = awesome;
        this.collect = collect;
        this.regions = regions;
    }

    public CatDetailRep(Integer id, String name, String color, String sex, String foreignTrade, String character, LocalDateTime updateTime, String type, Integer visible, String referrer, Integer audit, LocalDateTime createTime, Integer awesomeCount, Integer collectCount, List<Pic> pics, Integer commentCount, List<CommentRep> commentReps, Boolean awesome, Boolean collect, List<Region> regions) {
        super(id, name, color, sex, foreignTrade, character, updateTime, type, visible, referrer, audit, createTime, awesomeCount, collectCount);
        this.pics = pics;
        this.commentCount = commentCount;
        this.commentReps = commentReps;
        this.awesome = awesome;
        this.collect = collect;
        this.regions = regions;
    }
}
