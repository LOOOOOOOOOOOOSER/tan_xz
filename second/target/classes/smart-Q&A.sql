SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(11) DEFAULT NULL COMMENT '反馈用户',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `title` varchar(255) DEFAULT NULL COMMENT '反馈标题',
  `content` text COMMENT '反馈内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of feedback
-- ----------------------------
BEGIN;
INSERT INTO `feedback` (`id`, `name`, `email`, `title`, `content`, `create_time`, `update_time`) VALUES (6, '路人甲', '31952874@qq.com', '测试一号', '测试这个系统有问题吗？', '2022-05-03 16:13:59', '2022-05-03 16:13:59');
INSERT INTO `feedback` (`id`, `name`, `email`, `title`, `content`, `create_time`, `update_time`) VALUES (7, '路人乙', '31952874@qq.com', '测试二号', '惆怅长岑长错错错错错错', '2022-05-03 16:14:20', '2022-05-03 16:14:20');
COMMIT;



-- ----------------------------
-- Table structure for pageview
-- ----------------------------
DROP TABLE IF EXISTS `pageview`;
CREATE TABLE `pageview` (
  `id` int(1) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pageviews` int(1) DEFAULT NULL COMMENT '浏览量',
  `illness_id` int(11) DEFAULT NULL COMMENT '病的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pageview
-- ----------------------------
BEGIN;
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`) VALUES (5, 5, 1);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`) VALUES (6, 4, 13);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`) VALUES (7, 2, 4);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`) VALUES (8, 1, 2);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`) VALUES (9, 1, 3);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`) VALUES (10, 1, 5);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`) VALUES (11, 1, 6);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`) VALUES (12, 2, 7);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`) VALUES (13, 1, 8);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`) VALUES (14, 1, 9);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `user_account` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户的真实名字',
  `user_pwd` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `user_age` int(11) DEFAULT NULL COMMENT '用户年龄',
  `user_sex` varchar(1) DEFAULT NULL COMMENT '用户性别',
  `user_email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `user_tel` varchar(50) DEFAULT NULL COMMENT '手机号',
  `role_status` int(11) DEFAULT NULL COMMENT '角色状态，1管理员，0普通用户',
  `img_path` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `user_account`, `user_name`, `user_pwd`, `user_age`, `user_sex`, `user_email`, `user_tel`, `role_status`, `img_path`, `create_time`, `update_time`) VALUES (4, 'admin', '管理员', '123456', 23, '男', '2678788262@qq.com', '17746678954', 1, 'https://moti-cloud-v2.oss-cn-beijing.aliyuncs.com/Snipaste_2022-05-01_15-37-01.png', '2022-05-03 15:55:41', '2022-05-03 15:56:15');
