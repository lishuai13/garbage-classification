/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : garbage_classification

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 12/06/2021 11:18:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for capacity
-- ----------------------------
DROP TABLE IF EXISTS `capacity`;
CREATE TABLE `capacity`  (
  `capacity_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '垃圾站容量表',
  `station_id` int(0) NOT NULL COMMENT '垃圾站',
  `category_id` int(0) NOT NULL COMMENT '垃圾种类',
  `capacity_number` int(0) NOT NULL COMMENT '容量',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`capacity_id`) USING BTREE,
  INDEX `垃圾站`(`station_id`) USING BTREE,
  INDEX `垃圾种类2`(`category_id`) USING BTREE,
  CONSTRAINT `垃圾种类2` FOREIGN KEY (`category_id`) REFERENCES `classification` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `垃圾站` FOREIGN KEY (`station_id`) REFERENCES `garbage_station` (`station_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '垃圾站容量表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of capacity
-- ----------------------------
INSERT INTO `capacity` VALUES (1, 1, 1, 50, '2021-04-14 16:53:50', '2021-04-14 16:53:52');
INSERT INTO `capacity` VALUES (2, 1, 2, 75, '2021-04-14 16:54:02', '2021-04-14 16:54:05');
INSERT INTO `capacity` VALUES (3, 1, 3, 74, '2021-04-14 16:54:20', '2021-04-14 16:54:22');
INSERT INTO `capacity` VALUES (4, 1, 4, 76, '2021-04-14 16:54:31', '2021-04-14 16:54:34');
INSERT INTO `capacity` VALUES (5, 2, 1, 80, '2021-04-14 16:54:45', '2021-04-14 16:54:48');
INSERT INTO `capacity` VALUES (6, 2, 2, 18, '2021-04-14 16:55:00', '2021-04-14 16:55:03');
INSERT INTO `capacity` VALUES (7, 2, 3, 63, '2021-04-14 16:55:16', '2021-04-14 16:55:19');
INSERT INTO `capacity` VALUES (8, 2, 4, 0, '2021-04-14 16:55:33', '2021-04-14 16:55:35');

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '垃圾分类信息',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '垃圾分类名称',
  `category_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '垃圾分类描述',
  `category_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '垃圾分类图片',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '垃圾分类信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES (1, '可回收垃圾', '###  可回收垃圾只指 \r\n废纸张、废塑料、废玻璃制品、废金属、废织物等适宜回收、可循环利用的生活废弃物。\r\n\r\n### 可回收物投放要求\r\n+ 轻投轻放\r\n+ 清洁干燥，避免污染\r\n+ 废纸尽量平整\r\n+ 立体包装物请清空内容物，清洁后压扁投放\r\n+ 有尖锐变焦的，应包裹后投放。', NULL, '2021-04-14 16:21:52', '2021-04-14 16:21:54');
INSERT INTO `classification` VALUES (2, '湿垃圾', '###  湿垃圾是指 \r\n即易腐垃圾,是指食材废料、剩菜剩饭、过期食品、瓜皮果核、花卉绿植、中药药渣等生物质、生活废弃物。\r\n\r\n### 湿垃圾投放要求\r\n+ 流质的食物垃圾，如牛奶等，应直接倒进下水口\r\n+ 有包装物的湿垃圾应将包装物去除后分类投放\r\n+ 包装物请投放到对应的可回收物或干垃圾容器。', NULL, '2021-04-14 16:42:11', '2021-04-14 16:42:13');
INSERT INTO `classification` VALUES (3, '干垃圾', '###  干垃圾是指\r\n即其他垃圾，是指除可回收物、有害垃圾、湿垃圾以外的其他生活废弃物。\r\n\r\n### 干垃圾投放要求\r\n+ 尽量沥干水分\r\n+ 难以辨识类别的生活垃圾投入干垃圾容器内。', NULL, '2021-04-14 16:45:32', '2021-04-14 16:45:34');
INSERT INTO `classification` VALUES (4, '有害垃圾', '###  有害垃圾是指\r\n废电池、废灯管、废药品、废油漆及其容器对人体健康或者自然环境造成直接或者潜在危害的生活废弃物。\r\n\r\n### 有害垃圾投放要求\r\n+ 投放时请注意轻放\r\n+ 易破损的请连带包装或包裹后轻放如易挥发，请密封后投放。', NULL, '2021-04-14 16:46:57', '2021-04-14 16:47:00');

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `complaint_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '投诉表',
  `user_id` int(0) NOT NULL COMMENT '用户',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `flag` bigint(0) NOT NULL DEFAULT 0 COMMENT '是否回复，默认为0，未回复',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '管理员回复',
  PRIMARY KEY (`complaint_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `投诉` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户投诉记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaint
-- ----------------------------
INSERT INTO `complaint` VALUES (1, 1, '系统开发', '系统开发真慢啊，前端真的难写啊。', '2021-04-14 16:48:57', '2021-04-14 16:49:00', 1, 'testssssssssss');
INSERT INTO `complaint` VALUES (2, 3, 'aaaaaaaa', 'cccccccccccc', '2021-04-15 15:49:26', '2021-04-15 15:49:26', 1, 'aaaaaaaaaaaaaa');
INSERT INTO `complaint` VALUES (3, 3, 'zzzzzzzzzzzz', 'cchhhhhhhhhhhhcccccccccc', '2021-04-15 15:49:44', '2021-04-15 15:49:44', 1, '222222222222222222222');
INSERT INTO `complaint` VALUES (4, 3, '前端好难啊', '确实好难啊！！！！！！！！！！4-16', '2021-04-16 13:34:54', '2021-04-16 13:34:54', 1, '11111111111111111111111111');
INSERT INTO `complaint` VALUES (5, 3, '1111111111111', '11111111111111111111111111', '2021-04-16 13:36:08', '2021-05-10 17:51:12', 1, '2222222222222222222222');
INSERT INTO `complaint` VALUES (10, 3, '23312', '122122', '2021-05-07 10:39:47', '2021-05-07 10:39:47', 1, '111111111111111111111111');
INSERT INTO `complaint` VALUES (12, 3, 'uuuuuuu', 'uuuuuuuuuuuuuuuuuuuu', '2021-05-10 17:45:40', '2021-05-10 17:45:40', 1, 'iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii');
INSERT INTO `complaint` VALUES (13, 3, '前端', '前端页面不够友好!', '2021-05-11 11:39:26', '2021-06-05 01:22:10', 1, 'ok');
INSERT INTO `complaint` VALUES (14, 3, '系统问题', '更新头像后，需要重新登录，这也太不友好了！', '2021-05-11 11:39:38', '2021-05-13 12:20:19', 1, '好的，我们后续会改进，多谢建议');
INSERT INTO `complaint` VALUES (16, 3, '积分', '为什么积分换物还没有上线', '2021-06-05 01:26:04', '2021-06-05 01:27:32', 1, 'OK');

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery`  (
  `delivery_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '投递信息',
  `user_id` int(0) NOT NULL COMMENT '投递人',
  `category_id` int(0) NOT NULL COMMENT '垃圾种类',
  `station_id` int(0) NOT NULL COMMENT '垃圾站',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`delivery_id`) USING BTREE,
  INDEX `投递`(`user_id`) USING BTREE,
  CONSTRAINT `投递` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '垃圾投递记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery
