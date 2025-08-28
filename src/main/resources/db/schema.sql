
-- 学生表
CREATE TABLE student (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         student_id VARCHAR(20) NOT NULL UNIQUE COMMENT '学号',
                         name VARCHAR(50) NOT NULL COMMENT '姓名',
                         password VARCHAR(255) NOT NULL COMMENT '密码',
                         major VARCHAR(50) NOT NULL COMMENT '专业',
                         grade ENUM('大一','大二') NOT NULL COMMENT '年级',
                         phone VARCHAR(11) NOT NULL COMMENT '手机号',
                         email VARCHAR(100) NOT NULL COMMENT '邮箱'
);

-- 管理员表
CREATE TABLE admin (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       admin_id VARCHAR(20) NOT NULL UNIQUE COMMENT '管理员编号',
                       name VARCHAR(50) NOT NULL COMMENT '姓名',
                       password VARCHAR(255) NOT NULL COMMENT '密码',
                       phone VARCHAR(11) NOT NULL COMMENT '手机号'
);

-- 面试学生表
CREATE TABLE interview_student (
                                   id INT PRIMARY KEY AUTO_INCREMENT,
                                   student_id VARCHAR(20) NOT NULL UNIQUE COMMENT '学号',
                                   name VARCHAR(50) NOT NULL COMMENT '姓名',
                                   email VARCHAR(100) NOT NULL COMMENT '邮箱',
                                   phone VARCHAR(11) NOT NULL COMMENT '手机号',
                                   direction ENUM('嵌入式', '后端', '前端', '安卓') NOT NULL COMMENT '意向方向',
                                   interview_method ENUM('秋面', '霸面') NOT NULL COMMENT '面试方式',
                                   motto VARCHAR(500) COMMENT '座右铭',
                                   interview_time DATETIME COMMENT '面试时间'
);

-- 面试结果表
CREATE TABLE interview_result (
                                  id INT PRIMARY KEY AUTO_INCREMENT,
                                  student_id VARCHAR(20) NOT NULL COMMENT '学号',
                                  student_name VARCHAR(50) NOT NULL COMMENT '学生姓名',
                                  direction ENUM('嵌入式', '后端', '前端', '安卓') NOT NULL COMMENT '意向方向',
                                  interview_method ENUM('秋面', '霸面') NOT NULL COMMENT '面试方式',
                                  status ENUM('待面试', '已通过', '未通过') DEFAULT '待面试' COMMENT '面试状态',
                                  remark TEXT COMMENT '备注',
                                  interview_time DATETIME COMMENT '面试时间'
);