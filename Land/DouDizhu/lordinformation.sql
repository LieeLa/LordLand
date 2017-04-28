/*
Navicat MySQL Data Transfer

Source Server         : mysqlnn
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : lordinformation

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-04-28 12:11:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information` (
  `playID` int(255) NOT NULL,
  `JinDou` int(255) NOT NULL,
  `Score` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`playID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of information
-- ----------------------------
INSERT INTO `information` VALUES ('0', '825', '8', '玩家0（电脑)');
INSERT INTO `information` VALUES ('1', '815', '6', '玩家1');
INSERT INTO `information` VALUES ('2', '795', '5', '玩家2（电脑）');

-- ----------------------------
-- Procedure structure for proce_UpdatePlayerInfor
-- ----------------------------
DROP PROCEDURE IF EXISTS `proce_UpdatePlayerInfor`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proce_UpdatePlayerInfor`(IN playerid int, IN goldenbean int, IN scores int)
BEGIN
	UPDATE information set information.JinDou = goldenbean, information.score = scores  
where information.playID = playerid ;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_QueryInfor
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_QueryInfor`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_QueryInfor`()
begin 
select* from information;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_UpdateScore
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_UpdateScore`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_UpdateScore`(IN playerid int)
BEGIN
update information set JinDou = JinDou + 10, Score = Score + 1
		where information.playID = playerid ;
END
;;
DELIMITER ;
SET FOREIGN_KEY_CHECKS=1;
