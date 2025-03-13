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

CREATE TABLE QuestionAnswerRecord (
                                      RecordID INT PRIMARY KEY AUTO_INCREMENT, -- 记录ID
                                      TotalQA INT NOT NULL, -- 总问答数
                                      AvgTurnsPerSession INT NOT NULL, -- 平均问答轮数
                                      HotQuestions TEXT, -- 热门问题TOP10，以逗号分隔
                                      QAByCategory TEXT, -- 问答分类统计，以逗号分隔
                                      QAByTime TEXT -- 问答时间分布，以逗号分隔
);

CREATE TABLE TokensData (
                            TokensID INT PRIMARY KEY AUTO_INCREMENT, -- Tokens记录ID
                            TotalTokensConsumed INT NOT NULL, -- Tokens总消耗
                            AvgTokensPerQA INT NOT NULL, -- 平均Tokens消耗/问答
                            TokensConsumptionTrend TEXT, -- Tokens消耗趋势
                            PeakTokensPeriod TEXT -- 高峰Tokens消耗时段
);

CREATE TABLE SystemPerformance (
                                   PerformanceID INT PRIMARY KEY AUTO_INCREMENT, -- 性能记录ID
                                   AvgResponseTime DECIMAL(10, 2) NOT NULL, -- 系统响应时间（秒）
                                   ErrorRate DECIMAL(5, 2) NOT NULL, -- 系统错误率（百分比）
                                   KnowledgeBaseUpdateFrequency TEXT, -- 知识库更新频率
                                   UserFeedbackCount INT NOT NULL -- 用户反馈数量
);


INSERT INTO QuestionAnswerRecord (TotalQA, AvgTurnsPerSession, HotQuestions, QAByCategory, QAByTime)
VALUES (10000, 3, '问题1：考试安排？', '教学问题：4000', '14:00-16:00: 30%');

INSERT INTO TokensData (TotalTokensConsumed, AvgTokensPerQA, TokensConsumptionTrend, PeakTokensPeriod)
VALUES (1000000, 100, '逐日上升', '14:00-16:00');

INSERT INTO SystemPerformance (AvgResponseTime, ErrorRate, KnowledgeBaseUpdateFrequency, UserFeedbackCount)
VALUES (2.00, 5.00, '1次/周', 500);

CREATE TABLE clubs (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       background TEXT,
                       activities TEXT,
                       highlight TEXT,
                       imagePath VARCHAR(255)
);

-- 学术科技类
INSERT INTO clubs (name, background, activities, highlight) VALUES
                                                                ('WIT人工智能与大数据创新协会', '随着人工智能和大数据技术的快速发展，WIT人工智能与大数据创新协会应运而生，为对相关领域感兴趣的学生提供了学习和实践的平台。', '定期举办技术讲座，邀请行业专家分享最新的人工智能和大数据技术动态；组织编程实战活动，让学生在实践中提升编程能力和数据处理能力；开展创新项目研究，鼓励学生组队参与各类科技竞赛，提升团队协作和创新能力。', '注重理论与实践相结合，让学生在掌握基础知识的同时，能够将所学应用于实际项目中，为未来的职业发展打下坚实基础。'),
                                                                ('Soul of fly无人机协会', '为了满足学生对无人机技术的兴趣和探索欲望，Soul of fly无人机协会成立了，旨在推广无人机文化，提升学生的科技创新能力。', '组织无人机飞行培训，由专业教练指导学生掌握飞行技巧；开展无人机组装与调试实践，让学生了解无人机的内部结构和工作原理；举办无人机竞赛，激发学生的创新思维和竞争意识。', '社团成员在各类无人机竞赛中取得了优异成绩，为学校争得了荣誉，同时也推动了无人机技术在校园内的普及和发展。'),
                                                                ('三维设计爱好者社', '三维设计爱好者社成立于2022年，是一个专注于三维设计技术的学术科技类社团。社团旨在培养学生的三维设计兴趣和技能，提升其在相关领域的竞争力。', '定期开展三维设计软件培训课程，教授3D建模、动画制作等实用技能；组织三维设计作品展示和交流活动，激发学生的创作灵感；举办三维设计竞赛，鼓励学生将所学知识应用于实际项目中。', '社团成员在各类三维设计竞赛中屡获佳绩，其作品在学校及周边地区获得了广泛好评，为三维设计技术在校园内的推广做出了积极贡献。'),
                                                                ('创想3D打印社', '创想3D打印社成立于2022年，是一个以3D打印技术为核心的学术科技类社团。社团致力于探索3D打印技术的应用和创新，为学生提供一个实践和交流的平台。', '定期举办3D打印技术讲座，介绍3D打印的原理、材料和应用领域；开展3D打印实践操作培训，让学生亲身体验3D打印的过程；组织3D打印作品展览，展示学生的创意和成果。', '通过一系列的活动，创想3D打印社成功激发了学生对3D打印技术的兴趣，培养了一批具有创新思维和实践能力的技术人才，为学校的科技创新氛围增添了活力。');

