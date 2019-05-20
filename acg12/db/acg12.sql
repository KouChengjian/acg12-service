/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : acg12

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-05-20 12:19:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for acg_bangumi_video
-- ----------------------------
DROP TABLE IF EXISTS `acg_bangumi_video`;
CREATE TABLE `acg_bangumi_video` (
  `vId` int(11) NOT NULL AUTO_INCREMENT,
  `bangumitId` int(11) NOT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `indexNum` varchar(255) DEFAULT NULL,
  `indexTitle` varchar(255) DEFAULT NULL,
  `danmaku` varchar(255) DEFAULT NULL,
  `bilibiliUrl` varchar(255) DEFAULT NULL,
  `baiduyunUrl` varchar(255) DEFAULT NULL,
  `aiqiyiUrl` varchar(255) DEFAULT NULL,
  `createTime` int(11) DEFAULT NULL,
  `updateTime` int(11) DEFAULT NULL,
  PRIMARY KEY (`vId`)
) ENGINE=InnoDB AUTO_INCREMENT=325 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for installation
-- ----------------------------
DROP TABLE IF EXISTS `installation`;
CREATE TABLE `installation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceType` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `installationId` varchar(255) NOT NULL,
  `createdAt` int(11) DEFAULT NULL,
  `updatedAt` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_acg12_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_banner`;
CREATE TABLE `t_acg12_banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `type` int(11) NOT NULL COMMENT '类型',
  `type_name` varchar(100) DEFAULT NULL COMMENT '类型名',
  `is_lock` int(11) DEFAULT NULL COMMENT '锁定',
  `cover` varchar(255) DEFAULT NULL COMMENT '图片',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='banner';

-- ----------------------------
-- Table structure for t_acg12_character
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_character`;
CREATE TABLE `t_acg12_character` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `c_id` int(11) DEFAULT NULL COMMENT 'c_id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `name_cn` varchar(255) DEFAULT NULL COMMENT '中文名称',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `summary` varchar(4000) DEFAULT NULL COMMENT '简介',
  `height` varchar(250) DEFAULT NULL COMMENT '身高',
  `weight` varchar(255) DEFAULT NULL COMMENT '体重',
  `alias` varchar(350) DEFAULT NULL COMMENT '别名',
  `type` varchar(255) DEFAULT NULL COMMENT '类型 1、角色 2、机体 3、舰船 4、组织机构',
  `gender` int(11) DEFAULT NULL COMMENT '性别 1：男 2：女',
  `bloodtype` int(11) DEFAULT NULL COMMENT '血型  1、A   2、B   3、AB   4、O',
  `birthday` varchar(255) DEFAULT NULL COMMENT '生日',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1909 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_character_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_character_detail`;
