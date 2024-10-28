/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : db_goods

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 06/05/2021 17:37:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `editor` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fbsj` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of board
-- ----------------------------
INSERT INTO `board` VALUES (1, '2021！你好！', '今天校园丢失东西了吗', '逍遥游', '2021-04-24 01:05:01');
INSERT INTO `board` VALUES (2, '2021年计算机专业Java相关毕业设计如何选题更容易通过', '逍遥游说', '逍遥游', '2021-04-24 01:08:50');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `lostPlace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `whoLost` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createTime` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '钥匙', '北京路附近', 'admin   ', '钥匙有三把', '2021-03-17', '1', ' 17766091168');
INSERT INTO `goods` VALUES (2, '华为P30', ' 南京', 'test ', '非常新', '2021-03-18', '1', ' 17766091168');
INSERT INTO `goods` VALUES (3, '耳机', ' 建邺区附近', 'test ', '黑色的', '2021-03-16', '1', ' 17766091168');
INSERT INTO `goods` VALUES (4, '茶杯', ' 建邺区附近', 'test ', '黑色的富光茶杯', '2021-03-18', '1', ' 17766091168');

-- ----------------------------
-- Table structure for letter
-- ----------------------------
DROP TABLE IF EXISTS `letter`;
CREATE TABLE `letter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `text` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `username` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of letter
-- ----------------------------
INSERT INTO `letter` VALUES (1, '设计模式之单例模式112', '<p>&nbsp; &nbsp; &nbsp;设计模式之单例模式设计模式之单例模式设计模式之单例模式设计模式之单例模式设计模式之单例模式设计模式之单例模式设计模式之单例模式</p><p>设计模式之单例模式设计模式之单例模式设计模式之单例模式设计模式之单例模式</p><p>设计模式之单例模式设计模式之单例模式设计模式之单例模式</p>', 'test');
INSERT INTO `letter` VALUES (5, 'Spring面试集合【三】', '<p>Spring面试集合【三】Spring面试集合【三】Spring面试集合【三】</p><p>Spring面试集合【三】Spring面试集合【三】Spring面试集合【三】</p><p>Spring面试集合【三】Spring面试集合【三】Spring面试集合【三】</p>', 'test');

-- ----------------------------
-- Table structure for meet
-- ----------------------------
DROP TABLE IF EXISTS `meet`;
CREATE TABLE `meet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `rlwp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `rlsj` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meet
-- ----------------------------
INSERT INTO `meet` VALUES (2, 'test ', '这是我的', ' 17766091168', '荣威R-X5', 48, '2021-05-06 01:18:38', 1);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createTime` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 'test', '今天天气不错', '2020-03-17');
INSERT INTO `message` VALUES (7, 'test', '哈哈哈', '2020-03-17');
INSERT INTO `message` VALUES (17, 'test2', '我想你了', '2020-03-17');
INSERT INTO `message` VALUES (18, 'test', '11111', '2021-04-22');
INSERT INTO `message` VALUES (19, 'test', '1122121', '2021-04-22');
INSERT INTO `message` VALUES (20, 'test', '12122112', '2021-04-22');
INSERT INTO `message` VALUES (21, 'test', '21212112', '2021-04-22');
INSERT INTO `message` VALUES (22, 'test', '1221212', '2021-04-22');

-- ----------------------------
-- Table structure for shiwu
-- ----------------------------
DROP TABLE IF EXISTS `shiwu`;
CREATE TABLE `shiwu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `wpname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `place` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createTime` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `imgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiwu
-- ----------------------------
INSERT INTO `shiwu` VALUES (16, 'test', '欢乐谷图片', ' 南京', '2021-03-15 00:20:12', ' 17766091168', '348205dd-6694-439c-8f34-b2d7da4dd528.jpg', '和好');
INSERT INTO `shiwu` VALUES (17, 'test', '荣威R-X5', '南京', '2021-03-17 00:47:04', '17766091168', 'aa242fdc-bd81-4942-9a5a-1eea981b342e.jpg', '荣威车辆很酷');
INSERT INTO `shiwu` VALUES (29, 'test', '暖风扇', '外边', '2021-03-18 01:42:01', ' 17766091168', '127fd8ec-57d6-475c-a187-993c8af6251a.jpg', '非常暖');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (33, 'admin', '123456', '2', '1', '李四');
INSERT INTO `user` VALUES (48, 'test', '123456', '1', '1', '测试');
INSERT INTO `user` VALUES (57, 'zhangsan ', '123456', '1', '1', '张三');

SET FOREIGN_KEY_CHECKS = 1;
