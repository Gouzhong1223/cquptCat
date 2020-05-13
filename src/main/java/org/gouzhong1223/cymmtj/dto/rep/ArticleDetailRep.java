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

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : 文章详情（附带评论，点赞数，评论数）
 * @Date : create by QingSong in 2020-05-11 18:32
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.dto.rep
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Getter
@Setter
public class ArticleDetailRep extends ArticleRep {
    private List<CommentRep> commentRepList;

    public ArticleDetailRep(Integer id, String context, Integer awesomeCount, LocalDateTime createTime, String token, String nickName, String avatarUrl, Integer commentCount, Boolean awesome, List<CommentRep> commentRepList) {
        super(id, context, awesomeCount, createTime, token, nickName, avatarUrl, commentCount, awesome);
        this.commentRepList = commentRepList;
    }

    public ArticleDetailRep(Boolean awesome, List<CommentRep> commentRepList) {
        super(awesome);
        this.commentRepList = commentRepList;
    }
}
