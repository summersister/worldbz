/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : dear

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 03/07/2020 20:15:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '社区名字',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社区介绍',
  `headUrl` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社区头像',
  `del` tinyint(4) NOT NULL COMMENT '是否删除 0正常1删除',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of community
-- ----------------------------
INSERT INTO `community` VALUES (1, '这是一个很隐蔽很隐蔽很隐蔽很隐蔽很隐蔽的地方', '隐居者，隐居在一个很隐蔽很隐蔽的地方。', 'https://imgsa.baidu.com/forum/w%3D580%3B/sign=6b096ae9202eb938ec6d7afae5598435/9d82d158ccbf6c81bfe445e0b33eb13532fa40e2.jpg', 0, '2020-06-09 13:41:04', '2020-06-09 13:41:04');
INSERT INTO `community` VALUES (2, '很想', '哪有那么多适合自己的人', 'https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg?wapp&quality=80&size=b150_150&subsize=20480&cut_x=0&cut_w=0&cut_y=0&cut_h=0&sec=1369815402&srctrace&di=4079135eab76882e2d712782b9b0ba3a&wh_rate=null&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2Fbf096b63f6246b6018caa89beef81a4c500fa21c.jpg', 0, '2020-06-09 13:42:52', '2020-06-09 13:42:55');
INSERT INTO `community` VALUES (3, '奇迹暖暖', '奇迹暖暖粉丝联盟', 'https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg?wapp&quality=80&size=b150_150&subsize=20480&cut_x=0&cut_w=0&cut_y=0&cut_h=0&sec=1369815402&srctrace&di=3351259ab0778c857004be2ca804dd1a&wh_rate=null&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2F5bafa40f4bfbfbed327356c97df0f736afc31f59.jpg', 0, '2020-06-17 18:05:55', '2020-06-17 18:05:59');
INSERT INTO `community` VALUES (4, '初音ミク吧', '接触VOCALOID，从初音开始', 'https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg?wapp&quality=80&size=b150_150&subsize=20480&cut_x=0&cut_w=0&cut_y=0&cut_h=0&sec=1369815402&srctrace&di=981cb023e2451b27e7a2f24c0bdb8b83&wh_rate=null&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2F8694a4c27d1ed21b4dda948aac6eddc451da3f49.jpg', 0, '2020-06-20 16:32:49', '2020-06-20 16:32:51');
INSERT INTO `community` VALUES (5, '情侣吧', '愿得一人心，白首不相离', 'https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg?wapp&quality=80&size=b150_150&subsize=20480&cut_x=0&cut_w=0&cut_y=0&cut_h=0&sec=1369815402&srctrace&di=ef11f401747d751a08273b5f115f49d8&wh_rate=null&src=http%3A%2F%2Ftiebapic.baidu.com%2Fforum%2Fpic%2Fitem%2F9345d688d43f879422334e46c51b0ef41ad53a8d.jpg', 0, '2020-06-20 16:33:01', '2020-06-20 16:33:04');
INSERT INTO `community` VALUES (6, '内涵图', '以内涵的图诠释幽默，用内涵与你分享快乐。', 'https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg?wapp&quality=80&size=b150_150&subsize=20480&cut_x=0&cut_w=0&cut_y=0&cut_h=0&sec=1369815402&srctrace&di=35580a5e89892530dbfa24bcd2008983&wh_rate=null&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2Fdc54564e9258d1090fb17aadd258ccbf6c814d5b.jpg', 0, '2020-06-20 16:36:56', '2020-06-20 16:36:58');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comId` int(11) NOT NULL COMMENT '社区id',
  `userId` int(11) NOT NULL,
  `replyCount` int(11) NULL DEFAULT NULL COMMENT '回复数',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `good` bit(1) NULL DEFAULT NULL COMMENT '是否加精',
  `hide` bit(1) NULL DEFAULT NULL COMMENT '是否隐藏',
  `del` bit(1) NOT NULL,
  `top` bit(1) NULL DEFAULT NULL COMMENT '置顶',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `delUser` int(11) NULL DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (1, 1, 10000, 44, '球球集美帮我搭一个正常的清纯风', '球球集美帮我搭一个正常的清纯风', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (2, 1, 10000, 2, '新爆料是不是大家都在推关啊', '新爆料\r\n是不是大家都在推关啊', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (3, 1, 10000, 1, '33333', 'wwwwwwwwww呜呜呜呜呜呜呜呜无无我无无无无无', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (4, 1, 10000, 1, '44444444', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (5, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (6, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (7, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (8, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (9, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (10, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (11, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (12, 1, 10000, 1, '121212121212112', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (13, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (14, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (15, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (16, 1, 10000, 2, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (17, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (18, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (19, 1, 10000, 3, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (20, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (21, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (22, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (23, 1, 10000, 1, '2323232323232', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (24, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (25, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (26, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (27, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (28, 1, 10000, 1, '2828282828', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (29, 1, 10000, 1, 'nmsl', 'nmslnmslnmslnmslnmsl', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (30, 3, 10000, 1, '奇怪的人体又增加了呢么么么表白叠纸的操作，你们是多', '奇怪的人体又增加了呢么么么表白叠纸的操作，你们是多爱这个动作', b'1', b'0', b'0', b'0', NULL, 1);
INSERT INTO `post` VALUES (31, 5, 10000, 2, '情侣吧第一帖', '我不在呢', b'0', b'0', b'0', b'0', '2020-06-23 15:25:34', NULL);
INSERT INTO `post` VALUES (32, 1, 10000, 0, '321', '312', b'0', b'0', b'0', b'0', '2020-06-23 16:03:56', NULL);
INSERT INTO `post` VALUES (33, 1, 10000, 0, '我我我我我', '312', b'0', b'0', b'0', b'0', '2020-06-23 16:04:08', NULL);
INSERT INTO `post` VALUES (34, 1, 10000, 0, '我我我我我', '312', b'0', b'0', b'0', b'0', '2020-06-23 16:07:41', NULL);
INSERT INTO `post` VALUES (35, 1, 10000, 3, '312', '312', b'0', b'0', b'0', b'0', '2020-06-23 16:16:41', NULL);
INSERT INTO `post` VALUES (36, 1, 10000, 2, '3213', '21', b'0', b'0', b'0', b'0', '2020-06-29 11:15:16', NULL);
INSERT INTO `post` VALUES (37, 2, 10007, 0, '小东西挺别致', '小东西挺别致小东西挺别致', b'0', b'0', b'0', b'0', '2020-07-03 19:54:13', NULL);

-- ----------------------------
-- Table structure for post_picture
-- ----------------------------
DROP TABLE IF EXISTS `post_picture`;
CREATE TABLE `post_picture`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `postId` int(11) NOT NULL COMMENT '帖子id',
  `url` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for post_reply
-- ----------------------------
DROP TABLE IF EXISTS `post_reply`;
CREATE TABLE `post_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `postId` int(11) NOT NULL COMMENT '帖子id',
  `userId` int(11) NOT NULL COMMENT '回帖人',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `number` int(11) NULL DEFAULT NULL COMMENT '楼层',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `del` bit(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_reply
-- ----------------------------
INSERT INTO `post_reply` VALUES (1, 1, 10000, '一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复一楼回复', 1, '2020-06-09 13:50:44', b'0');
INSERT INTO `post_reply` VALUES (2, 1, 10000, '二楼回复', 2, '2020-06-09 13:50:46', b'0');
INSERT INTO `post_reply` VALUES (3, 1, 10000, '回复', 3, '2020-06-09 13:50:46', b'0');
INSERT INTO `post_reply` VALUES (4, 1, 10000, '回复', 4, '2020-06-09 13:50:46', b'0');
INSERT INTO `post_reply` VALUES (5, 1, 10000, '回复', 5, '2020-06-09 13:50:46', b'0');
INSERT INTO `post_reply` VALUES (6, 1, 10000, '回复', 6, '2020-06-09 13:50:46', b'0');
INSERT INTO `post_reply` VALUES (7, 1, 10000, '回复', 7, '2020-06-09 13:50:46', b'0');
INSERT INTO `post_reply` VALUES (8, 1, 10000, '回复', 8, '2020-06-09 13:50:46', b'0');
INSERT INTO `post_reply` VALUES (9, 1, 10000, '回复', 9, '2020-06-09 13:50:46', b'0');
INSERT INTO `post_reply` VALUES (10, 1, 10000, '回复', 10, '2020-06-09 13:50:46', b'0');
INSERT INTO `post_reply` VALUES (11, 1, 10000, '回复', 11, '2020-06-09 13:50:46', b'0');
INSERT INTO `post_reply` VALUES (30, 1, 10000, '2321', 28, '2020-06-16 12:05:08', b'0');
INSERT INTO `post_reply` VALUES (31, 1, 10000, '1231', 29, '2020-06-16 12:05:40', b'0');
INSERT INTO `post_reply` VALUES (32, 1, 10000, '14', 30, '2020-06-16 12:06:08', b'0');
INSERT INTO `post_reply` VALUES (33, 1, 10000, '15', 31, '2020-06-16 12:06:09', b'0');
INSERT INTO `post_reply` VALUES (34, 1, 10000, '16', 32, '2020-06-16 12:06:11', b'0');
INSERT INTO `post_reply` VALUES (35, 1, 10000, '17', 33, '2020-06-16 12:06:12', b'0');
INSERT INTO `post_reply` VALUES (36, 1, 10000, '18', 34, '2020-06-16 12:06:14', b'0');
INSERT INTO `post_reply` VALUES (37, 1, 10000, '19', 35, '2020-06-16 12:06:17', b'0');
INSERT INTO `post_reply` VALUES (38, 1, 10000, '20', 36, '2020-06-16 12:06:21', b'0');
INSERT INTO `post_reply` VALUES (39, 1, 10000, '21', 37, '2020-06-16 12:06:24', b'0');
INSERT INTO `post_reply` VALUES (40, 1, 10000, '21', 38, '2020-06-16 12:07:41', b'0');
INSERT INTO `post_reply` VALUES (41, 1, 10000, '666', 39, '2020-06-16 12:15:12', b'0');
INSERT INTO `post_reply` VALUES (42, 1, 10000, 'hgfhgfhx', 40, '2020-06-16 12:15:28', b'0');
INSERT INTO `post_reply` VALUES (43, 1, 10000, '1561456156156', 41, '2020-06-16 12:17:05', b'0');
INSERT INTO `post_reply` VALUES (44, 1, 10000, '123', 42, '2020-06-16 15:48:25', b'0');
INSERT INTO `post_reply` VALUES (45, 1, 10000, '123', 43, '2020-06-16 15:48:41', b'0');
INSERT INTO `post_reply` VALUES (46, 1, 10000, '3333', 44, '2020-06-16 15:49:13', b'0');
INSERT INTO `post_reply` VALUES (47, 1, 10000, '231', 45, '2020-06-16 15:49:38', b'0');
INSERT INTO `post_reply` VALUES (48, 1, 10000, '321321', 46, '2020-06-16 15:49:41', b'0');
INSERT INTO `post_reply` VALUES (49, 1, 10000, '123', 47, '2020-06-20 18:14:12', b'0');
INSERT INTO `post_reply` VALUES (50, 1, 10000, '这是一个完美的回帖', 48, '2020-06-20 18:14:28', b'0');
INSERT INTO `post_reply` VALUES (51, 1, 10000, '哦呦', 49, '2020-06-20 18:20:05', b'0');
INSERT INTO `post_reply` VALUES (52, 19, 10002, '123', 2, '2020-06-22 16:57:48', b'0');
INSERT INTO `post_reply` VALUES (53, 31, 10000, '我不在呢', 1, '2020-06-23 15:25:34', b'0');
INSERT INTO `post_reply` VALUES (54, 31, 10000, '不知道呢', 2, '2020-06-23 15:25:50', b'0');
INSERT INTO `post_reply` VALUES (55, 32, 10000, '312', 1, '2020-06-23 16:03:56', b'0');
INSERT INTO `post_reply` VALUES (56, 33, 10000, '312', 1, '2020-06-23 16:04:08', b'0');
INSERT INTO `post_reply` VALUES (57, 34, 10000, '312', 1, '2020-06-23 16:07:41', b'0');
INSERT INTO `post_reply` VALUES (58, 35, 10000, '312', 1, '2020-06-23 16:16:41', b'0');
INSERT INTO `post_reply` VALUES (59, 35, 10000, '123', 2, '2020-06-23 16:16:51', b'0');
INSERT INTO `post_reply` VALUES (60, 35, 10000, '6666', 3, '2020-06-23 16:17:10', b'0');
INSERT INTO `post_reply` VALUES (61, 35, 10000, '666', 4, '2020-06-23 16:17:14', b'0');
INSERT INTO `post_reply` VALUES (62, 16, 10000, '123', 2, '2020-06-23 16:21:10', b'0');
INSERT INTO `post_reply` VALUES (63, 36, 10000, '21', 1, '2020-06-29 11:15:16', b'0');
INSERT INTO `post_reply` VALUES (64, 2, 10000, '321', 2, '2020-06-29 15:54:29', b'0');
INSERT INTO `post_reply` VALUES (65, 36, 10007, '321', 2, '2020-07-03 19:20:52', b'0');
INSERT INTO `post_reply` VALUES (66, 36, 10000, '11', 3, '2020-07-03 19:21:11', b'0');
INSERT INTO `post_reply` VALUES (67, 37, 10007, '小东西挺别致小东西挺别致', 1, '2020-07-03 19:54:13', b'0');

-- ----------------------------
-- Table structure for post_reply_floor
-- ----------------------------
DROP TABLE IF EXISTS `post_reply_floor`;
CREATE TABLE `post_reply_floor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `postId` int(11) NOT NULL,
  `replyId` int(11) NOT NULL,
  `userId` int(11) NOT NULL COMMENT '用户id',
  `content` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(11) NOT NULL,
  `del` bit(1) NOT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_reply_floor
-- ----------------------------
INSERT INTO `post_reply_floor` VALUES (1, 1, 1, 10000, '66666', 1, b'0', '2020-06-11 14:09:34');
INSERT INTO `post_reply_floor` VALUES (2, 1, 3, 10000, '回复一下3楼', 1, b'0', '2020-06-11 14:13:58');
INSERT INTO `post_reply_floor` VALUES (3, 1, 1, 10000, '7777777', 2, b'0', '2020-06-13 19:50:37');
INSERT INTO `post_reply_floor` VALUES (4, 1, 1, 10000, '88888', 3, b'0', '2020-06-13 19:51:07');
INSERT INTO `post_reply_floor` VALUES (5, 1, 1, 10000, '123', 4, b'0', '2020-06-14 03:57:09');
INSERT INTO `post_reply_floor` VALUES (6, 1, 1, 10000, '123', 5, b'0', '2020-06-14 03:58:45');
INSERT INTO `post_reply_floor` VALUES (7, 1, 1, 10000, '卧槽', 6, b'0', '2020-06-14 04:00:06');
INSERT INTO `post_reply_floor` VALUES (8, 1, 5, 10000, 'gun', 1, b'0', '2020-06-14 04:05:35');
INSERT INTO `post_reply_floor` VALUES (9, 1, 5, 10000, '123456789', 2, b'0', '2020-06-14 04:06:18');
INSERT INTO `post_reply_floor` VALUES (10, 1, 22, 10000, '第二页回复一个', 1, b'0', '2020-06-14 04:08:28');
INSERT INTO `post_reply_floor` VALUES (11, 1, 1, 10000, '7777', 7, b'0', '2020-06-14 04:28:03');
INSERT INTO `post_reply_floor` VALUES (12, 1, 1, 10000, '888', 8, b'0', '2020-06-15 17:15:58');
INSERT INTO `post_reply_floor` VALUES (13, 1, 11, 10000, '123', 1, b'0', '2020-06-16 11:40:03');
INSERT INTO `post_reply_floor` VALUES (14, 1, 11, 10000, '6666', 2, b'0', '2020-06-16 11:40:11');
INSERT INTO `post_reply_floor` VALUES (15, 1, 11, 10000, '111', 3, b'0', '2020-06-16 11:41:07');
INSERT INTO `post_reply_floor` VALUES (16, 1, 11, 10000, '111', 4, b'0', '2020-06-16 11:42:41');
INSERT INTO `post_reply_floor` VALUES (17, 1, 1, 10000, '321', 9, b'0', '2020-06-18 16:36:50');
INSERT INTO `post_reply_floor` VALUES (18, 1, 50, 10000, '这个完美的楼中楼', 1, b'0', '2020-06-20 18:15:59');
INSERT INTO `post_reply_floor` VALUES (19, 1, 50, 10000, '完美的楼中楼', 2, b'0', '2020-06-20 18:16:23');
INSERT INTO `post_reply_floor` VALUES (20, 1, 50, 10000, '完美的楼中楼', 3, b'0', '2020-06-20 18:16:52');
INSERT INTO `post_reply_floor` VALUES (21, 1, 50, 10000, '完美的楼中楼', 4, b'0', '2020-06-20 18:17:30');
INSERT INTO `post_reply_floor` VALUES (22, 1, 51, 10000, '@Login', 1, b'0', '2020-06-20 18:20:12');
INSERT INTO `post_reply_floor` VALUES (23, 19, 52, 10002, '123', 1, b'0', '2020-06-22 16:57:55');
INSERT INTO `post_reply_floor` VALUES (24, 1, 1, 10002, '123', 10, b'0', '2020-06-22 16:58:03');
INSERT INTO `post_reply_floor` VALUES (25, 31, 54, 10000, '回复啦', 1, b'0', '2020-06-23 15:25:55');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `passWord` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `headUrl` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `isFrozen` bit(1) NOT NULL,
  `del` bit(1) NOT NULL COMMENT '是否删除',
  `createTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10000, 'wstsummer', 'e10adc3949ba59abbe56e057f20f883e', 'wst', NULL, b'0', b'0', '2020-07-03 18:47:19', '2020-07-03 18:47:19');
