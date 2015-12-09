/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : canglong

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2015-12-01 10:46:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `bank` varchar(20) DEFAULT NULL,
  `last_active_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_ip` varchar(15) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_NAME` (`name`) USING BTREE,
  UNIQUE KEY `IDX_ID` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'canglong', 'canglong321@163.com', '3123123123123', null, null, '2015-11-30 16:58:14', null);
