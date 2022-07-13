/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 13/07/2022 09:21:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_friend
-- ----------------------------
DROP TABLE IF EXISTS `blog_friend`;
CREATE TABLE `blog_friend`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客地址',
  `blog_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客名称',
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `blog_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客图片',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语录',
  `is_status` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '逻辑删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1546825028683497474 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '友链表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for blog_message
-- ----------------------------
DROP TABLE IF EXISTS `blog_message`;
CREATE TABLE `blog_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for blog_music
-- ----------------------------
DROP TABLE IF EXISTS `blog_music`;
CREATE TABLE `blog_music`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `music_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音乐名称',
  `music_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音乐编号',
  `music_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音乐地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10004 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '音乐表' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for blog_picture
-- ----------------------------
DROP TABLE IF EXISTS `blog_picture`;
CREATE TABLE `blog_picture`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图片主键',
  `picture_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `picture_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `picture_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片内容',
  `picture_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片时间',
  `picture_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '级别',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1545813878621663235 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '相册' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for blog_upload
-- ----------------------------
DROP TABLE IF EXISTS `blog_upload`;
CREATE TABLE `blog_upload`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `picture_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `picture_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片内容',
  `picture_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `picture_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片时间',
  `picture_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '相册' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `type` bigint(20) NULL DEFAULT NULL COMMENT '类型',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '化名',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_user
-- ----------------------------
INSERT INTO `blog_user` VALUES (1, 1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '张三', '120@cs.p', '2022-02-03 18:58:44', '2022-02-10 18:58:38');

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实名称',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `is_status` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '状态',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '令牌',
  `last_login_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次登录地址',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '逻辑删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (1, 1544560347008778241, 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '11@11.co', 'https://img.slcp.top/bg.jpg', 1, '108:RCJ2MQ==8KD', 'CHINA', '2022-07-12 19:50:31', '2022-07-06 03:14:34', '2022-07-06 03:14:34', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_attendance
-- ----------------------------
DROP TABLE IF EXISTS `sys_attendance`;
CREATE TABLE `sys_attendance`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `length` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '时长',
  `date` datetime NOT NULL COMMENT '迟到时间',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for sys_report
-- ----------------------------
DROP TABLE IF EXISTS `sys_report`;
CREATE TABLE `sys_report`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `count` bigint(20) NULL DEFAULT NULL COMMENT '用户数',
  `academy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言',
  `date` datetime NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_report
-- ----------------------------
INSERT INTO `sys_report` VALUES (1, 4, 'C', '2020-12-26 00:00:00');
INSERT INTO `sys_report` VALUES (2, 21, 'Go', '2020-12-26 00:00:00');
INSERT INTO `sys_report` VALUES (3, 14, 'Python', '2020-12-26 00:00:00');
INSERT INTO `sys_report` VALUES (4, 10, 'C++', '2020-12-26 00:00:00');
INSERT INTO `sys_report` VALUES (5, 50, 'Java', '2020-12-26 00:00:00');
INSERT INTO `sys_report` VALUES (6, 25, 'C', '2020-12-27 00:00:00');
INSERT INTO `sys_report` VALUES (8, 32, 'Go', '2020-12-27 00:00:00');
INSERT INTO `sys_report` VALUES (9, 21, 'Python', '2020-12-27 00:00:00');
INSERT INTO `sys_report` VALUES (10, 14, 'C++', '2020-12-27 00:00:00');
INSERT INTO `sys_report` VALUES (11, 79, 'Java', '2020-12-27 00:00:00');
INSERT INTO `sys_report` VALUES (12, 20, 'C', '2020-12-28 00:00:00');
INSERT INTO `sys_report` VALUES (13, 50, 'Go', '2020-12-28 00:00:00');
INSERT INTO `sys_report` VALUES (14, 43, 'Python', '2020-12-28 00:00:00');
INSERT INTO `sys_report` VALUES (15, 35, 'C++', '2020-12-28 00:00:00');
INSERT INTO `sys_report` VALUES (16, 146, 'Java', '2020-12-28 00:00:00');
INSERT INTO `sys_report` VALUES (17, 35, 'C', '2020-12-29 00:00:00');
INSERT INTO `sys_report` VALUES (18, 41, 'Go', '2020-12-29 00:00:00');
INSERT INTO `sys_report` VALUES (19, 33, 'Python', '2020-12-29 00:00:00');
INSERT INTO `sys_report` VALUES (20, 23, 'C++', '2020-12-29 00:00:00');
INSERT INTO `sys_report` VALUES (21, 102, 'Java', '2020-12-29 00:00:00');
INSERT INTO `sys_report` VALUES (22, 11, 'C', '2020-12-30 00:00:00');
INSERT INTO `sys_report` VALUES (23, 75, 'Go', '2020-12-30 00:00:00');
INSERT INTO `sys_report` VALUES (24, 66, 'Python', '2020-12-30 00:00:00');
INSERT INTO `sys_report` VALUES (25, 40, 'C++', '2020-12-30 00:00:00');
INSERT INTO `sys_report` VALUES (26, 211, 'Java', '2020-12-30 00:00:00');
INSERT INTO `sys_report` VALUES (27, 9, 'C', '2021-12-31 00:00:00');
INSERT INTO `sys_report` VALUES (28, 90, 'Go', '2021-12-31 00:00:00');
INSERT INTO `sys_report` VALUES (29, 88, 'Python', '2021-12-31 00:00:00');
INSERT INTO `sys_report` VALUES (30, 91, 'C++', '2021-12-31 00:00:00');
INSERT INTO `sys_report` VALUES (31, 312, 'Java', '2021-12-31 00:00:00');