-- ----------------------------
INSERT INTO `delivery` VALUES (1, 1, 1, 1, '2021-04-14 16:49:32', '2021-04-14 16:49:34');
INSERT INTO `delivery` VALUES (2, 3, 1, 2, '2021-04-15 10:23:17', '2021-04-15 10:23:17');
INSERT INTO `delivery` VALUES (3, 3, 1, 2, '2021-04-15 10:25:03', '2021-04-15 10:25:03');
INSERT INTO `delivery` VALUES (4, 3, 1, 2, '2021-04-15 10:30:25', '2021-04-15 10:30:25');
INSERT INTO `delivery` VALUES (5, 3, 1, 2, '2021-04-15 10:32:45', '2021-04-15 10:32:45');
INSERT INTO `delivery` VALUES (6, 3, 1, 2, '2021-04-15 14:03:42', '2021-04-15 14:03:42');
INSERT INTO `delivery` VALUES (7, 3, 1, 1, '2021-04-17 16:13:57', '2021-04-17 16:13:57');
INSERT INTO `delivery` VALUES (8, 3, 4, 1, '2021-04-17 16:15:39', '2021-04-17 16:15:39');
INSERT INTO `delivery` VALUES (9, 3, 3, 2, '2021-04-18 07:52:22', '2021-04-18 07:52:22');
INSERT INTO `delivery` VALUES (10, 3, 3, 2, '2021-04-18 08:02:36', '2021-04-18 08:02:36');
INSERT INTO `delivery` VALUES (11, 3, 1, 1, '2021-04-18 16:06:19', '2021-04-18 16:06:19');
INSERT INTO `delivery` VALUES (12, 3, 2, 1, '2021-04-18 16:21:16', '2021-04-18 16:21:16');
INSERT INTO `delivery` VALUES (14, 3, 1, 1, '2021-05-07 10:18:05', '2021-05-07 10:18:05');
INSERT INTO `delivery` VALUES (15, 3, 4, 1, '2021-05-07 10:18:47', '2021-05-07 10:18:47');
INSERT INTO `delivery` VALUES (16, 3, 4, 1, '2021-05-07 10:38:32', '2021-05-07 10:38:32');
INSERT INTO `delivery` VALUES (17, 3, 1, 1, '2021-05-07 10:39:33', '2021-05-07 10:39:33');
INSERT INTO `delivery` VALUES (18, 3, 1, 2, '2021-05-07 10:41:40', '2021-05-07 10:41:40');
INSERT INTO `delivery` VALUES (19, 3, 1, 1, '2021-05-09 23:59:22', '2021-05-09 23:59:22');
INSERT INTO `delivery` VALUES (20, 3, 1, 1, '2021-05-10 22:23:03', '2021-05-10 22:23:03');
INSERT INTO `delivery` VALUES (21, 3, 2, 2, '2021-05-10 22:35:45', '2021-05-10 22:35:45');
INSERT INTO `delivery` VALUES (22, 3, 3, 1, '2021-05-10 22:36:04', '2021-05-10 22:36:04');
INSERT INTO `delivery` VALUES (23, 3, 1, 1, '2021-05-10 22:38:24', '2021-05-10 22:38:24');
INSERT INTO `delivery` VALUES (24, 3, 2, 2, '2021-05-10 22:44:33', '2021-05-10 22:44:33');
INSERT INTO `delivery` VALUES (25, 3, 1, 1, '2021-05-10 22:59:36', '2021-05-10 22:59:36');
INSERT INTO `delivery` VALUES (26, 3, 1, 1, '2021-05-10 23:00:48', '2021-05-10 23:00:48');
INSERT INTO `delivery` VALUES (27, 3, 1, 1, '2021-05-10 23:02:13', '2021-05-10 23:02:13');
INSERT INTO `delivery` VALUES (28, 3, 1, 1, '2021-05-10 23:03:05', '2021-05-10 23:03:05');
INSERT INTO `delivery` VALUES (29, 3, 3, 2, '2021-05-10 23:03:14', '2021-05-10 23:03:14');
INSERT INTO `delivery` VALUES (30, 3, 2, 1, '2021-05-10 23:03:32', '2021-05-10 23:03:32');
INSERT INTO `delivery` VALUES (31, 3, 1, 2, '2021-05-10 23:03:39', '2021-05-10 23:03:39');
INSERT INTO `delivery` VALUES (35, 1, 1, 1, '2021-05-10 23:21:12', '2021-05-10 23:21:12');
INSERT INTO `delivery` VALUES (36, 1, 1, 1, '2021-05-10 23:24:12', '2021-05-10 23:24:12');
INSERT INTO `delivery` VALUES (37, 1, 1, 1, '2021-05-10 23:24:57', '2021-05-10 23:24:57');
INSERT INTO `delivery` VALUES (38, 1, 1, 1, '2021-05-10 23:30:31', '2021-05-10 23:30:31');
INSERT INTO `delivery` VALUES (39, 1, 3, 1, '2021-05-10 23:30:37', '2021-05-10 23:30:37');
INSERT INTO `delivery` VALUES (40, 1, 1, 1, '2021-05-10 23:32:01', '2021-05-10 23:32:01');
INSERT INTO `delivery` VALUES (41, 1, 2, 1, '2021-05-10 23:34:13', '2021-05-10 23:34:13');
INSERT INTO `delivery` VALUES (42, 1, 2, 2, '2021-05-10 23:35:15', '2021-05-10 23:35:15');
INSERT INTO `delivery` VALUES (43, 1, 1, 2, '2021-05-10 23:36:36', '2021-05-10 23:36:36');
INSERT INTO `delivery` VALUES (44, 1, 2, 1, '2021-05-10 23:37:02', '2021-05-10 23:37:02');
INSERT INTO `delivery` VALUES (45, 1, 2, 1, '2021-05-10 23:40:02', '2021-05-10 23:40:02');
INSERT INTO `delivery` VALUES (46, 1, 1, 1, '2021-05-10 23:41:40', '2021-05-10 23:41:40');
INSERT INTO `delivery` VALUES (47, 1, 1, 1, '2021-05-10 23:41:46', '2021-05-10 23:41:46');
INSERT INTO `delivery` VALUES (48, 1, 1, 1, '2021-05-10 23:42:30', '2021-05-10 23:42:30');
INSERT INTO `delivery` VALUES (49, 1, 1, 1, '2021-05-10 23:44:11', '2021-05-10 23:44:11');
INSERT INTO `delivery` VALUES (50, 1, 1, 1, '2021-05-10 23:47:01', '2021-05-10 23:47:01');
INSERT INTO `delivery` VALUES (51, 1, 1, 1, '2021-05-10 23:47:35', '2021-05-10 23:47:35');
INSERT INTO `delivery` VALUES (52, 1, 1, 1, '2021-05-10 23:48:45', '2021-05-10 23:48:45');
INSERT INTO `delivery` VALUES (53, 1, 1, 1, '2021-05-10 23:52:58', '2021-05-10 23:52:58');
INSERT INTO `delivery` VALUES (54, 1, 1, 1, '2021-05-10 23:53:09', '2021-05-10 23:53:09');
INSERT INTO `delivery` VALUES (55, 1, 1, 1, '2021-05-10 23:53:46', '2021-05-10 23:53:46');
INSERT INTO `delivery` VALUES (56, 1, 2, 1, '2021-05-10 23:54:05', '2021-05-10 23:54:05');
INSERT INTO `delivery` VALUES (57, 1, 2, 2, '2021-05-10 23:54:21', '2021-05-10 23:54:21');
INSERT INTO `delivery` VALUES (58, 1, 1, 1, '2021-05-10 23:56:15', '2021-05-10 23:56:15');
INSERT INTO `delivery` VALUES (59, 1, 1, 2, '2021-05-10 23:56:55', '2021-05-10 23:56:55');
INSERT INTO `delivery` VALUES (60, 1, 1, 1, '2021-05-11 00:05:32', '2021-05-11 00:05:32');
INSERT INTO `delivery` VALUES (61, 1, 1, 1, '2021-05-11 00:05:37', '2021-05-11 00:05:37');
INSERT INTO `delivery` VALUES (62, 1, 1, 1, '2021-05-11 00:06:47', '2021-05-11 00:06:47');
INSERT INTO `delivery` VALUES (63, 1, 1, 1, '2021-05-11 00:06:53', '2021-05-11 00:06:53');
INSERT INTO `delivery` VALUES (64, 1, 1, 1, '2021-05-11 00:08:34', '2021-05-11 00:08:34');
INSERT INTO `delivery` VALUES (65, 1, 1, 1, '2021-05-11 10:46:10', '2021-05-11 10:46:10');
INSERT INTO `delivery` VALUES (66, 1, 1, 1, '2021-05-11 10:52:45', '2021-05-11 10:52:45');
INSERT INTO `delivery` VALUES (67, 1, 1, 2, '2021-05-11 11:00:52', '2021-05-11 11:00:52');
INSERT INTO `delivery` VALUES (72, 3, 3, 1, '2021-06-05 01:17:19', '2021-06-05 01:17:19');