-- 文化体育类
INSERT INTO clubs (name, background, activities, highlight) VALUES
                                                                ('WIT音乐工作室', 'WIT音乐工作室是武汉工程大学音乐爱好者的重要聚集地，经过多年的发展，已成为学校文化活动的重要参与者和贡献者。', '定期开展音乐创作和排练活动，涵盖流行音乐、古典音乐等多种风格；举办校园歌手大赛，挖掘和培养校园内的音乐人才；组织各类音乐演出，如草坪音乐节、舞蹈专场晚会等，为学生提供展示音乐才华的舞台。', '强调音乐无界限，鼓励成员大胆创新和尝试不同的音乐风格，营造了自由、包容的音乐创作氛围。'),
                                                                ('九歌汉服社', '九歌汉服社以传承和弘扬汉服文化为己任，致力于让更多学生了解和喜爱汉服，增强文化自信。', '定期举办汉服知识讲座，介绍汉服的历史渊源、制作工艺等；开展汉服试穿和体验活动，让学生亲身感受汉服的魅力；组织汉服出行活动，如参加校内外的汉服展览、文化活动等，展示汉服文化。', '社团成员积极参与各类汉服文化活动，获得了良好的社会反响，为汉服文化的传承和发展做出了积极贡献。'),
                                                                ('逊逸武术社', '逊逸武术社是武汉工程大学的一个传统体育类社团，致力于推广武术文化，提升学生的身体素质和自我防卫能力。', '定期开展武术基本功训练，教授各类武术套路和技巧；组织成员参加各类武术比赛和表演活动，展示武术风采；邀请武术大师进行讲座和指导，提升成员的武术理论水平。', '逊逸武术社在校园内拥有较高的知名度，其精彩的武术表演深受师生喜爱，同时也在各类武术竞赛中取得了优异成绩，为学校争得了荣誉。'),
                                                                ('精英龙狮社', '精英龙狮社成立于2006年，是武汉工程大学的一个具有浓郁传统文化特色的体育类社团。社团旨在传承和弘扬龙狮文化，培养学生的团队协作精神和表演技巧。', '定期进行龙狮训练，包括基本功、套路编排和表演技巧等方面；参加校内外的龙狮比赛和演出活动，展示龙狮文化的独特魅力；开展龙狮文化讲座和交流活动，普及龙狮知识，增强学生对传统文化的认识和理解。', '精英龙狮社在各类龙狮赛事中屡获佳绩，其精彩的表演不仅为学校赢得了荣誉，也在社会上获得了广泛的关注和好评，为龙狮文化的传承和发展做出了积极贡献。');

