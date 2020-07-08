/*
 Navicat Premium Data Transfer

 Source Server         : 本地 Docker 数据库
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : cymmtj

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 08/07/2020 14:49:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章主键',
  `context` varchar(50) NOT NULL DEFAULT '' COMMENT '文章内容',
  `awesomeCount` int(11) NOT NULL DEFAULT '-1' COMMENT '点赞数量',
  `createTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT '微信用户 token',
  `nickName` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatarUrl` varchar(50) NOT NULL DEFAULT '' COMMENT '用户头像 URL',
  `commentCount` int(11) NOT NULL DEFAULT '-1' COMMENT '评论数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_article';

-- ----------------------------
-- Table structure for tb_article_awesome
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_awesome`;
CREATE TABLE `tb_article_awesome` (
  `articleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章 ID',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT '微信用户 token',
  `nickName` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_article_awesome';

-- ----------------------------
-- Table structure for tb_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_comment`;
CREATE TABLE `tb_article_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论 ID',
  `acticleId` int(11) NOT NULL DEFAULT '-1' COMMENT '文章 ID',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_article_comment';

-- ----------------------------
-- Table structure for tb_article_pic
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_pic`;
CREATE TABLE `tb_article_pic` (
  `picId` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片 ID',
  `articleId` int(11) NOT NULL DEFAULT '-1' COMMENT '文章 ID',
  PRIMARY KEY (`picId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_article_pic';

-- ----------------------------
-- Table structure for tb_awesome_article_wechatUser
-- ----------------------------
DROP TABLE IF EXISTS `tb_awesome_article_wechatUser`;
CREATE TABLE `tb_awesome_article_wechatUser` (
  `articleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章 ID',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT '微信用户 token',
  `nickName` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_awesome_article_wechatUser';

-- ----------------------------
-- Table structure for tb_awesome_cat_wechatUser
-- ----------------------------
DROP TABLE IF EXISTS `tb_awesome_cat_wechatUser`;
CREATE TABLE `tb_awesome_cat_wechatUser` (
  `token` varchar(50) NOT NULL COMMENT '微信用户 token',
  `catId` int(11) NOT NULL DEFAULT '-1' COMMENT '猫咪 ID',
  `praiseTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '点赞时间',
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_awesome_cat_wechatUser';

-- ----------------------------
-- Table structure for tb_awesome_comment_wechatUser
-- ----------------------------
DROP TABLE IF EXISTS `tb_awesome_comment_wechatUser`;
CREATE TABLE `tb_awesome_comment_wechatUser` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论 ID',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT '微信用户 token',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_awesome_comment_wechatUser';

-- ----------------------------
-- Table structure for tb_awesome_wechatUser
-- ----------------------------
DROP TABLE IF EXISTS `tb_awesome_wechatUser`;
CREATE TABLE `tb_awesome_wechatUser` (
  `token` varchar(50) NOT NULL COMMENT '微信用户 token',
  `catId` int(11) NOT NULL DEFAULT '-1' COMMENT '猫咪 ID',
  `praiseTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '点赞时间',
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_awesome_wechatUser';

-- ----------------------------
-- Table structure for tb_cat
-- ----------------------------
DROP TABLE IF EXISTS `tb_cat`;
CREATE TABLE `tb_cat` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '猫咪主键',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '猫咪名字',
  `color` varchar(50) NOT NULL DEFAULT '' COMMENT '猫咪毛色',
  `sex` varchar(50) NOT NULL DEFAULT '' COMMENT '猫咪性别',
  `foreignTrade` varchar(50) NOT NULL DEFAULT '' COMMENT '猫咪外貌',
  `character` varchar(50) NOT NULL DEFAULT '' COMMENT '猫咪性格',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` varchar(50) NOT NULL DEFAULT '' COMMENT '猫咪所属分类',
  `visible` int(11) NOT NULL DEFAULT '-1' COMMENT '是否可见 1-可见 0-不可见',
  `referrer` varchar(50) NOT NULL DEFAULT '' COMMENT '推荐人昵称',
  `audit` int(11) NOT NULL DEFAULT '-1' COMMENT '是否已经审核 1-是 0-否',
  `createTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `awesomeCount` int(11) NOT NULL DEFAULT '-1' COMMENT '点赞数',
  `collectCount` int(11) NOT NULL DEFAULT '-1' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_cat';

-- ----------------------------
-- Table structure for tb_cat_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_cat_comment`;
CREATE TABLE `tb_cat_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论 Id',
  `catId` int(11) NOT NULL DEFAULT '-1' COMMENT '猫咪 Id',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_cat_comment';

-- ----------------------------
-- Table structure for tb_cat_pic
-- ----------------------------
DROP TABLE IF EXISTS `tb_cat_pic`;
CREATE TABLE `tb_cat_pic` (
  `picId` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片主键',
  `catId` int(11) NOT NULL DEFAULT '-1' COMMENT '猫咪 id',
  PRIMARY KEY (`picId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_cat_pic';

-- ----------------------------
-- Table structure for tb_cat_refrrer
-- ----------------------------
DROP TABLE IF EXISTS `tb_cat_refrrer`;
CREATE TABLE `tb_cat_refrrer` (
  `catId` int(11) NOT NULL AUTO_INCREMENT COMMENT '猫咪 ID',
  `referrerEmail` varchar(50) NOT NULL DEFAULT '' COMMENT '推荐人邮箱',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT '推荐人 token',
  PRIMARY KEY (`catId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_cat_refrrer';

-- ----------------------------
-- Table structure for tb_cat_region
-- ----------------------------
DROP TABLE IF EXISTS `tb_cat_region`;
CREATE TABLE `tb_cat_region` (
  `catId` int(11) NOT NULL AUTO_INCREMENT COMMENT '猫咪 ID',
  `reginId` int(11) NOT NULL DEFAULT '-1' COMMENT '区域ID',
  PRIMARY KEY (`catId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_cat_region';

-- ----------------------------
-- Table structure for tb_collect_wechatUser
-- ----------------------------
DROP TABLE IF EXISTS `tb_collect_wechatUser`;
CREATE TABLE `tb_collect_wechatUser` (
  `catId` int(11) NOT NULL AUTO_INCREMENT COMMENT '猫咪 ID',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT '微信用户 token',
  PRIMARY KEY (`catId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_collect_wechatUser';

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论主键',
  `content` varchar(50) NOT NULL DEFAULT '' COMMENT '评论内容',
  `createTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '评论时间',
  `awesomeCount` int(11) NOT NULL DEFAULT '-1' COMMENT '点赞数量',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT '微信用户 token',
  `nickName` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `avaterUrl` varchar(50) NOT NULL DEFAULT '' COMMENT '头像链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_comment';

-- ----------------------------
-- Table structure for tb_comment_wechatUser
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment_wechatUser`;
CREATE TABLE `tb_comment_wechatUser` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论 id',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT '微信用户 token',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_comment_wechatUser';

-- ----------------------------
-- Table structure for tb_mail_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_mail_log`;
CREATE TABLE `tb_mail_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sendFrom` varchar(50) NOT NULL DEFAULT '' COMMENT '发送人',
  `sendTo` varchar(50) NOT NULL DEFAULT '' COMMENT '收件人',
  `sendTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '发送时间',
  `subject` varchar(50) NOT NULL DEFAULT '' COMMENT '邮件标题',
  `content` varchar(50) NOT NULL DEFAULT '' COMMENT '邮件正文',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_mail_log';

-- ----------------------------
-- Table structure for tb_pic
-- ----------------------------
DROP TABLE IF EXISTS `tb_pic`;
CREATE TABLE `tb_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片主键',
  `fileLink` varchar(50) NOT NULL DEFAULT '' COMMENT '图片链接',
  `fileUri` varchar(50) NOT NULL DEFAULT '' COMMENT '图片 URI',
  `uploadTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '图片上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_pic';

-- ----------------------------
-- Table structure for tb_praise_wechatUser
-- ----------------------------
DROP TABLE IF EXISTS `tb_praise_wechatUser`;
CREATE TABLE `tb_praise_wechatUser` (
  `openId` varchar(50) NOT NULL COMMENT '微信用户 openId',
  `catId` int(11) NOT NULL DEFAULT '-1' COMMENT '猫咪 ID',
  `praiseTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '点赞时间',
  PRIMARY KEY (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_praise_wechatUser';

-- ----------------------------
-- Table structure for tb_region
-- ----------------------------
DROP TABLE IF EXISTS `tb_region`;
CREATE TABLE `tb_region` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域 ID',
  `regionName` varchar(50) NOT NULL DEFAULT '' COMMENT '区域名称',
  `createTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '区域创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '区域更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_region';

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '用户密码',
  `createTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '用户创建时间',
  `lastLoginTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '上次登录时间',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT '管理员 token',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_user';

-- ----------------------------
-- Table structure for tb_wechatUser
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechatUser`;
CREATE TABLE `tb_wechatUser` (
  `openId` varchar(50) NOT NULL COMMENT 'open_id',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT 'token',
  `createTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '创建时间',
  `lastVisitTime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '最后登录时间',
  `sessionKey` varchar(50) NOT NULL DEFAULT '' COMMENT 'session_key',
  `city` varchar(50) NOT NULL DEFAULT '' COMMENT '市',
  `province` varchar(50) NOT NULL DEFAULT '' COMMENT '省',
  `country` varchar(50) NOT NULL DEFAULT '' COMMENT '国',
  `avatarUrl` varchar(50) NOT NULL DEFAULT '' COMMENT '头像',
  `gender` int(11) NOT NULL DEFAULT '-1' COMMENT '性别',
  `nickName` varchar(50) NOT NULL DEFAULT '' COMMENT '网名',
  PRIMARY KEY (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tb_wechatUser';

SET FOREIGN_KEY_CHECKS = 1;