-- ----------------------------
-- Table structure for sys_rights
-- ----------------------------
DROP TABLE IF EXISTS `sys_rights`;
CREATE TABLE `sys_rights`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `auth_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `level` int(11) NOT NULL COMMENT '级别',
  `path` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_rights
-- ----------------------------
INSERT INTO `sys_rights` VALUES (1, 1, '用户管理', 0, 'users');
INSERT INTO `sys_rights` VALUES (2, 1, '添加用户', 2, 'users');
INSERT INTO `sys_rights` VALUES (3, 1, '删除用户', 2, 'users');
INSERT INTO `sys_rights` VALUES (4, 1, '更新用户', 2, 'users');
INSERT INTO `sys_rights` VALUES (5, 1, '用户列表', 1, 'users');
INSERT INTO `sys_rights` VALUES (6, 1, '权限管理', 0, 'rights');
INSERT INTO `sys_rights` VALUES (7, 3, '文章管理', 0, 'blog');
INSERT INTO `sys_rights` VALUES (8, 1, '修改用户状态', 2, 'users');
INSERT INTO `sys_rights` VALUES (9, 1, '分配用户角色', 2, 'users');
INSERT INTO `sys_rights` VALUES (10, 3, '文章列表', 1, 'blog');
INSERT INTO `sys_rights` VALUES (11, 3, '数据统计', 0, 'reports');
INSERT INTO `sys_rights` VALUES (12, 3, '数据报表', 1, 'reports');
INSERT INTO `sys_rights` VALUES (13, 3, '查看数据', 2, 'reports');
INSERT INTO `sys_rights` VALUES (14, 1, '角色列表', 1, 'roles');
INSERT INTO `sys_rights` VALUES (15, 1, '权限列表', 1, 'rights');
INSERT INTO `sys_rights` VALUES (16, 1, '添加角色', 2, 'roles');
INSERT INTO `sys_rights` VALUES (17, 1, '删除角色', 2, 'roles');
INSERT INTO `sys_rights` VALUES (18, 1, '更新角色', 2, 'roles');
INSERT INTO `sys_rights` VALUES (19, 1, '角色授权', 2, 'roles');
INSERT INTO `sys_rights` VALUES (20, 2, '文章信息修改', 2, 'blog');
INSERT INTO `sys_rights` VALUES (21, 2, '文章信息删除', 2, 'blog');
INSERT INTO `sys_rights` VALUES (22, 3, '获取文章详情', 2, 'blog');
INSERT INTO `sys_rights` VALUES (23, 1, '取消角色授权', 2, 'roles');
INSERT INTO `sys_rights` VALUES (24, 1, '获取角色列表', 2, 'roles');
INSERT INTO `sys_rights` VALUES (25, 1, '获取角色详情', 2, 'roles');
INSERT INTO `sys_rights` VALUES (26, 1, '更新角色信息', 2, 'roles');
INSERT INTO `sys_rights` VALUES (27, 1, '更新角色权限', 2, 'roles');
INSERT INTO `sys_rights` VALUES (28, 2, '添加文章信息', 2, 'add');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `rights_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色介绍',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (3, 1, '作者2', '主管人员', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (4, 3, '作者3', '学者', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (5, 2, '作者4', '公职人员', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (6, 3, '作者5', '教书者', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (1544560347008778241, 1, 'admin', '管理员', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '分类id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  `first_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `published` tinyint(1) NOT NULL COMMENT '公开',
  `appreciation` tinyint(1) NOT NULL COMMENT '赞赏',
  `comment` tinyint(1) NOT NULL COMMENT '评论',
  `recommend` tinyint(1) NOT NULL COMMENT '推荐',
  `share_statement` tinyint(1) NOT NULL COMMENT '转载声明',
  `top` bigint(2) NULL DEFAULT 0 COMMENT '置顶',
  `flag` tinyint(1) NULL DEFAULT NULL COMMENT '文章类型',
  `views` bigint(20) NULL DEFAULT 3421 COMMENT '访问次数',
  `comment_count` bigint(20) NULL DEFAULT NULL COMMENT '评论次数',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1546319612291674114 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博文' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论主键',
  `blog_id` bigint(20) NULL DEFAULT NULL COMMENT '博客id',
  `parent_comment_id` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '化名',
  `admin_comment` bit(1) NOT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `parent_message_id` bigint(20) NULL DEFAULT NULL,
  `admin_message` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '逻辑删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10011 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for tag_blog_rel
-- ----------------------------
DROP TABLE IF EXISTS `tag_blog_rel`;
CREATE TABLE `tag_blog_rel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tid` bigint(20) NOT NULL,
  `bid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tid`(`bid`, `tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1546765396326572035 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
