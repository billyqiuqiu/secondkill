--Create the database.
create database seckill;

--创建秒杀表

CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  `number` int(11) NOT NULL,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

--添加测试数据
INSERT INTO `seckill`.`seckill` (`name`, `number`, `start_time`, `end_time`) 
VALUES ('10元秒杀苹果耳机', '200', '2016-06-10 12:00:00', '2016-06-12:00:00');

--秒杀成功表
create table success_killed(
seckill_id bigint not null comment '秒杀商品id',
user_phone bigint not null comment '用户手机号',
state tinyint not null default -1 comment '秒杀状态 -1:无效。0：成功： 1：已付款',
create_time timestamp comment '秒杀成功时间',
primary key (success_killedsuccess_killedseckill_id, user_phone),
key idx_create_time (create_time)
)engine=INNODB default charset=utf8 comment '秒杀成功明细表'