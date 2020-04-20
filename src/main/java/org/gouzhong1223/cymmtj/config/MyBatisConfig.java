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

package org.gouzhong1223.cymmtj.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description : MyBatisConfig
 * @Date : create by QingSong in 2020-02-01 5:55 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : com.gouzhong1223.blog.config
 * @ProjectName : blog
 * @Version : 1.0.0
 */
@Configuration
@MapperScan("org.gouzhong1223.cymmtj.mapper")
@Transactional
public class MyBatisConfig {
}
