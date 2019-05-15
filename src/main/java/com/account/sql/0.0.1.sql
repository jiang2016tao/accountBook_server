/*创建数据库*/
create database account_book charset utf8 collate utf8_general_ci;
create table users (
	id bigint(20) not null primary key auto_increment comment '用户Id',
	user_name varchar(50) not null comment '用户名称',
	user_password varchar(50) not null comment '用户密码'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户表';