INSERT INTO `user` VALUES (10007, 'wst', 'e10adc3949ba59abbe56e057f20f883e', '小东西', NULL, b'0', b'0', '2020-07-03 19:20:42', '2020-07-03 19:20:42');

-- ----------------------------
-- Table structure for user_details
-- ----------------------------
DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `sex` bit(2) NOT NULL COMMENT '年龄 0保密 1男 2女',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人签名',
  `updateTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_details
-- ----------------------------
INSERT INTO `user_details` VALUES (10000, b'10', '2020-07-27 00:00:00', '昨天不是刚胖今天怎么又胖了呢', '2020-07-03 18:47:19');
INSERT INTO `user_details` VALUES (10007, b'00', '2020-08-04 00:00:00', '呜呜呜呜', '2020-07-03 19:20:42');

-- ----------------------------
-- Table structure for user_ip
-- ----------------------------
DROP TABLE IF EXISTS `user_ip`;
CREATE TABLE `user_ip`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(11) NOT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_ip
-- ----------------------------
INSERT INTO `user_ip` VALUES (44, 10004, '192.168.50.243', 1004, '2020-07-02 18:27:35');
INSERT INTO `user_ip` VALUES (45, 10004, '192.168.50.243', 1005, '2020-07-02 18:27:35');
INSERT INTO `user_ip` VALUES (46, 10004, '192.168.50.243', 1005, '2020-07-02 18:46:12');
INSERT INTO `user_ip` VALUES (47, 10005, '192.168.50.243', 1004, '2020-07-02 18:46:25');
INSERT INTO `user_ip` VALUES (48, 10005, '192.168.50.243', 1005, '2020-07-02 18:46:25');
INSERT INTO `user_ip` VALUES (49, 10006, '192.168.50.243', 1004, '2020-07-02 18:47:01');
INSERT INTO `user_ip` VALUES (50, 10006, '192.168.50.243', 1005, '2020-07-02 18:47:01');
INSERT INTO `user_ip` VALUES (51, 10000, '192.168.50.243', 1005, '2020-07-02 18:55:46');
INSERT INTO `user_ip` VALUES (52, 10000, '192.168.50.243', 1005, '2020-07-03 10:24:40');
INSERT INTO `user_ip` VALUES (53, 10000, '192.168.50.243', 1005, '2020-07-03 18:56:20');
INSERT INTO `user_ip` VALUES (54, 10007, '192.168.50.243', 1004, '2020-07-03 19:15:38');
INSERT INTO `user_ip` VALUES (55, 10007, '192.168.50.243', 1005, '2020-07-03 19:15:38');
INSERT INTO `user_ip` VALUES (56, 10000, '192.168.50.243', 1005, '2020-07-03 19:21:06');
INSERT INTO `user_ip` VALUES (57, 10000, '192.168.50.243', 1005, '2020-07-03 19:37:11');
INSERT INTO `user_ip` VALUES (58, 10007, '192.168.50.243', 1005, '2020-07-03 19:37:43');
INSERT INTO `user_ip` VALUES (59, 10007, '192.168.50.243', 1005, '2020-07-03 19:38:27');
INSERT INTO `user_ip` VALUES (60, 10000, '192.168.50.243', 1005, '2020-07-03 19:38:45');
INSERT INTO `user_ip` VALUES (61, 10000, '192.168.50.243', 1005, '2020-07-03 19:39:35');
INSERT INTO `user_ip` VALUES (62, 10007, '192.168.50.243', 1005, '2020-07-03 19:39:44');
INSERT INTO `user_ip` VALUES (63, 10000, '192.168.50.243', 1005, '2020-07-03 19:41:05');
INSERT INTO `user_ip` VALUES (64, 10007, '192.168.50.243', 1005, '2020-07-03 19:43:08');

SET FOREIGN_KEY_CHECKS = 1;
