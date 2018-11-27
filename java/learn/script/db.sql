/* 启动测试项目需要导入如下数据库和表信息 */
/* 1. 数据库 test_a */
CREATE database test_a;

CREATE TABLE IF NOT EXISTS `launch_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `category_id` bigint(20) NOT NULL COMMENT '商业计费类目ID',
  `ps_source_id` bigint(20) NOT NULL COMMENT '投放类目检索ID',
  `is_shadow` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为暗投检索ID',
  `is_stat_provider` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为情景页统计检索ID',
  `launch_category_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '投放类目名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_UNI_CAT_SOURCE_ID` (`category_id`,`ps_source_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='投放类目配置信息' AUTO_INCREMENT=1;

INSERT INTO `launch_info` (`id`, `category_id`, `ps_source_id`, `is_shadow`, `is_stat_provider`, `launch_category_name`) VALUES
(1, 356, 100002, 0, 0, 'Wise国外酒店-详情'),
(2, 369, 27051, 0, 0, 'PC-B2B商品'),
(3, 386, 27049, 0, 0, 'PC新房泛需求'),
(4, 387, 27047, 0, 0, 'PC新房精确需求'),
(5, 388, 27531, 0, 0, 'wise新房泛需求'),
(6, 390, 27045, 0, 0, 'pc二手房泛需求'),
(7, 391, 27046, 0, 0, 'PC二手房精确需求'),
(8, 373, 27033, 0, 0, 'PC国外酒店名称词'),
(9, 372, 27032, 0, 0, 'PC国外酒店品牌词'),
(10, 370, 27030, 0, 0, 'PC国外酒店泛需求'),
(11, 1087, 100016, 0, 0, '服饰箱包');

/* 2. 数据库 test_b */
CREATE database test_b;

CREATE TABLE IF NOT EXISTS `budget` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `budget_id` bigint(20) NOT NULL COMMENT '预算控制的层级id',
  `daily_budget` double(20,2) NOT NULL DEFAULT '0.00' COMMENT '日预算',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '预算类型,0计划/1物料',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '更新时间',
  `creator` int(10) NOT NULL DEFAULT '0' COMMENT '创建者',
  `updater` int(10) NOT NULL DEFAULT '0' COMMENT '更新者',
  `version` int(10) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `IDX_BUDGET_ID_TYPE` (`budget_id`,`type`),
  KEY `IDX_BUDGET_USER_ID` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='预算记录' AUTO_INCREMENT=1;

INSERT INTO `budget` (`id`, `budget_id`, `daily_budget`, `type`, `user_id`, `create_time`, `update_time`, `creator`, `updater`, `version`) VALUES
(1, 1734405, 3000.00, 0, 10267913, '2017-06-22 13:48:55', '2017-06-22 13:48:55', 0, 0, 0),
(2, 1738159, 3000.00, 0, 10276015, '2017-06-22 13:48:55', '2017-06-22 13:48:55', 0, 0, 0),
(3, 1590314, 3000.00, 0, 6439548, '2017-06-22 13:48:55', '2017-06-22 13:48:55', 0, 0, 0),
(4, 1738162, 3000.00, 0, 24011771, '2017-06-22 13:48:55', '2017-06-22 13:48:55', 0, 0, 0),
(5, 1590782, 3000.00, 0, 622687, '2017-06-23 17:00:13', '2017-06-23 17:00:13', 0, 0, 0),
(6, 1739905, 4000.00, 0, 7344146, '2017-06-29 10:32:25', '2018-06-29 16:29:39', 0, 7344146, 3),
(7, 1600883, 355484.00, 0, 24228264, '2017-07-24 14:42:46', '2017-07-24 14:42:46', 0, 0, 0),
(8, 1603361, 10000.00, 0, 24286001, '2017-07-31 11:00:21', '2017-07-31 11:00:21', 0, 0, 1),
(9, 1747851, 10000.00, 0, 1984018, '2017-07-31 11:00:21', '2017-08-15 17:00:52', 0, 1984018, 2),
(10, 1603370, 10000.00, 0, 24286001, '2017-07-31 11:10:16', '2017-07-31 11:10:16', 0, 0, 1);