-- ----------------------------
-- Table structure for garbage
-- ----------------------------
DROP TABLE IF EXISTS `garbage`;
CREATE TABLE `garbage`  (
  `garbage_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '垃圾表',
  `garbage_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '垃圾名',
  `garbage_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '垃圾描述',
  `category_id` int(0) NOT NULL COMMENT '垃圾分类',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`garbage_id`) USING BTREE,
  UNIQUE INDEX `垃圾名称`(`garbage_name`) USING BTREE,
  INDEX `垃圾种类`(`category_id`) USING BTREE,
  CONSTRAINT `垃圾种类` FOREIGN KEY (`category_id`) REFERENCES `classification` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '垃圾信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of garbage
-- ----------------------------
INSERT INTO `garbage` VALUES (1, '苹果电脑', '苹果电脑', 1, '2021-04-14 16:50:19', '2021-04-14 16:50:22');
INSERT INTO `garbage` VALUES (2, '烂苹果', '烂苹果', 2, '2021-04-14 16:50:58', '2021-04-14 16:51:00');
INSERT INTO `garbage` VALUES (3, '苹果核', '干的苹果核', 3, '2021-04-26 17:32:18', '2021-04-26 17:32:21');
INSERT INTO `garbage` VALUES (4, '苹果电脑电池', '电池', 4, '2021-04-26 17:32:52', '2021-04-26 17:32:55');

-- ----------------------------
-- Table structure for garbage_station
-- ----------------------------
DROP TABLE IF EXISTS `garbage_station`;
CREATE TABLE `garbage_station`  (
  `station_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '垃圾站表',
  `station_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '垃圾站名称',
  `station_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '垃圾站地址',
  `station_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '垃圾站描述',
  `station_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '垃圾站图片',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`station_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '垃圾站信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of garbage_station
-- ----------------------------
INSERT INTO `garbage_station` VALUES (1, '天健垃圾站', '天健园', '天健男儿的垃圾站', NULL, '2021-04-14 16:51:54', '2021-04-14 16:51:56');
INSERT INTO `garbage_station` VALUES (2, '修贤垃圾站', '休闲广场', '', NULL, '2021-04-14 16:52:34', '2021-04-14 16:52:36');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `notice_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '公告',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告信息',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '> 作者：吃桔子的攻城狮 来源：http://www.tekbroaden.com/singleton-java.html\r\n\r\n单例模式可能是代码最少的模式了，但是少不一定意味着简单，想要用好、用对单例模式，还真得费一番脑筋。本文对 Java 中常见的单例模式写法做了一个总结，如有错漏之处，恳请读者指正。\r\n\r\n饿汉法\r\n===\r\n\r\n顾名思义，饿汉法就是在第一次引用该类的时候就创建对象实例，而不管实际是否需要创建。代码如下：\r\n\r\n```\r\npublic class Singleton {  \r\n    private static Singleton = new Singleton();\r\n    private Singleton() {}\r\n    public static getSignleton(){\r\n        return singleton;\r\n    }\r\n}\r\n\r\n```\r\n![test](https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/test/test-1.jpg)\r\n\r\n', '2021-05-14 16:58:47', '2021-05-19 16:23:07');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '角色表',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '身份表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'user');
INSERT INTO `role` VALUES (2, 'admin');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户表',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `score` int(0) NOT NULL DEFAULT 0 COMMENT '积分，默认为0',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png' COMMENT '头像',
  `role_id` int(0) NOT NULL DEFAULT 1 COMMENT '角色id',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '加密用的随机盐',
  `status` bigint(0) NOT NULL DEFAULT 1 COMMENT '用户状态，0：封号，1：正常',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `角色`(`role_id`) USING BTREE,
  CONSTRAINT `角色` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'lishuai', 'c96aa1268950ba3355f1946d662c825c', '13870985027', '1431881130@qq.com', 35, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'http://lishuai13.oss-cn-shanghai.aliyuncs.com/userAvatars/lishuai-1.jpg', 2, '0sd082FC', 1);
