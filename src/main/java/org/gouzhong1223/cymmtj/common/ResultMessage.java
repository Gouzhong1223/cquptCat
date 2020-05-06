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

package org.gouzhong1223.cymmtj.common;

import lombok.Getter;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : ResultMessage
 * @Date : create by QingSong in 2020-02-01 8:05 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : com.gouzhong1223.blog.common
 * @ProjectName : blog
 * @Version : 1.0.0
 */
@Getter
public enum ResultMessage {

    SUCCESS("操作成功！"),
    FAIL("操作失败!"),
    VALUE_NULL("数据为空"),
    ISEXIST("存在同名标签或者分类"),
    LOGINFAIL("登录失败"),
    UNLOGIN("未登录"),
    SERVER("服务器打瞌睡啦！"),
    EMAILCANNOTBEEMPTY("邮箱不能为空"),
    EMAILFORMATISINCORRECT("邮箱格式不正确"),
    UPLOADIMAGEFAILED("上传图片失败！")
    ;

    private String messaage;

    ResultMessage(String messaage) {
        this.messaage = messaage;
    }
}
