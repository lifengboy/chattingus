
CREATE DATABASE IF NOT EXISTS chattingus;

USE chattingus;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`user_id` INT(10) AUTO_INCREMENT PRIMARY KEY comment '用户主键id',
	`username` VARCHAR(128) UNIQUE NOT NULL comment '用户名',
	`password`	VARCHAR(128) NOT NULL comment '密码',
	`nick`	VARCHAR(128) NOT NULL  comment '用户昵称',
	`avatar`	VARCHAR(256)   comment '用户头像',
	`real_name`	VARCHAR(128) comment '真实姓名',
	`birthday`	VARCHAR(128) NOT NULL default '1995-8-28' comment '生日',
	`gender`	VARCHAR(2) NOT NULL default 'm' comment '性别',
	`tel`	VARCHAR(11) comment '手机号码',
	`register_date`	timestamp NOT NULL default now() comment '注册日期',
	`e_mail`	VARCHAR(128) comment '电子邮件',
	`privilege`	VARCHAR(10) NOT NULL default 'user'  comment '权限，分为user和manager，默认user',
	`status`	VARCHAR(2) NOT NULL default '0'  comment '状态，0是离线状态，1表示在线'
);
INSERT INTO user(`user_id`, `username`, `password`, `nick`, `real_name`, `tel`, `e_mail`, `privilege`) VALUES(1, 'lifeng', 'lifeng', '管理员','李风','15973667676', '1039415356@qq.com','manager');
INSERT INTO user(`user_id`, `username`, `password`, `nick`, `real_name`, `tel`, `e_mail`) VALUES(2, 'account1', 'account1', '小明', '账号1','15973667611', 'aaa@qq.com');
INSERT INTO user(`user_id`, `username`, `password`, `nick`, `real_name`, `tel`, `e_mail`) VALUES(3, 'account2', 'account2', '小张', '账号2','15973667612', 'bbb@qq.com');
INSERT INTO user(`user_id`, `username`, `password`, `nick`, `real_name`, `tel`, `e_mail`) VALUES(4, 'account3', 'account3', '小王', '账号3','15973667613', 'ccc@qq.com');
INSERT INTO user(`user_id`, `username`, `password`, `nick`, `real_name`, `tel`, `e_mail`) VALUES(5, 'account4', 'account4', '小李', '账号4','15973667614', 'ddd@qq.com');
INSERT INTO user(`user_id`, `username`, `password`, `nick`, `real_name`, `tel`, `e_mail`) VALUES(6, 'account5', 'account5', '小丽', '账号5','15973667615', 'eee@qq.com');
INSERT INTO user(`user_id`, `username`, `password`, `nick`, `real_name`, `tel`, `e_mail`) VALUES(7, 'account6', 'account6', '小陈', '账号6','15973667616', 'fff@qq.com');


DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
	`id` INT(20) AUTO_INCREMENT PRIMARY KEY comment 'id',
	`user_id` INT(10) NOT NULL comment '用户主键id',
	`friend_id`	INT(10) NOT NULL comment '好友主键id'
);



