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
CREATE TABLE `blog_friend` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `blog_address` varchar(50) NOT NULL COMMENT '博客地址',
                             `blog_name` varchar(50) NOT NULL COMMENT '博客名称',
                             `head_img` varchar(255) NOT NULL COMMENT '头像',
                             `blog_img` varchar(255) DEFAULT NULL COMMENT '博客图片',
                             `description` varchar(255) DEFAULT NULL COMMENT '语录',
                             `is_status` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '状态',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `is_deleted` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '逻辑删除标识',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1546825028683497474 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='友链表';

-- ----------------------------
-- Records of blog_friend
-- ----------------------------
BEGIN;
INSERT INTO `blog_friend` (`id`, `blog_address`, `blog_name`, `head_img`, `blog_img`, `description`, `is_status`, `create_time`, `update_time`, `is_deleted`) VALUES (1001, 'https://slcp.top', 'Slcpの童话镇', 'https://img.slcp.top/bg.jpg', 'https://img.slcp.top/slcpH.png', '你若盛开,阳光自来。', 1, '2022-03-01 10:38:50', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for blog_message
-- ----------------------------
DROP TABLE IF EXISTS `blog_message`;
CREATE TABLE `blog_message` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `content` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_message
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for blog_music
-- ----------------------------
DROP TABLE IF EXISTS `blog_music`;
CREATE TABLE `blog_music` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `music_name` varchar(20) DEFAULT NULL COMMENT '音乐名称',
                            `music_code` varchar(20) DEFAULT NULL COMMENT '音乐编号',
                            `music_path` varchar(255) DEFAULT NULL COMMENT '音乐地址',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='音乐表';

-- ----------------------------
-- Records of blog_music
-- ----------------------------
BEGIN;
INSERT INTO `blog_music` (`id`, `music_name`, `music_code`, `music_path`, `create_time`, `update_time`) VALUES (10004, '童话镇-暗杠', '413829859', '//music.163.com/outchain/player?type=2&id= 413829859&auto=1&height=66', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for blog_picture
-- ----------------------------
DROP TABLE IF EXISTS `blog_picture`;
CREATE TABLE `blog_picture` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图片主键',
                              `picture_name` varchar(255) DEFAULT NULL COMMENT '图片名称',
                              `picture_address` varchar(255) DEFAULT NULL COMMENT '图片地址',
                              `picture_description` varchar(255) DEFAULT NULL COMMENT '图片内容',
                              `picture_time` varchar(255) DEFAULT NULL COMMENT '图片时间',
                              `picture_level` varchar(255) DEFAULT NULL COMMENT '级别',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `is_deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除标识',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1545813878621663235 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='相册';

-- ----------------------------
-- Records of blog_picture
-- ----------------------------
BEGIN;
INSERT INTO `blog_picture` (`id`, `picture_name`, `picture_address`, `picture_description`, `picture_time`, `picture_level`, `create_time`, `update_time`, `is_deleted`) VALUES (1, '自然风光', 'https://img.png', '自然风光', '1', '1', NULL, NULL, NULL);
INSERT INTO `blog_picture` (`id`, `picture_name`, `picture_address`, `picture_description`, `picture_time`, `picture_level`, `create_time`, `update_time`, `is_deleted`) VALUES (2, '自然风光', 'https://img.png', '自然风光', '1', '2', NULL, NULL, NULL);
INSERT INTO `blog_picture` (`id`, `picture_name`, `picture_address`, `picture_description`, `picture_time`, `picture_level`, `create_time`, `update_time`, `is_deleted`) VALUES (3, '自然风光', 'https://img.png', '自然风光', '1', '1', NULL, NULL, NULL);
INSERT INTO `blog_picture` (`id`, `picture_name`, `picture_address`, `picture_description`, `picture_time`, `picture_level`, `create_time`, `update_time`, `is_deleted`) VALUES (4, '自然风光', 'https://img.png', '自然风光', '1', '2', NULL, NULL, NULL);
INSERT INTO `blog_picture` (`id`, `picture_name`, `picture_address`, `picture_description`, `picture_time`, `picture_level`, `create_time`, `update_time`, `is_deleted`) VALUES (5, '自然风光', 'https://img.png', '自然风光', '1', '1', NULL, NULL, NULL);
INSERT INTO `blog_picture` (`id`, `picture_name`, `picture_address`, `picture_description`, `picture_time`, `picture_level`, `create_time`, `update_time`, `is_deleted`) VALUES (6, '自然风光', 'https://img.png', '自然风光', '1', '3', NULL, NULL, NULL);
INSERT INTO `blog_picture` (`id`, `picture_name`, `picture_address`, `picture_description`, `picture_time`, `picture_level`, `create_time`, `update_time`, `is_deleted`) VALUES (7, '城堡', 'https://img.png', '城堡', '1', '1', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for blog_upload
-- ----------------------------
DROP TABLE IF EXISTS `blog_upload`;
CREATE TABLE `blog_upload` (
                             `id` bigint(20) NOT NULL COMMENT '主键',
                             `picture_address` varchar(255) DEFAULT NULL COMMENT '图片地址',
                             `picture_description` varchar(255) DEFAULT NULL COMMENT '图片内容',
                             `picture_name` varchar(255) DEFAULT NULL COMMENT '图片名称',
                             `picture_time` varchar(255) DEFAULT NULL COMMENT '图片时间',
                             `picture_path` varchar(255) DEFAULT NULL COMMENT '图片地址',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='相册';

-- ----------------------------
-- Records of blog_upload
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
                           `type` bigint(20) DEFAULT NULL COMMENT '类型',
                           `username` varchar(50) DEFAULT NULL COMMENT '账号',
                           `password` varchar(50) DEFAULT NULL COMMENT '密码',
                           `avatar` varchar(50) DEFAULT NULL COMMENT '化名',
                           `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
                           `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                           `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                           `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of blog_user
-- ----------------------------
BEGIN;
INSERT INTO `blog_user` (`id`, `type`, `username`, `password`, `avatar`, `nickname`, `email`, `create_time`, `update_time`) VALUES (1, 1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '张三', '120@cs.p', '2022-02-03 18:58:44', '2022-02-10 18:58:38');
COMMIT;

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `rid` bigint(20) DEFAULT NULL COMMENT '角色id',
                           `nickname` varchar(20) DEFAULT NULL COMMENT '真实名称',
                           `username` varchar(50) NOT NULL COMMENT '账号',
                           `password` varchar(32) NOT NULL COMMENT '密码',
                           `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                           `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
                           `is_status` tinyint(1) unsigned zerofill NOT NULL COMMENT '状态',
                           `token` varchar(255) NOT NULL COMMENT '令牌',
                           `last_login_address` varchar(255) DEFAULT NULL COMMENT '上次登录地址',
                           `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
                           `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                           `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                           `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
                           `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
                           `is_deleted` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '逻辑删除标识',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
BEGIN;
INSERT INTO `sys_admin` (`id`, `rid`, `nickname`, `username`, `password`, `email`, `avatar`, `is_status`, `token`, `last_login_address`, `last_login_time`, `create_time`, `update_time`, `create_by`, `update_by`, `is_deleted`) VALUES (1, 1544560347008778241, 'admin', 'admin', '96e79218965eb72c92a549dd5a330112', '11@11.co', 'https://img.slcp.top/bg.jpg', 1, '108:RCJ2MQ==8KD', 'CHINA', '2022-07-12 19:50:31', '2022-07-06 03:14:34', '2022-07-06 03:14:34', NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_attendance
-- ----------------------------
DROP TABLE IF EXISTS `sys_attendance`;
CREATE TABLE `sys_attendance` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `uid` bigint(20) NOT NULL COMMENT '用户id',
                                `name` varchar(50) NOT NULL COMMENT '姓名',
                                `type` varchar(50) NOT NULL COMMENT '类型',
                                `length` varchar(50) NOT NULL COMMENT '时长',
                                `date` datetime NOT NULL COMMENT '迟到时间',
                                `remark` varchar(100) NOT NULL COMMENT '原因',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_attendance
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_report
-- ----------------------------
DROP TABLE IF EXISTS `sys_report`;
CREATE TABLE `sys_report` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `count` bigint(20) DEFAULT NULL COMMENT '用户数',
                            `academy` varchar(50) DEFAULT NULL COMMENT '语言',
                            `date` datetime DEFAULT NULL COMMENT '时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_report
-- ----------------------------
BEGIN;
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (1, 4, 'C', '2020-12-26 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (2, 21, 'Go', '2020-12-26 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (3, 14, 'Python', '2020-12-26 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (4, 10, 'C++', '2020-12-26 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (5, 50, 'Java', '2020-12-26 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (6, 25, 'C', '2020-12-27 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (8, 32, 'Go', '2020-12-27 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (9, 21, 'Python', '2020-12-27 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (10, 14, 'C++', '2020-12-27 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (11, 79, 'Java', '2020-12-27 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (12, 20, 'C', '2020-12-28 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (13, 50, 'Go', '2020-12-28 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (14, 43, 'Python', '2020-12-28 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (15, 35, 'C++', '2020-12-28 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (16, 146, 'Java', '2020-12-28 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (17, 35, 'C', '2020-12-29 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (18, 41, 'Go', '2020-12-29 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (19, 33, 'Python', '2020-12-29 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (20, 23, 'C++', '2020-12-29 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (21, 102, 'Java', '2020-12-29 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (22, 11, 'C', '2020-12-30 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (23, 75, 'Go', '2020-12-30 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (24, 66, 'Python', '2020-12-30 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (25, 40, 'C++', '2020-12-30 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (26, 211, 'Java', '2020-12-30 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (27, 9, 'C', '2021-12-31 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (28, 90, 'Go', '2021-12-31 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (29, 88, 'Python', '2021-12-31 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (30, 91, 'C++', '2021-12-31 00:00:00');
INSERT INTO `sys_report` (`id`, `count`, `academy`, `date`) VALUES (31, 312, 'Java', '2021-12-31 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_rights
-- ----------------------------
DROP TABLE IF EXISTS `sys_rights`;
CREATE TABLE `sys_rights` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `rid` bigint(20) DEFAULT NULL COMMENT '角色id',
                            `auth_name` varchar(20) NOT NULL COMMENT '权限名称',
                            `level` int(11) NOT NULL COMMENT '级别',
                            `path` varchar(20) NOT NULL COMMENT '路径',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_rights
-- ----------------------------
BEGIN;
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (1, 1, '用户管理', 0, 'users');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (2, 1, '添加用户', 2, 'users');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (3, 1, '删除用户', 2, 'users');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (4, 1, '更新用户', 2, 'users');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (5, 1, '用户列表', 1, 'users');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (6, 1, '权限管理', 0, 'rights');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (7, 3, '文章管理', 0, 'blog');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (8, 1, '修改用户状态', 2, 'users');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (9, 1, '分配用户角色', 2, 'users');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (10, 3, '文章列表', 1, 'blog');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (11, 3, '数据统计', 0, 'reports');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (12, 3, '数据报表', 1, 'reports');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (13, 3, '查看数据', 2, 'reports');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (14, 1, '角色列表', 1, 'roles');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (15, 1, '权限列表', 1, 'rights');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (16, 1, '添加角色', 2, 'roles');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (17, 1, '删除角色', 2, 'roles');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (18, 1, '更新角色', 2, 'roles');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (19, 1, '角色授权', 2, 'roles');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (20, 2, '文章信息修改', 2, 'blog');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (21, 2, '文章信息删除', 2, 'blog');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (22, 3, '获取文章详情', 2, 'blog');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (23, 1, '取消角色授权', 2, 'roles');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (24, 1, '获取角色列表', 2, 'roles');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (25, 1, '获取角色详情', 2, 'roles');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (26, 1, '更新角色信息', 2, 'roles');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (27, 1, '更新角色权限', 2, 'roles');
INSERT INTO `sys_rights` (`id`, `rid`, `auth_name`, `level`, `path`) VALUES (28, 2, '添加文章信息', 2, 'add');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                          `id` bigint(20) NOT NULL COMMENT '主键',
                          `rights_id` bigint(20) DEFAULT NULL COMMENT '权限id',
                          `role_name` varchar(20) NOT NULL COMMENT '角色名称',
                          `role_desc` varchar(30) NOT NULL COMMENT '角色介绍',
                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                          `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                          `is_deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除标识',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `rights_id`, `role_name`, `role_desc`, `create_time`, `update_time`, `is_deleted`) VALUES (3, 1, '作者2', '主管人员', NULL, NULL, NULL);
INSERT INTO `sys_role` (`id`, `rights_id`, `role_name`, `role_desc`, `create_time`, `update_time`, `is_deleted`) VALUES (4, 3, '作者3', '学者', NULL, NULL, NULL);
INSERT INTO `sys_role` (`id`, `rights_id`, `role_name`, `role_desc`, `create_time`, `update_time`, `is_deleted`) VALUES (5, 2, '作者4', '公职人员', NULL, NULL, NULL);
INSERT INTO `sys_role` (`id`, `rights_id`, `role_name`, `role_desc`, `create_time`, `update_time`, `is_deleted`) VALUES (6, 3, '作者5', '教书者', NULL, NULL, NULL);
INSERT INTO `sys_role` (`id`, `rights_id`, `role_name`, `role_desc`, `create_time`, `update_time`, `is_deleted`) VALUES (1544560347008778241, 1, 'admin', '管理员', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客id',
                        `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
                        `type_id` bigint(20) DEFAULT NULL COMMENT '分类id',
                        `title` varchar(255) DEFAULT NULL COMMENT '标题',
                        `content` longtext COMMENT '内容',
                        `description` varchar(255) NOT NULL COMMENT '描述',
                        `first_picture` varchar(255) DEFAULT NULL COMMENT '图片路径',
                        `published` tinyint(1) NOT NULL COMMENT '公开',
                        `appreciation` tinyint(1) NOT NULL COMMENT '赞赏',
                        `comment` tinyint(1) NOT NULL COMMENT '评论',
                        `recommend` tinyint(1) NOT NULL COMMENT '推荐',
                        `share_statement` tinyint(1) NOT NULL COMMENT '转载声明',
                        `top` bigint(2) DEFAULT '0' COMMENT '置顶',
                        `flag` tinyint(1) DEFAULT NULL COMMENT '文章类型',
                        `views` bigint(20) DEFAULT '3421' COMMENT '访问次数',
                        `comment_count` bigint(20) DEFAULT NULL COMMENT '评论次数',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        `is_deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除标识',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1546319612291674114 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='博文';

-- ----------------------------
-- Records of t_blog
-- ----------------------------
BEGIN;
INSERT INTO `t_blog` (`id`, `user_id`, `type_id`, `title`, `content`, `description`, `first_picture`, `published`, `appreciation`, `comment`, `recommend`, `share_statement`, `top`, `flag`, `views`, `comment_count`, `create_time`, `update_time`, `is_deleted`) VALUES (1, 1, 1, 'MarkDown文本编译器及必备语法', '## MarkDown文本编译器\r\n\r\n推荐文本编译器：Typora\r\n\r\n## Typora下载和安装\r\n\r\n### 下载\r\n\r\n[Typora官网](https://www.typora.io/)\r\n\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20200726184835321.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1N1bnNoaW5lX01yX1N1bg==,size_16,color_FFFFFF,t_70)\r\n\r\n* 根据自己的电脑的系统下载对应的Typora。\r\n\r\n### 安装\r\n安装依次往下点就ok。\r\n## Typora常用语法\r\n\r\n### 标题\r\n\r\n标题是以` # + 空格`\r\n\r\n~~~ \r\n# 一级标题\r\n## 二级标题\r\n### 三级标题\r\n...\r\n###### 六级标题\r\n~~~\r\n\r\n\r\n\r\n### 字体样式\r\n\r\n`斜体`：用`单星号`(*)包裹。 \r\n\r\n```\r\n*内容*\r\n```\r\n\r\n`粗体`：用`双星号`(*)包裹。\r\n\r\n```\r\n**内容**\r\n```\r\n\r\n`粗斜体`：用`三个星号`(*)包裹。\r\n\r\n```\r\n***内容***\r\n```\r\n\r\n\r\n\r\n### 链接\r\n\r\n(1) `图片链接`：\r\n\r\n```\r\n![填入图片名称](填入图片路径)\r\n```\r\n\r\n\r\n\r\n(2) `超链接`：\r\n\r\n```\r\n[超链接名称](超链接路径)\r\n```\r\n\r\n\r\n\r\n### 引用\r\n\r\n引用是通过` > `来实现的：\r\n\r\n```\r\n> 内容\r\n```\r\n\r\n\r\n\r\n### 列表\r\n\r\n`有序列表： `\r\n\r\n```\r\n1. + 空格来实现，然后根据回车(Enter)来增加一列。\r\n```\r\n\r\n`无序列表：`\r\n\r\n```\r\n* + 空格来实现，然后回车可以增加一列。\r\n```\r\n\r\n\r\n\r\n### 表格\r\n第一种方法：可以`右键选择==>插入==>表格`\r\n第二种方法：根据相对于的横杠(`|`)来创建。如：\r\n\r\n```\r\n姓名|性别|时间\r\n--|--|--\r\n张三|男|2020.7.26\r\n```\r\n\r\n**注意：** 如果它们之间有空行，那么要打开源代码模式去除中间的空行，才能实现效果。\r\n\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20200726185238432.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1N1bnNoaW5lX01yX1N1bg==,size_16,color_FFFFFF,t_70)\r\n\r\n\r\n### 代码\r\n\r\n~~~\r\n通过~~~或者```可以打开代码模式，并且可以选择语言。\r\n~~~\r\n\r\n### ...\r\n等等', 'Markdown是一种轻量级标记语言，创始人为约翰·格鲁伯（英语：John Gruber）。 它允许人们使用易读易写的纯文本格式编写文档，然后转换成有效的XHTML（或者HTML）文档。这种语言吸收了很多在电子邮件中已有的纯文本标记的特性。', 'https://img.slcp.top/2022-10-09-ec9edb-824373', 1, 0, 0, 0, 0, 0, 1, 423, 0, '2019-03-16 20:41:06', '2019-03-16 20:41:06', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论主键',
                           `blog_id` bigint(20) DEFAULT NULL COMMENT '博客id',
                           `parent_comment_id` bigint(20) DEFAULT NULL COMMENT '父id',
                           `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
                           `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                           `content` varchar(255) DEFAULT NULL COMMENT '内容',
                           `avatar` varchar(255) DEFAULT NULL COMMENT '化名',
                           `admin_comment` bit(1) NOT NULL,
                           `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='评论表';

-- ----------------------------
-- Records of t_comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
                           `id` int(20) NOT NULL AUTO_INCREMENT,
                           `nickname` varchar(255) DEFAULT NULL,
                           `email` varchar(255) DEFAULT NULL,
                           `content` varchar(255) DEFAULT NULL,
                           `avatar` varchar(255) DEFAULT NULL,
                           `create_time` datetime DEFAULT NULL,
                           `parent_message_id` bigint(20) DEFAULT NULL,
                           `admin_message` bit(1) NOT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_message
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
                       `id` bigint(20) NOT NULL COMMENT 'id',
                       `name` varchar(20) NOT NULL COMMENT '标签名',
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='标签';

-- ----------------------------
-- Records of t_tag
-- ----------------------------
BEGIN;
INSERT INTO `t_tag` (`id`, `name`) VALUES (1, '杂文');
COMMIT;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                        `name` varchar(20) NOT NULL COMMENT '类别名',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        `is_deleted` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '逻辑删除标识',
                        PRIMARY KEY (`id`) USING BTREE,
                        KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10011 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='分类';

-- ----------------------------
-- Records of t_type
-- ----------------------------
BEGIN;
INSERT INTO `t_type` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (1, 'Java', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tag_blog_rel
-- ----------------------------
DROP TABLE IF EXISTS `tag_blog_rel`;
CREATE TABLE `tag_blog_rel` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `tid` bigint(20) NOT NULL,
                              `bid` bigint(20) NOT NULL,
                              PRIMARY KEY (`id`) USING BTREE,
                              KEY `tid` (`bid`,`tid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1546765396326572035 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tag_blog_rel
-- ----------------------------
BEGIN;
INSERT INTO `tag_blog_rel` (`id`, `tid`, `bid`) VALUES (1, 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