INSERT INTO `user` VALUES (2, 'gggw', '126233dab083a180a47bde1df9255fd6', '13870985027', '1431881130@qq.com', 0, '2021-04-14 16:53:14', '2021-04-14 16:53:16', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncMqwer', 1);
INSERT INTO `user` VALUES (3, 'test', 'ddfcc7fa57158e0aaa7913d9fc99aab3', '15961473267', '1431881130@qq.com', 34, '2021-04-15 08:33:52', '2021-04-16 16:02:12', 'http://lishuai13.oss-cn-shanghai.aliyuncs.com/userAvatars/test-3.jpg', 1, '8(qA)2rK', 1);
INSERT INTO `user` VALUES (11, 'test3', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 1);
INSERT INTO `user` VALUES (12, 'test4', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 0);
INSERT INTO `user` VALUES (13, 'test5', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 1);
INSERT INTO `user` VALUES (14, 'test6', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 1);
INSERT INTO `user` VALUES (15, 'test7', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 0);
INSERT INTO `user` VALUES (16, 'test8', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'http://lishuai13.oss-cn-shanghai.aliyuncs.com/userAvatars/test8-16.jpg', 1, '5#lV3ncM', 1);
INSERT INTO `user` VALUES (17, 'test9', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 0);
INSERT INTO `user` VALUES (18, 'test10', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 0);
INSERT INTO `user` VALUES (19, 'test11', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 1);
INSERT INTO `user` VALUES (20, 'test12', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 1);
INSERT INTO `user` VALUES (21, 'test13', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 1);
INSERT INTO `user` VALUES (22, 'test14', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 1);
INSERT INTO `user` VALUES (23, 'test15', '126233dab083a180a47bde1df9255fd6', '15961473267', '1431881130@qq.com', 0, '2021-04-14 15:45:00', '2021-04-14 15:45:06', 'https://lishuai13.oss-cn-shanghai.aliyuncs.com/common/default-avatar.png', 1, '5#lV3ncM', 1);
INSERT INTO `user` VALUES (26, 'ttttt', '9a4f4bf71fd3b2bba169c87c61e5e099', '15961473267', '1431881130@qq.com', 0, '2021-05-11 10:56:06', '2021-05-11 10:56:06', 'http://lishuai13.oss-cn-shanghai.aliyuncs.com/userAvatars/ttttt.jpg', 1, 'PRFYVrxu', 1);

SET FOREIGN_KEY_CHECKS = 1;
