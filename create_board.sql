show tables;

drop table mvc_board;

create table mvc_board (
bId int primary key auto_increment,
bName char(20),
 bTitle char(100),
 bContent char(255),
 bDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 bHit int,
 bGroup int,
 bStep int,
 bIndent int
);

//create sequence mvc_board_seq;

select * from mvc_board;