INSERT INTO `user` (`id`, `user_account`, `user_name`, `user_pwd`, `user_age`, `user_sex`, `user_email`, `user_tel`, `role_status`, `img_path`, `create_time`, `update_time`) VALUES (5, 'zhangsan', '张三', '123456', 23, '女', 'isxuewei@qq.com', '17879544343', 0, 'https://su-share.oss-cn-beijing.aliyuncs.com/5/5dc107dcd2db4cbd8ad561f4c1642886.png', '2022-05-03 16:15:53', '2022-05-03 16:17:12');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE IF EXISTS `t_hot_issues`;
CREATE TABLE `t_hot_issues` (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题ID',
                            `user_id` int(11) NOT NULL COMMENT '用户ID',
                                `content` text COMMENT '问题内容',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`),
                                KEY `user_id` (`user_id`),
                                CONSTRAINT `t_hot_issues_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
                            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
                              `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
                              `user_id` int(11) NOT NULL COMMENT '用户ID',
                              `issue_id` int(11) NOT NULL COMMENT '问题ID',
                              `content` text COMMENT '评论内容',
                              `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`),
                              KEY `user_id` (`user_id`),
                              KEY `issue_id` (`issue_id`),
                              CONSTRAINT `t_comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `t_comments_ibfk_2` FOREIGN KEY (`issue_id`) REFERENCES `t_hot_issues` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_favorites`;
CREATE TABLE `t_favorites` (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
                               `user_id` int(11) NOT NULL COMMENT '用户ID',
                               `issue_id` int(11) NOT NULL COMMENT '问题ID',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
                               `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               KEY `user_id` (`user_id`),
                               KEY `issue_id` (`issue_id`),
                               CONSTRAINT `t_favorites_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT `t_favorites_ibfk_2` FOREIGN KEY (`issue_id`) REFERENCES `t_hot_issues` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



INSERT INTO t_hot_issues (user_id, content) VALUES
    (1, '线性代数课程的考试大纲在哪里可以找到？'),
                                                    (2, '宿舍2号楼的空调不制冷，什么时候能修好？'),
                                                    (3, '如何进行课程重修的报名？'),
                                                    (5, '学校里哪里有免费的体育活动？'),
                                                    (6, '图书馆的图书借阅期限是多长？'),
                                                    (1, '为什么我的宿舍门窗锁有问题？什么时候能修好？'),
                                                    (2, '学校社团招募的时间安排是什么？'),
                                                    (3, '数据结构课程的上机实验在哪里进行？'),
                                                    (5, '学校食堂的饭菜质量如何改进？'),
                                                    (6, '运动场馆的开放时间是什么时候？'),
                                                    (1, '考试周的时间安排是什么？'),
                                                    (2, '学校里的超市是否可以刷卡支付？'),
                                                    (3, '计算机学院的双选会什么时候举行？'),
                                                    (5, '监考任务在哪里可以看到？'),
                                                    (6, '考研选哪个专业就业前景更好？'),
                                                    (1, '请帮我查一下今天图书馆还有没有座位？'),
                                                    (2, '请问这个东西能买吗？'),
                                                    (3, '发动机原理课程是否在网络上更新？'),
                                                    (5, '如何在线购买火车票？'),
                                                    (6, '请问学习强国如何注册？'),
                                                    (1, '如何成为防洪英雄？'),
                                                    (2, '发动机原理课程是否在网络上更新？'),
                                                    (3, '如何获得更高的权限？'),
                                                    (5, '如何修复iPhone卡慢问题？'),
                                                    (6, '如何制作番茄炒蛋？'),
                                                    (1, '如何在电脑上恢复误删除的文件？'),
                                                    (2, '如何设置复杂密码以提高安全性？'),
                                                    (3, '如何用微波炉热饭菜？'),
                                                    (5, '如何制作一杯美味的拿铁咖啡？'),
                                                    (6, '如何办理校园网续费？'),
                                                    (1, '学校篮球比赛的结果什么时候公布？'),
                                                    (2, '如何提高编程能力？'),
                                                    (3, '图书馆自习室如何预约？'),
                                                    (5, '校园里哪里有维修笔记本电脑的地方？'),
                                                    (6, '如何在学校官网上查找学术讲座信息？'),
                                                    (1, '学校的勤工助学岗位如何申请？'),
                                                    (2, '如何在学校图书馆查找特定的学术论文？'),
                                                    (3, '校园内的自行车停车场在哪里？'),
                                                    (5, '如何在学校官网上查找学术讲座信息？'),
                                                    (6, '学校的勤工助学岗位如何申请？'),
                                                    (1, '如何在学校图书馆查找特定的学术论文？'),
                                                    (2, '校园内的自行车停车场在哪里？'),
                                                    (3, '学校的校园卡如何充值？'),
                                                    (5, '如何在学校官网上查找教职工通讯录？'),
                                                    (6, '校园内的快递驿站几点关门？'),
                                                    (1, '如何快速获取校园内最新的活动信息？'),
                                                    (2, '学校的毕业典礼通常在什么时候举行？'),
                                                    (3, '如何申请学校的创新创业项目资助？'),
                                                    (5, '学校的雅思托福考试报名如何进行？'),
                                                    (6, '如何在校内组织社团活动并申请场地？');

INSERT INTO t_comments (user_id, issue_id, content) VALUES
                                                        (1, 61, '这个问题的答案可以在教务处的网站上找到。'),
                                                        (2, 61, '我也有同样的疑问，希望得到详细解答。'),
                                                        (3, 61, '我可以确认这门课程是在 A101 教室上。'),
                                                        (5, 62, '网络维修师傅已经安排了，预计周末就能修好。'),
                                                        (6, 62, '奖学金申请条件可以在学工处的公告栏查看。'),
                                                        (1, 63, '学校的讲座信息通常会提前一周在学校官网发布。'),
                                                        (2, 114, '图书馆的书架调整是为了更好地分类图书。'),
                                                        (3, 115, '考试大纲一般会在课程开始时由教师提供。'),
                                                        (5, 116, '宿舍的空调维修已经提交申请，估计下周会有人来修。'),
                                                        (6, 117, '课程重修报名需要在教务处办理手续。'),
                                                        (1, 118, '学校每天上午和下午都会举办体育活动，详情请咨询体育部。'),
                                                        (2, 119, '图书馆的图书借阅期限一般是 30 天，可续借一次。'),
                                                        (3, 120, '宿舍门窗锁的问题需要向宿管阿姨反映。'),
                                                        (5, 121, '社团招募时间表会在校园公告栏和社团联合会微信公众号发布。'),
                                                        (6, 122, '数据结构课程的上机实验在计算机学院的实验楼进行。'),
                                                        (1, 123, '食堂饭菜质量的反馈可以向后勤处提出。'),
                                                        (2, 124, '运动场馆的开放时间是每天 8:00-22:00，法定节假日除外。'),
                                                        (3, 125, '考试周的时间安排会在教务处的通知中明确说明。'),
                                                        (5, 126, '学校超市支持校园卡、银行卡和支付宝等多种支付方式。'),
                                                        (6, 127, '计算机学院的双选会预计在下个月举行，详情请关注学院官网。'),
                                                        (1, 128, '监考任务一般会在考试前一周由教务处通知安排。'),
                                                        (2, 129, '考研专业的选择需要考虑个人兴趣、职业规划和就业前景。'),
                                                        (3, 130, '今天图书馆的座位紧张，你可以先去学习中心或者自习室。'),
                                                        (5, 131, '这个问题的答案需要结合具体情况来分析。'),
                                                        (6, 132, '发动机原理课程的教学视频可以在学校的网络课堂上找到。'),
                                                        (1, 133, '购买火车票可以通过 12306 官网或者火车站售票窗口。'),
                                                        (2, 134, '学习强国的注册流程非常简单，只需要扫描二维码下载 APP。'),
                                                        (3, 135, '成为防洪英雄需要具备高度的责任感和专业技能。'),
                                                        (5, 136, '发动机原理课程的教学视频会在每周五更新。'),
                                                        (6, 137, '获得更高权限需要向管理员提出申请并说明理由。'),
                                                        (1, 138, '修复 iPhone 卡慢问题可以尝试清理缓存、关闭不必要的后台应用。'),
                                                        (2, 139, '番茄炒蛋的制作方法很简单，先将鸡蛋打散，然后热油下锅。'),
                                                        (3, 140, '在电脑上恢复误删除的文件可以使用数据恢复软件。'),
                                                        (5, 141, '设置复杂密码可以提高安全性，建议定期更换密码。'),
                                                        (6, 142, '用微波炉热饭菜要注意控制时间和火力，避免饭菜变干。'),
                                                        (1, 143, '制作一杯美味的拿铁咖啡需要优质的咖啡豆和牛奶。'),
                                                        (2, 144, '校园网续费可以在学校的网上缴费平台进行。'),
                                                        (3, 145, '学校的篮球比赛结果通常会在比赛结束后一小时内公布。'),
                                                        (5, 146, '提高编程能力需要多练习、多思考，可以从简单的项目开始。'),
                                                        (6, 147, '图书馆自习室可以通过图书馆的预约系统进行预约。'),
                                                        (1, 148, '校园内有多个维修笔记本电脑的地方，可以去学校的维修中心或者附近的电脑维修店。'),
                                                        (2, 149, '在学校官网上查找学术讲座信息可以访问学术动态板块。'),
                                                        (3, 150, '学校的勤工助学岗位信息会在学生资助中心的网站上发布。'),
                                                        (5, 151, '在学校图书馆查找学术论文可以通过图书馆的电子资源搜索系统。'),
                                                        (6, 152, '校园内的自行车停车场分布在学校的各个角落，可以在学校地图上查看。'),
                                                        (1, 153, '在学校官网上查找学术讲座信息可以访问学术动态板块。'),
                                                        (2, 154, '学校的勤工助学岗位信息会在学生资助中心的网站上发布。'),
                                                        (3, 155, '在学校图书馆查找学术论文可以通过图书馆的电子资源搜索系统。'),
                                                        (5, 156, '校园内的自行车停车场分布在学校的各个角落，可以在学校地图上查看。'),
                                                        (6, 157, '学校的校园卡可以在学校的财务处或者自助充值机上充值。'),
                                                        (1, 158, '在学校官网上查找教职工通讯录可以访问学校的人事处页面。'),
                                                        (2, 159, '校园内的快递驿站关门时间一般是晚上 9 点，但可能会因季节和节假日有所调整。'),
                                                        (3, 160, '如何快速获取校园内最新的活动信息？'),
                                                        (5, 161, '学校的毕业典礼通常在什么时候举行？'),
                                                        (6, 162, '如何申请学校的创新创业项目资助？'),
                                                        (1, 163, '学校的雅思托福考试报名如何进行？');

INSERT INTO t_favorites (user_id, issue_id) VALUES
                                                (1, 61),
                                                (2, 62),
                                                (3, 63),
                                                (5, 114),
                                                (6, 115),
                                                (1, 116),
                                                (2, 117),
                                                (3, 118),
                                                (5, 119),
                                                (6, 120),
                                                (1, 121),
                                                (2, 122),
                                                (3, 123),
                                                (5, 124),
                                                (6, 125),
                                                (1, 126),
                                                (2, 127),
                                                (3, 128),
                                                (5, 129),
                                                (6, 130),
                                                (1, 131),
                                                (2, 132),
                                                (3, 133),
                                                (5, 134),
                                                (6, 135),
                                                (1, 136),
                                                (2, 137),
                                                (3, 138),
                                                (5, 139),
                                                (6, 140),
                                                (1, 141),
                                                (2, 142),
                                                (3, 143),
                                                (5, 144),
                                                (6, 145),
                                                (1, 146),
                                                (2, 147),
                                                (3, 148),
                                                (5, 149),
                                                (6, 150),
                                                (1, 151),
                                                (2, 152),
                                                (3, 153),
                                                (5, 154),
                                                (6, 155),
                                                (1, 156),
                                                (2, 157),
                                                (3, 158),
                                                (5, 159),
                                                (6, 160),
                                                (1, 161),
                                                (2, 162),
                                                (3, 163);

CREATE TABLE IF NOT EXISTS `hot_news` (
                                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '热门新闻主键id',
                                          `user_id` int(11) NOT NULL COMMENT '用户id，关联user表',
                                          `author` varchar(255) NOT NULL COMMENT '作者',
                                          `source` varchar(255) NOT NULL COMMENT '资料来源',
                                          `view_count` int(11) NOT NULL DEFAULT 0 COMMENT '浏览次数',
                                          `img_path` varchar(255) DEFAULT NULL COMMENT '新闻图片路径',
                                          `title` varchar(255) NOT NULL COMMENT '新闻标题',
                                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `context` text NOT NULL COMMENT '新闻内容',
                                          PRIMARY KEY (`id`),
                                          KEY `user_id` (`user_id`),
                                          CONSTRAINT `hot_news_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE hot_news ADD COLUMN update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;