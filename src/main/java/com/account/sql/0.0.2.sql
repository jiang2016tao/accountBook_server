/**创建账户信息*/
create table account_info (
	id bigint(20) not null primary key auto_increment comment '账户Id',
	account_name varchar(256) not null comment '账户名称',
	parent_id bigint(20) not null default -1 comment '父账户Id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '账户信息';