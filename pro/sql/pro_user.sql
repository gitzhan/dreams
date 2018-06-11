/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : dreams-pro

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-06-11 22:34:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pro_user
-- ----------------------------
DROP TABLE IF EXISTS `pro_user`;
CREATE TABLE `pro_user` (
  `user_id` char(32) NOT NULL DEFAULT '' COMMENT 'userId',
  `account` varchar(64) NOT NULL DEFAULT '' COMMENT '用户账号',
  `password` varchar(120) NOT NULL DEFAULT '' COMMENT '密码',
  `type` tinyint(1) DEFAULT '0' COMMENT '用户类型(0:自定义注册用户 1:微信 2:其他...)',
  `phone` varchar(16) DEFAULT '' COMMENT '手机号',
  `name` varchar(64) DEFAULT '' COMMENT '昵称',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别(0:男 1:女)',
  `email` varchar(32) DEFAULT '' COMMENT '邮箱',
  `photo` varchar(255) DEFAULT '' COMMENT '头像',
  `token` varchar(120) DEFAULT '' COMMENT 'accessToken',
  `cid` varchar(120) DEFAULT '' COMMENT 'cid(推送)',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除(0:否 1:是)',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of pro_user
-- ----------------------------
INSERT INTO `pro_user` VALUES ('111111', 'aaaaaa', 'abcd1234', '0', '12345678901', 'userTest', '0', '1@1.com', null, '814c72792ccb4712bdca99b29d98281d@1528622470017', 'cidcid', '0', '2018-06-10 17:21:10', '2018-06-10 17:21:21');
INSERT INTO `pro_user` VALUES ('1fab501508214d298fad7db9ad3d0d47', 'aaaaaa', 'abcd1234', '0', '12345678901', 'userTest', '0', '1@1.com', null, '1fab501508214d298fad7db9ad3d0d47@1528622426420', 'cidcid', '0', '2018-06-10 17:20:26', '2018-06-10 17:20:26');
