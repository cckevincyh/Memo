create database MemoDao;

use MemoDao;
drop table tb_memo;
create table tb_memo(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` varchar(200) NOT NULL,
  `memotype` varchar(20) NOT NULL,
  `memotime` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `tb_memo` (`id`,`username`,`title`,`content`,`memotype`,`memotime`) 
VALUES (1,"小明","文稿","今天要开会","会议","2016-03-05");

select * from tb_memo;