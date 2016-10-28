CREATE TABLE BOARD(
WRITER varchar2(20),
REGDATE DATE,
FTITLE varchar2(20),
CONTENTS varchar2(20),
CLICKCOUNT NUMBER,
FILENAME VARCHAR2(20),
BOARDNUM VARCHAR2(20),
CATEGORY VARCHAR2(20)
);

CREATE TABLE CONTEST(
TITLE varchar2(30),
SPONSER varchar2(30),
STARTDAY DATE,
ENDDAY DATE,
PHOTONAME varchar2(30),
CLICKCOUNT NUMBER,
CATEGORY VARCHAR2(30),
REGDATE DATE,
CONTESTNUM VARCHAR2(30)
);

CREATE TABLE TEAMBOARD(
TEAMID varchar2(20),
WRITER VARCHAR2(20),
TITLE varchar2(20),
CONTENTS varchar2(20),
CLICKCOUNT NUMBER,
FILENAME VARCHAR2(20),
BOARDNUM VARCHAR2(20),
CATEGORY VARCHAR2(20)
);

select * from member;
insert into member values('kwon','123','권푸름','푸름','kongs');
insert into member values('kim','111','김명환','명환','kongs');

drop table member;

create table member(
id varchar2(20) primary key,
pwd varchar2(20),
name varchar2(20),
nick varchar2(20),
teamid varchar2(20));

select * from board;

insert into board values('writer',sysdate,'ftitle','contents',0,'filename',seq_freeBoard.nextval,'category');
insert into board values('find-writer',sysdate,'find-ftitle','find-contents',0,'find-filename',seq_findBoard.nextval,'find-category');
insert into board values('free-writer',sysdate,'free-ftitle','free-contents',0,'free-filename',seq_freeBoard.nextval,'free-category');
insert into board values('find-writer',sysdate,'find-ftitle','find-contents',0,'find-filename',seq_findBoard.nextval,'find-category');

CREATE SEQUENCE seq_freeBoard
start with 1
increment by 1;

CREATE SEQUENCE seq_findBoard
start with 1
increment by 1;

drop sequence seq_freeBoard;
drop sequence seq_findBoard;

select * from contest;

insert into contest values('title','sponser',to_date('10/17/16','MM/DD//YY'),to_date('16/12/01','YY/MM/DD'),'photoname',0,'category',sysdate,seq_contestBoard.nextval);
insert into contest values('title','sponser',to_date('10/10/16','MM/DD/YY'),to_date('16/12/24','YY/MM/DD'),'photoname',0,'category',sysdate,seq_contestBoard.nextval);

drop sequence seq_contestBoard;
CREATE SEQUENCE seq_contestBoard
start with 1
increment by 1;

select * from teamboard;
delete from teamboard where clickcount=0;

insert into teamboard values('kongs','notice-writer','notice-title','notice-contents',0,'notice-filename',seq_noticeBoard.nextval,'notice-category');
insert into teamboard values('kongs','file-writer','file-title','file-contents',0,'file-filename',seq_fileBoard.nextval,'file-category');
insert into teamboard values('kongs','notice-writer2','notice-title2','notice-contents2',0,'notice-filename2',seq_noticeBoard.nextval,'notice-category');
insert into teamboard values('kongs','file-writer2','file-title2','file-contents2',0,'file-filename2',seq_fileBoard.nextval,'file-category');


drop sequence seq_noticeBoard;
drop sequence seq_fileBoard;

CREATE SEQUENCE seq_noticeBoard
start with 1
increment by 1;

CREATE SEQUENCE seq_fileBoard
start with 1
increment by 1;

CREATE SEQUENCE seq_contestBoard
start with 1
increment by 1;

update contest set photoname='photo1' where contestnum=1;
update contest set photoname='photo2' where contestnum=2;

commit;

select * from contest;

insert into contest values('공모1','sponser1',to_date('10/26/16','MM/DD//YY'),to_date('16/12/01','YY/MM/DD'),'Desert.jpg',0,'design',sysdate,seq_contestBoard.nextval);
insert into contest values('공모2','sponser2',to_date('10/22/16','MM/DD//YY'),to_date('16/01/01','YY/MM/DD'),'Hydrangeas.jpg',0,'advertisement',sysdate,seq_contestBoard.nextval);
insert into contest values('공모3','sponser3',to_date('11/02/16','MM/DD//YY'),to_date('16/03/01','YY/MM/DD'),'Lighthouse.jpg',0,'it',sysdate,seq_contestBoard.nextval);
insert into contest values('공모4','sponser4',to_date('11/05/16','MM/DD//YY'),to_date('16/12/01','YY/MM/DD'),'Penguins.jpg',0,'media',sysdate,seq_contestBoard.nextval);
insert into contest values('공모5','sponser5',to_date('10/27/16','MM/DD//YY'),to_date('16/12/16','YY/MM/DD'),'Tulips.jpg',0,'idea',sysdate,seq_contestBoard.nextval);
insert into contest values('공모6','sponser6',to_date('11/17/16','MM/DD//YY'),to_date('16/12/22','YY/MM/DD'),'border.png',0,'design',sysdate,seq_contestBoard.nextval);
insert into contest values('공모7','sponser7',to_date('09/09/16','MM/DD//YY'),to_date('16/10/29','YY/MM/DD'),'naver.gif',0,'advertisement',sysdate,seq_contestBoard.nextval);
insert into contest values('공모8','sponser8',to_date('10/13/16','MM/DD//YY'),to_date('16/11/01','YY/MM/DD'),'test.jpeg',0,'idea',sysdate,seq_contestBoard.nextval);
