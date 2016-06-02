--Create the database.
create database seckill;

--������ɱ��

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
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='��ɱ����';

--��Ӳ�������
INSERT INTO `seckill`.`seckill` (`name`, `number`, `start_time`, `end_time`) 
VALUES ('10Ԫ��ɱƻ������', '200', '2016-06-10 12:00:00', '2016-06-12:00:00');

--��ɱ�ɹ���
create table success_killed(
seckill_id bigint not null comment '��ɱ��Ʒid',
user_phone bigint not null comment '�û��ֻ���',
state tinyint not null default -1 comment '��ɱ״̬ -1:��Ч��0���ɹ��� 1���Ѹ���',
create_time timestamp comment '��ɱ�ɹ�ʱ��',
primary key (success_killedsuccess_killedseckill_id, user_phone),
key idx_create_time (create_time)
)engine=INNODB default charset=utf8 comment '��ɱ�ɹ���ϸ��'