-- 志愿公益类
INSERT INTO clubs (name, background, activities, highlight) VALUES
                                                                ('乡土公益社', '乡土公益社成立于对乡村发展和传统文化保护的关注之下，旨在通过实际行动为乡村地区的发展贡献力量。', '组织乡村支教活动，为乡村儿童提供知识和关爱；开展乡村调研，了解乡村现状，为乡村发展提供参考建议；举办公益讲座和宣传活动，提升学生对乡村问题的关注度和参与度。', '注重实践与教育相结合，不仅让学生在志愿服务中锻炼自己，也为乡村地区带来了实际的帮助和改变。'),
                                                                ('碧源环境保护协会', '碧源环境保护协会以保护环境、倡导绿色生活为使命，致力于提升学生的环保意识和行动力。', '定期开展环保知识讲座和宣传活动，介绍环境保护的重要性和方法；组织环保实践活动，如校园垃圾清理、环保创意制作等；参与校外环保项目和志愿活动，与其他环保组织合作，共同推动环保事业的发展。', '通过一系列的环保活动，成功提升了校园内的环保氛围，带动更多学生参与到环保行动中来，为学校的可持续发展做出了积极贡献。'),
                                                                ('勤工助学联盟', '勤工助学联盟是武汉工程大学的一个志愿公益类社团，旨在为家庭经济困难的学生提供勤工俭学的机会，帮助他们减轻经济压力，同时锻炼自身能力。', '收集和发布校内外的勤工俭学信息，为学生提供合适的兼职岗位；组织勤工俭学培训，提升学生的职场技能和综合素质；开展勤工俭学经验交流活动，让学生分享和学习彼此的心得体会。', '勤工助学联盟为众多经济困难的学生提供了实际帮助，使他们在获得经济报酬的同时，也提升了自己的实践能力和社交能力，受到了学校和学生的广泛好评。'),
                                                                ('爱心社', '爱心社是武汉工程大学的一个以关爱弱势群体、传递爱心为主要宗旨的志愿公益类社团。社团通过组织各类爱心活动，弘扬奉献精神，营造关爱他人的校园文化氛围。', '定期探访孤寡老人、孤儿院等，为他们送去生活物资和精神慰藉；组织爱心义卖、募捐等活动，筹集善款用于帮助贫困学生和弱势群体；开展爱心支教活动，为偏远地区的孩子们带去知识和关爱。', '爱心社的活动得到了学校师生和社会各界的积极响应和支持，其爱心行动不仅帮助了许多需要帮助的人，也在校园内树立了良好的公益形象，激励更多学生参与到志愿服务中来。');

-- 创新创业类
INSERT INTO clubs (name, background, activities, highlight) VALUES
                                                                ('聚星团队', '聚星团队以''汇聚星光，点亮梦想''为愿景，旨在通过团队合作和创新实践，培养学生的创业精神和能力。', '开展创业培训课程，涵盖商业计划书撰写、市场营销、团队管理等方面；组织创业项目孵化，为有潜力的创业项目提供指导和支持；举办创业经验分享会，邀请成功创业者分享经验和心得。', '注重团队协作和项目实践，通过真实的创业项目锻炼学生的实际操作能力，为未来的创业之路积累宝贵经验。'),
                                                                ('三创营', '三创营以''创新、创意、创业''为理念，致力于打造一个集学习、实践、交流于一体的创新创业平台。', '定期举办创新创业讲座和论坛，邀请行业专家和成功创业者分享前沿动态和实战经验；开展创新项目实践，鼓励学生跨学科组队，共同探索和解决实际问题；组织创业竞赛和项目路演，为优秀项目提供展示和资源对接的机会。', '成功孵化了多个创新创业项目，部分项目获得了相关领域的认可和投资，为学生的创新创业之路提供了有力支持。'),
                                                                ('玖酿社', '玖酿社成立于2019年，以精酿啤酒项目为核心，通过啤酒酿造活动和销售，培养学生的创新创业能力。', '定期开展啤酒酿造实践，让学生亲身体验啤酒制作过程；举办啤酒文化讲座，介绍啤酒的历史、种类和酿造工艺；组织创业经验分享会，邀请成功创业者分享经验和心得。', '成功打造了具有校园特色的精酿啤酒品牌，产品在学校及周边地区受到好评，为学生创业提供了实际案例和经验。'),
                                                                ('KAB创业俱乐部', 'KAB创业俱乐部是由武汉工程大学学生发起，经共青团中央KAB推广委员会批准成立，由武汉工程大学团委领导、KAB专业的创业导师辅导的跨流芳和武昌两个校区的学生组织。俱乐部旨在培养大学生的创业意识和能力，为有创业想法的学生提供交流和实践的平台。', '开设创业培训课程，涵盖创业基础知识、商业计划书撰写、市场营销等方面；举办创业计划大赛，激发学生的创业热情和创新思维；开展创业项目孵化，为优秀的创业项目提供指导和支持。', '注重理论与实践相结合，通过系统的创业培训和实际的项目孵化，全面提升学生的创业能力和综合素质，为未来的创业之路奠定坚实基础。');

ALTER TABLE clubs
    ADD COLUMN type INT ;

ALTER TABLE clubs CHANGE imagePath imagepath VARCHAR(255);