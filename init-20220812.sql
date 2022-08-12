-- ----------------------------
-- Records of cms_config
-- ----------------------------
INSERT INTO `cms_config` VALUES (1, '萤火科技', '前后端分离RBAC管理基础框架', '李', '18932813211', '854378082@qq.com', '地址：河北省衡水市中华南大街河阳路467号门店', '2021-03-15 09:23:29', '2022-08-03 09:55:20');

-- ----------------------------
-- Records of ums_admins
-- ----------------------------
INSERT INTO `ums_admins` VALUES (1, 'admin', 'admins', 'fsda@eee.com', '13123212345', 'edo9xnWP6P', '2ba26098ffb490e6294cfb38ded93c7d', '127.0.0.1', '2022-08-12 09:48:43', 1, 0, '2016-08-07 02:05:54', '2022-08-12 09:48:43');

-- Records of ums_department_admins
-- ----------------------------
INSERT INTO `ums_department_admins` VALUES (1, 1);


-- ----------------------------
-- Records of ums_departments
-- ----------------------------
INSERT INTO `ums_departments` VALUES (1, 0, '121', 1, 0, NULL, '2022-07-26 17:09:18');

-- ----------------------------
-- Records of ums_menus
-- ----------------------------
INSERT INTO `ums_menus` VALUES (5, 0, '系统', 'sys/index', 'sys-index', NULL, 1, 98, '2016-03-17 15:47:40', '2021-03-15 18:03:38');
INSERT INTO `ums_menus` VALUES (6, 10, '权限菜单管理', 'menu/tree', 'menu-tree', NULL, 1, 1, '2016-03-17 15:48:07', '2019-10-24 09:36:36');
INSERT INTO `ums_menus` VALUES (7, 6, '添加菜单', 'menu/create', 'menu-create', NULL, 0, 0, '2016-03-17 15:49:03', '2019-10-24 09:36:36');
INSERT INTO `ums_menus` VALUES (8, 6, '修改菜单', 'menu/update', 'menu-update', NULL, 0, 0, '2016-03-17 15:51:08', '2022-08-11 11:04:15');
INSERT INTO `ums_menus` VALUES (9, 6, '删除菜单', 'menu/remove', 'menu-remove', NULL, 0, 0, '2016-03-17 15:51:30', '2019-10-24 09:36:36');
INSERT INTO `ums_menus` VALUES (10, 5, '管理员管理', 'admin/manage', 'admin-manage', 'ios-people-outline', 1, 97, '2016-03-17 16:04:01', '2019-10-24 09:36:36');
INSERT INTO `ums_menus` VALUES (11, 10, '用户管理', 'admin/list', 'admin-list', NULL, 1, 0, '2016-03-17 16:04:38', '2019-10-24 09:36:36');
INSERT INTO `ums_menus` VALUES (12, 11, '添加用户', 'admin/create', 'admin-create', NULL, 0, 0, '2016-03-17 16:05:14', '2019-10-24 09:36:36');
INSERT INTO `ums_menus` VALUES (13, 11, '修改用户', 'admin/update', 'admin-update', NULL, 0, 0, '2016-03-17 16:06:10', '2022-08-11 11:04:27');
INSERT INTO `ums_menus` VALUES (14, 11, '删除用户', 'admin/remove', 'admin-remove', NULL, 0, 0, '2016-03-17 16:06:31', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (15, 11, '修改密码', 'admin/pwd', 'admin-pwd', NULL, 0, 0, '2016-03-17 16:07:07', '2022-08-11 11:04:44');
INSERT INTO `ums_menus` VALUES (16, 10, '角色管理', 'role/list', 'role-list', NULL, 1, 0, '2016-03-17 16:07:58', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (17, 16, '添加角色', 'role/create', 'role-create', NULL, 0, 0, '2016-03-17 16:08:23', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (18, 16, '修改角色', 'role/update', 'role-update', NULL, 0, 0, '2016-03-17 16:08:50', '2022-08-11 11:04:55');
INSERT INTO `ums_menus` VALUES (19, 16, '删除角色', 'role/remove', 'role-remove', NULL, 0, 0, '2016-03-17 16:09:10', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (20, 5, '所有人都要有的权限', 'index/main', 'index-main', NULL, 0, 99, '2016-03-23 15:42:14', '2019-10-24 09:36:59');
INSERT INTO `ums_menus` VALUES (21, 20, '左侧菜单', 'menu/list', 'menu-list', NULL, 1, 0, '2016-03-24 10:34:44', '2019-10-24 09:36:59');
INSERT INTO `ums_menus` VALUES (22, 75, '内容管理', 'content/manage', 'content-manage', 'ios-bookmarks-outline', 1, 0, '2016-03-28 08:39:52', '2021-03-16 02:24:40');
INSERT INTO `ums_menus` VALUES (23, 22, '栏目管理', 'category/list', 'category-list', NULL, 1, 0, '2016-03-28 08:40:08', '2022-08-10 16:28:20');
INSERT INTO `ums_menus` VALUES (24, 23, '添加栏目', 'category/create', 'category-create', NULL, 1, 0, '2016-03-28 08:40:25', '2022-08-10 16:28:24');
INSERT INTO `ums_menus` VALUES (25, 23, '修改栏目', 'category/update', 'category-update', NULL, 0, 0, '2016-03-28 08:40:42', '2022-08-11 10:35:29');
INSERT INTO `ums_menus` VALUES (26, 23, '删除栏目', 'category/remove', 'category-remove', NULL, 0, 0, '2016-03-28 08:40:54', '2022-08-10 16:28:34');
INSERT INTO `ums_menus` VALUES (28, 22, '友情链接管理', 'link/list', 'link-list', '', 1, 5, '2016-03-30 08:23:28', '2021-03-16 02:24:17');
INSERT INTO `ums_menus` VALUES (30, 22, '文章管理', 'article/list', 'article-list', NULL, 1, 0, '2016-03-30 08:25:22', '2020-02-28 17:19:18');
INSERT INTO `ums_menus` VALUES (31, 30, '添加文章', 'article/create', 'article-create', NULL, 1, 0, '2016-03-30 08:25:40', '2020-02-28 17:19:18');
INSERT INTO `ums_menus` VALUES (32, 30, '修改文章', 'article/update', 'article-update', NULL, 0, 0, '2016-03-30 08:25:59', '2022-08-11 11:05:02');
INSERT INTO `ums_menus` VALUES (33, 30, '删除文章', 'article/remove', 'article-remove', NULL, 0, 0, '2016-03-30 08:26:15', '2020-02-28 17:19:18');
INSERT INTO `ums_menus` VALUES (34, 30, '查看文章', 'article/detail', 'article-detail', NULL, 0, 0, '2016-03-30 08:26:35', '2020-02-28 17:19:18');
INSERT INTO `ums_menus` VALUES (67, 10, '操作日志', 'log/list', 'log-list', NULL, 1, 4, '2016-04-10 10:38:34', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (68, 67, '清除7天前日志', 'log/clear', 'log-clear', NULL, 0, 0, '2016-04-10 10:38:53', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (75, 0, '内容', 'content/index', 'content-index', '', 1, 1, '2016-05-08 19:29:09', '2021-05-13 10:09:38');
INSERT INTO `ums_menus` VALUES (121, 30, '批量删除', 'article/deleteall', 'article-deleteall', NULL, 0, 0, '2016-06-14 08:52:32', '2020-02-28 17:19:18');
INSERT INTO `ums_menus` VALUES (136, 30, '文章排序', 'article/sort', 'article-sort', NULL, 0, 0, '2016-07-24 08:35:42', '2020-02-28 17:19:18');
INSERT INTO `ums_menus` VALUES (140, 16, '角色权限', 'role/priv', 'role-priv', NULL, 0, 0, '2016-07-24 11:34:39', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (143, 5, '资料', 'admin/info', 'admin-info', 'ios-outlet-outline', 0, 99, '2016-07-27 14:01:45', '2022-08-12 09:53:16');
INSERT INTO `ums_menus` VALUES (144, 143, '修改个人资料', 'admin/selfinfo', 'admin-selfinfo', NULL, 1, 0, '2016-07-27 14:02:12', '2022-08-11 11:05:17');
INSERT INTO `ums_menus` VALUES (145, 143, '修改个人密码', 'admin/selfpassword', 'admin-selfpassword', NULL, 1, 0, '2016-07-27 14:02:37', '2022-08-11 11:05:22');
INSERT INTO `ums_menus` VALUES (151, 367, '分类管理', 'type/list', 'type-list', NULL, 1, 2, '2016-12-13 09:56:01', '2021-03-15 18:03:38');
INSERT INTO `ums_menus` VALUES (152, 151, '添加分类', 'type/create', 'type-create', NULL, 0, 0, '2016-12-13 09:56:23', '2019-10-24 09:42:31');
INSERT INTO `ums_menus` VALUES (153, 151, '修改分类', 'type/update', 'type-update', NULL, 0, 0, '2016-12-13 09:56:42', '2022-08-11 11:05:28');
INSERT INTO `ums_menus` VALUES (154, 151, '删除分类', 'type/remove', 'type-remove', NULL, 0, 0, '2016-12-13 09:56:57', '2019-10-24 09:42:31');
INSERT INTO `ums_menus` VALUES (170, 10, '部门管理', 'department/list', 'department-list', NULL, 1, 0, '2016-12-14 08:31:39', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (171, 170, '添加部门', 'department/create', 'department-create', NULL, 0, 0, '2016-12-14 08:32:01', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (172, 170, '修改部门', 'department/update', 'department-update', NULL, 0, 0, '2016-12-14 08:32:23', '2022-08-11 11:05:34');
INSERT INTO `ums_menus` VALUES (173, 170, '删除部门', 'department/remove', 'department-remove', NULL, 0, 0, '2016-12-14 08:32:44', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (205, 367, '系统配置', 'config/index', 'config-index', 'icon-brightnesshigh', 1, 0, '2017-04-24 21:49:13', '2019-10-24 09:42:18');
INSERT INTO `ums_menus` VALUES (242, 22, '广告管理', 'ad/list', 'ad-list', '', 1, 0, '2017-05-15 06:34:07', '2020-02-28 17:28:55');
INSERT INTO `ums_menus` VALUES (243, 242, '添加广告', 'ad/create', 'ad-create', '', 0, 0, '2017-05-15 06:34:25', '2020-02-28 17:31:31');
INSERT INTO `ums_menus` VALUES (244, 242, '修改广告', 'ad/update', 'ad-update', NULL, 0, 0, '2017-05-15 06:34:40', '2022-08-11 11:05:41');
INSERT INTO `ums_menus` VALUES (245, 242, '删除广告', 'ad/remove', 'ad-remove', '', 0, 0, '2017-05-15 06:34:55', '2020-02-28 17:29:58');
INSERT INTO `ums_menus` VALUES (246, 242, '广告排序', 'ad/sort', 'ad-sort', NULL, 0, 0, '2017-05-15 06:35:11', '2020-02-28 17:19:19');
INSERT INTO `ums_menus` VALUES (247, 242, '批量删除广告', 'ad/alldel', 'ad-alldel', NULL, 0, 0, '2017-05-15 06:35:35', '2020-02-28 17:19:19');
INSERT INTO `ums_menus` VALUES (300, 22, '广告位管理', 'adpos/list', 'adpos-list', '', 1, 0, '2017-06-30 09:19:12', '2021-03-16 02:24:41');
INSERT INTO `ums_menus` VALUES (301, 300, '添加广告位', 'adpos/create', 'adpos-create', '', 0, 0, '2017-06-30 09:19:37', '2020-02-28 17:31:26');
INSERT INTO `ums_menus` VALUES (302, 300, '修改广告位', 'adpos/update', 'adpos-update', NULL, 0, 0, '2017-06-30 09:19:59', '2022-08-11 11:05:46');
INSERT INTO `ums_menus` VALUES (303, 300, '删除广告位', 'adpos/remove', 'adpos-remove', '', 0, 0, '2017-06-30 09:20:21', '2020-02-28 17:29:27');
INSERT INTO `ums_menus` VALUES (353, 20, '面包屑', 'breadcrumb/list', 'breadcrumb-list', NULL, 1, 0, '2018-12-07 08:41:35', '2019-10-24 09:36:59');
INSERT INTO `ums_menus` VALUES (354, 6, '权限菜单下拉选框', 'menu/select', 'menu-select', NULL, 0, 0, '2018-12-07 08:44:56', '2019-10-24 09:36:37');
INSERT INTO `ums_menus` VALUES (355, 6, '查询权限菜单', 'menu/detail', 'menu-detail', NULL, 0, 0, '2018-12-07 08:49:46', '2019-10-24 09:36:38');
INSERT INTO `ums_menus` VALUES (356, 170, '修改部门状态', 'department/status', 'department-status', NULL, 0, 0, '2018-12-07 08:52:03', '2019-10-24 09:36:38');
INSERT INTO `ums_menus` VALUES (357, 16, '修改角色状态', 'role/status', 'role-status', NULL, 0, 0, '2018-12-07 08:53:26', '2019-10-24 09:36:38');
INSERT INTO `ums_menus` VALUES (358, 11, '修改用户状态', 'admin/status', 'admin-status', NULL, 0, 0, '2018-12-07 08:57:40', '2019-10-24 09:36:38');
INSERT INTO `ums_menus` VALUES (359, 11, '查看用户信息', 'admin/detail', 'admin-detail', NULL, 0, 0, '2018-12-07 08:57:59', '2019-10-24 09:36:38');
INSERT INTO `ums_menus` VALUES (360, 151, '分类排序', 'type/sort', 'type-sort', NULL, 0, 0, '2018-12-08 11:04:37', '2019-10-24 09:42:31');
INSERT INTO `ums_menus` VALUES (361, 23, '查看栏目', 'category/detail', 'category-detail', NULL, 0, 0, '2018-12-25 17:20:25', '2022-08-10 16:28:46');
INSERT INTO `ums_menus` VALUES (362, 23, '栏目下拉', 'category/select', 'category-select', NULL, 0, 0, '2018-12-25 17:20:43', '2022-08-10 16:28:49');
INSERT INTO `ums_menus` VALUES (363, 23, '栏目排序', 'category/sort', 'category-sort', NULL, 0, 0, '2018-12-25 17:21:05', '2022-08-10 16:28:53');
INSERT INTO `ums_menus` VALUES (367, 5, '系统设置', 'system/index', 'system-index', 'ios-cog-outline', 1, 0, '2019-10-24 09:41:20', '2021-03-15 18:03:38');
INSERT INTO `ums_menus` VALUES (369, 28, '添加友情链接', 'link/create', 'link-create', '', 0, 0, '2020-02-28 17:30:27', '2020-02-28 17:31:21');
INSERT INTO `ums_menus` VALUES (370, 28, '修改友情链接', 'link/update', 'link-update', '', 0, 0, '2020-02-28 17:30:49', '2022-08-11 11:06:11');
INSERT INTO `ums_menus` VALUES (371, 28, '删除友情链接', 'link/remove', 'link-remove', '', 0, 0, '2020-02-28 17:31:09', '2020-02-28 17:31:10');
INSERT INTO `ums_menus` VALUES (372, 151, '分类下拉', 'type/select', 'type-select', NULL, 0, 0, '2020-02-28 17:31:09', '2021-03-15 18:03:38');
INSERT INTO `ums_menus` VALUES (373, 22, '友情链接分类', 'linktype/list', 'linktype-list', NULL, 1, 0, '2021-03-15 18:01:56', '2021-03-16 02:23:23');
INSERT INTO `ums_menus` VALUES (374, 373, '添加友情链接分类', 'linktype/create', 'linktype-create', NULL, 1, 0, '2021-03-15 18:02:29', '2021-03-15 18:02:29');
INSERT INTO `ums_menus` VALUES (375, 373, '修改友情链接分类', 'linktype/update', 'linktype-update', NULL, 1, 0, '2021-03-15 18:02:51', '2022-08-11 11:06:16');
INSERT INTO `ums_menus` VALUES (376, 373, '删除友情链接分类', 'linktype/remove', 'linktype-remove', NULL, 1, 0, '2021-03-15 18:03:13', '2021-03-15 18:03:14');
INSERT INTO `ums_menus` VALUES (377, 373, '查看友情链接分类', 'linktype/detail', 'linktype-detail', NULL, 1, 0, '2021-03-16 02:23:22', '2021-03-16 02:23:23');
INSERT INTO `ums_menus` VALUES (378, 28, '查看友情连接', 'link/detail', 'link-detail', NULL, 1, 0, '2021-03-16 02:23:53', '2021-03-16 02:23:53');
INSERT INTO `ums_menus` VALUES (379, 28, '友情链接排序', 'link/sort', 'link-sort', NULL, 0, 0, '2021-03-16 02:24:16', '2021-03-16 02:24:17');
INSERT INTO `ums_menus` VALUES (380, 300, '查看广告位', 'adpos/detail', 'adpos-detail', NULL, 0, 0, '2021-03-16 02:24:40', '2021-03-16 02:24:41');

-- ----------------------------
-- Records of ums_role_admins
-- ----------------------------
INSERT INTO `ums_role_admins` VALUES (1, 1);

-- ----------------------------
-- Records of ums_roles
-- ----------------------------
INSERT INTO `ums_roles` VALUES (1, '超级管理员', 1, '2016-03-18 08:42:51', '2022-04-30 18:19:27');
