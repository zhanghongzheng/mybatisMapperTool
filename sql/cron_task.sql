/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50623
 Source Host           : localhost
 Source Database       : pay_naccount

 Target Server Version : 50623
 File Encoding         : utf-8

 Date: 10/21/2015 19:44:35 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `pay_cron_task`
-- ----------------------------
DROP TABLE IF EXISTS `pay_cron_task`;
CREATE TABLE `cron_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ip` varchar(32) NOT NULL COMMENT 'ip地址',
  `taskName` varchar(64) NOT NULL COMMENT '标识名',
  `server` varchar(64) NOT NULL COMMENT '服务名',
  `token` varchar(128) NOT NULL COMMENT '服务唯一标识码',
  `status` int(11) NOT NULL COMMENT '状态 0初始化  1完成',
  `type` varchar(16) NOT NULL COMMENT '类型',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  `createTimeSeconds` bigint(20) NOT NULL COMMENT '创建时间秒数',
  `updateTimeSeconds` bigint(20) NOT NULL COMMENT '更新时间秒数',
  `ext` varchar(512) NOT NULL COMMENT '额外信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `taskName` (`taskName`,`server`),
  KEY `createTime` (`createTime`),
  KEY `taskName_2` (`taskName`),
  KEY `server` (`server`),
  KEY `token` (`token`),
  KEY `createTimeSeconds` (`createTimeSeconds`),
  KEY `updateTimeSeconds` (`updateTimeSeconds`),
  KEY `status` (`status`),
  KEY `type` (`type`),
  KEY `createTime_2` (`createTime`),
  KEY `updateTime` (`updateTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `pay_cron_task`
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
