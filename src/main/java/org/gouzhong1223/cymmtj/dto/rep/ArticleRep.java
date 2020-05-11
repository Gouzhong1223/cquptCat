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

import lombok.*;
import org.gouzhong1223.cymmtj.entity.Article;

import java.time.LocalDateTime;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-05-11 16:34
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.cymmtj.dto.rep
 * @ProjectName : cymmtj
 * @Version : 1.0.0
 */
@Getter
@Setter
public class ArticleRep extends Article {

    private Boolean awesome;

    public ArticleRep(Integer id, String context, Integer awesomeCount, LocalDateTime createTime, String token, String nickName, String avatarUrl, Integer commentCount, Boolean awesome) {
        super(id, context, awesomeCount, createTime, token, nickName, avatarUrl, commentCount);
        this.awesome = awesome;
    }

    public ArticleRep(Boolean awesome) {
        this.awesome = awesome;
    }
}