CREATE TABLE `t_acg12_character_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `character_id` int(11) DEFAULT NULL COMMENT 'character_id',
  `other_title` varchar(255) DEFAULT NULL COMMENT 'title',
  `other_value` varchar(255) DEFAULT NULL COMMENT 'value',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3699 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_collect_album
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_collect_album`;
CREATE TABLE `t_acg12_collect_album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL,
  `pin_id` varchar(255) DEFAULT NULL COMMENT '图片id',
  `image` varchar(255) DEFAULT NULL COMMENT '图片url',
  `link_url` varchar(255) DEFAULT NULL COMMENT '外链',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `love` int(11) DEFAULT NULL COMMENT '点赞',
  `favorites` int(11) DEFAULT NULL COMMENT '采集',
  `res_width` int(11) DEFAULT NULL COMMENT '资源宽度',
  `res_hight` int(11) DEFAULT NULL COMMENT '资源高度',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_collect_caricature
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_collect_caricature`;
CREATE TABLE `t_acg12_collect_caricature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'userId',
  `comic_id` bigint(20) DEFAULT NULL COMMENT 'comicId',
  `type` int(11) DEFAULT NULL COMMENT '类型 1:酷克',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_collect_palette
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_collect_palette`;
CREATE TABLE `t_acg12_collect_palette` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'userId',
  `board_id` varchar(255) DEFAULT NULL COMMENT 'boardId',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `sign` varchar(255) DEFAULT NULL COMMENT '签名',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `thum_image_1` varchar(255) DEFAULT NULL COMMENT '缩略图1',
  `thum_image_2` varchar(255) DEFAULT NULL COMMENT '缩略图2',
  `thum_image_3` varchar(255) DEFAULT NULL COMMENT '缩略图3',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_collect_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_collect_subject`;
CREATE TABLE `t_acg12_collect_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `relevance_id` int(11) DEFAULT NULL COMMENT '关联id',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `name_cn` varchar(255) DEFAULT NULL COMMENT '中文名称',
  `type` int(11) DEFAULT NULL COMMENT '0:subject 1:crt 2:preson',
  `type_name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_person
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_person`;
CREATE TABLE `t_acg12_person` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'id',
  `p_id` int(11) DEFAULT NULL COMMENT 'pId',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `name_cn` varchar(255) DEFAULT NULL COMMENT '中文名称',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `summary` varchar(6000) DEFAULT NULL COMMENT '简介',
  `height` varchar(255) DEFAULT NULL COMMENT '身高',
  `weight` varchar(255) DEFAULT NULL COMMENT '体重',
  `alias` varchar(500) DEFAULT NULL COMMENT '别名',
  `type` varchar(255) DEFAULT NULL COMMENT ' 1、声优 2、漫画家 3、制作人 4、音乐人 5、 6、演员 7、绘师 8、作家',
  `gender` varchar(255) DEFAULT NULL COMMENT '// 1、男 2、女',
  `bloodtype` int(255) DEFAULT NULL COMMENT '血型  1、A   2、B   3、AB   4、O',
  `birthday` varchar(255) DEFAULT NULL COMMENT '生日',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5998 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_person_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_person_detail`;
CREATE TABLE `t_acg12_person_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) DEFAULT NULL,
  `other_title` varchar(255) DEFAULT NULL COMMENT '标题',
  `other_value` varchar(255) DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9673 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_schedule_job`;
CREATE TABLE `t_acg12_schedule_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `job_status` varchar(11) DEFAULT NULL COMMENT '0 未开启 1 开启',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
  `spring_id` varchar(255) DEFAULT NULL COMMENT 'spring bean',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_schedule_job_log`;
CREATE TABLE `t_acg12_schedule_job_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `job_id` bigint(20) NOT NULL COMMENT 'jobId',
  `start_num` bigint(20) DEFAULT NULL COMMENT '开始值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_subject`;
