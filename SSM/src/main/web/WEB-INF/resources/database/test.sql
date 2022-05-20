/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2017-06-25 15:56:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `delflag` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('0', '1', '123123', '123', '12');
INSERT INTO `users` VALUES ('0', '2', 'qweqwe', '123', '22');
INSERT INTO `users` VALUES (null, '9', 'asd', 'asd', '111');

-- ----------------------------
-- Procedure structure for `myproc`
-- ----------------------------
DROP PROCEDURE IF EXISTS `myproc`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `myproc`(OUT s int)
BEGIN
select COUNT(*) into s from users;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `myproc2`
-- ----------------------------
DROP PROCEDURE IF EXISTS `myproc2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `myproc2`(IN num int)
BEGIN
SELECT num;
SET num=num+1;
SELECT num;
END
;;
DELIMITER ;