CREATE TABLE `t_acg12_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL COMMENT '外部获取的id',
  `type` int(11) DEFAULT NULL COMMENT '1、书籍 2、动画 3、音乐 4、游戏 6、三次元',
  `type_name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `name_cn` varchar(255) DEFAULT NULL COMMENT 'name_cn',
  `summary` varchar(5000) DEFAULT NULL COMMENT '概况',
  `image` varchar(255) DEFAULT NULL COMMENT '封面',
  `eps_count` int(11) DEFAULT NULL COMMENT '话数',
  `air_date` varchar(255) DEFAULT NULL COMMENT '放送开始',
  `air_weekday` int(11) DEFAULT NULL COMMENT '放送星期',
  `end_date` varchar(255) DEFAULT NULL COMMENT '播放结束',
  `lock_status` int(11) DEFAULT NULL COMMENT '锁定状态  1正常 2锁定',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `s_id` (`s_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9133 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_subject_crt
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_subject_crt`;
CREATE TABLE `t_acg12_subject_crt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL COMMENT '外部获取的id',
  `c_id` int(11) DEFAULT NULL COMMENT '外部获取的id - 虚拟人物',
  `p_id` int(11) DEFAULT NULL COMMENT '外部获取的id - 真实人物',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `name_n` varchar(255) DEFAULT NULL COMMENT '中文名',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `p_name` varchar(255) DEFAULT NULL COMMENT 'cv-名称',
  `p_name_cn` varchar(255) DEFAULT NULL,
  `p_image` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25643 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_subject_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_subject_detail`;
CREATE TABLE `t_acg12_subject_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) NOT NULL,
  `s_id` int(11) DEFAULT NULL COMMENT '外部获取的id',
  `other_title` varchar(255) DEFAULT NULL COMMENT '标题',
  `other_value` varchar(255) DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35042 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_subject_offprint
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_subject_offprint`;
CREATE TABLE `t_acg12_subject_offprint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) DEFAULT NULL,
  `parent_s_id` int(11) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL COMMENT '当前id',
  `image` varchar(255) DEFAULT NULL COMMENT '封面',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5568 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_subject_song
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_subject_song`;
CREATE TABLE `t_acg12_subject_song` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22656 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_subject_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_subject_staff`;
CREATE TABLE `t_acg12_subject_staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  `p_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `job` varchar(255) DEFAULT NULL COMMENT '职业',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84712 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_acg12_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_tag`;
CREATE TABLE `t_acg12_tag` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `is_lock` int(11) DEFAULT NULL COMMENT '锁定',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='标签';

-- ----------------------------
-- Table structure for t_acg12_user
-- ----------------------------
DROP TABLE IF EXISTS `t_acg12_user`;
CREATE TABLE `t_acg12_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `nick` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `sign` varchar(255) DEFAULT NULL COMMENT '签名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for t_system_acl
-- ----------------------------
DROP TABLE IF EXISTS `t_system_acl`;
CREATE TABLE `t_system_acl` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `URL` varchar(255) DEFAULT '' COMMENT 'URL',
  `ACL_NAME` varchar(255) NOT NULL COMMENT '资源名称',
  `ACL_TYPE` int(11) NOT NULL COMMENT '资源类型',
  `PERMISSION` varchar(255) NOT NULL COMMENT '权限标识',
  `ICON` varchar(255) DEFAULT NULL COMMENT '图标',
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '上级编号',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `CREATOR` bigint(20) DEFAULT NULL COMMENT '创建者',
  `UPDATOR` bigint(20) DEFAULT NULL COMMENT '修改者',
  `SORTS` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_system_gen_scheme
-- ----------------------------
DROP TABLE IF EXISTS `t_system_gen_scheme`;
CREATE TABLE `t_system_gen_scheme` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATOR` bigint(20) DEFAULT NULL COMMENT '创建者',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATOR` bigint(20) DEFAULT NULL COMMENT '修改者',
  `STRATEGY` varchar(255) DEFAULT NULL COMMENT '生成策略',
  `SCHEME_NAME` varchar(255) DEFAULT NULL COMMENT '方案名称',
  `SCHEME_TEMPLATE` varchar(255) DEFAULT NULL COMMENT '方案模版',
  `PACKAGE_NAME` varchar(255) DEFAULT NULL COMMENT '包名',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述',
  `AUTHOR` varchar(255) DEFAULT NULL COMMENT '作者',
  `GEN_TABLE_ID` bigint(20) DEFAULT NULL COMMENT '关联表',
  `MODULE_NAME` varchar(20) DEFAULT NULL COMMENT '模块名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_system_gen_table
-- ----------------------------
DROP TABLE IF EXISTS `t_system_gen_table`;
CREATE TABLE `t_system_gen_table` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATOR` bigint(20) DEFAULT NULL COMMENT '创建者',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATOR` bigint(20) DEFAULT NULL COMMENT '修改者',
  `TABLE_NAME` varchar(100) NOT NULL COMMENT '表名',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述',
  `CLASS_NAME` varchar(100) NOT NULL COMMENT '对应Java类名',
  `PARENT_TABLE` varchar(100) DEFAULT NULL COMMENT '父级表',
  `PARENT_TABLE_FK` varchar(100) DEFAULT NULL COMMENT '外键关联字段',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_system_gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `t_system_gen_table_column`;
CREATE TABLE `t_system_gen_table_column` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATOR` bigint(20) DEFAULT NULL COMMENT '创建者',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATOR` bigint(20) DEFAULT NULL COMMENT '修改者',
  `GEN_TABLE_ID` bigint(20) DEFAULT NULL COMMENT '表名称',
  `NAME` varchar(100) DEFAULT NULL COMMENT '列名',
  `COMMENTS` varchar(255) DEFAULT NULL COMMENT '列说明',
  `JDBC_TYPE` varchar(255) DEFAULT NULL COMMENT 'JDBC类型',
  `MYBATIS_JDBC_TYPE` varchar(255) DEFAULT NULL COMMENT 'Mybatis对应类型',
  `JAVA_TYPE` varchar(255) DEFAULT NULL COMMENT 'Java类型',
  `JAVA_FIELD` varchar(255) DEFAULT NULL COMMENT 'Java字段',
  `IS_PK` int(1) DEFAULT '0' COMMENT '是否为主键',
  `IS_NULL` int(1) DEFAULT '0' COMMENT '是否为空',
  `IS_INSERT` int(1) DEFAULT '0' COMMENT '可插入',
  `IS_EDIT` int(1) DEFAULT '0' COMMENT '可修改',
  `IS_LIST` int(1) DEFAULT '0' COMMENT '列表',
  `IS_QUERY` int(1) DEFAULT '0' COMMENT '查询',
  `QUERY_TYPE` varchar(255) DEFAULT NULL COMMENT '查询类型',
  `SHOW_TYPE` varchar(255) DEFAULT NULL COMMENT '显示类型',
  `SORTS` int(11) DEFAULT '0' COMMENT '排序',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_system_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `ROLE_NAME` varchar(255) NOT NULL COMMENT '角色名称',
  `IS_SYSTEM` bit(1) DEFAULT b'0' COMMENT '是否内置',
  `DESCRIPTIONS` varchar(255) DEFAULT NULL COMMENT '描述',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `CREATOR` bigint(20) DEFAULT NULL COMMENT '创建者',
  `UPDATOR` bigint(20) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Table structure for t_system_role_acls
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_acls`;
CREATE TABLE `t_system_role_acls` (
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色编号',
  `ACL_ID` bigint(20) NOT NULL COMMENT '资源编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_system_user
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `USER_NAME` varchar(20) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(100) NOT NULL COMMENT '密码',
  `NICKNAME` varchar(20) DEFAULT NULL COMMENT '姓名',
  `IS_ENABLED` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `IS_LOCKED` bit(1) DEFAULT b'0' COMMENT '是否锁定',
  `LOGIN_FAILURE_COUNT` int(11) DEFAULT NULL COMMENT '登录失败次数',
  `LOCKED_DATE` datetime DEFAULT NULL COMMENT '锁定时间',
  `LOGIN_DATE` datetime DEFAULT NULL COMMENT '登录时间',
  `LOGIN_IP` varchar(20) DEFAULT NULL COMMENT '登录IP',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `ROLE_ID` bigint(20) DEFAULT NULL COMMENT '角色',
  `AVATAR` varchar(200) DEFAULT NULL COMMENT '头像',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `THEME` int(11) DEFAULT '1' COMMENT '主题默认1，2为Classic',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `idcard` varchar(100) DEFAULT NULL COMMENT '身份证',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `loginadress` varchar(100) DEFAULT NULL COMMENT '登录地址',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_NAME` (`USER_NAME`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统管理员表';

-- ----------------------------
-- Table structure for update_app
-- ----------------------------
DROP TABLE IF EXISTS `update_app`;
CREATE TABLE `update_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `versionCode` varchar(255) DEFAULT NULL,
  `versionName` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `appurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
