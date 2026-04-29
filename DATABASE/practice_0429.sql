create table CUSTOMERS (
              CUSTOMER_ID NUMBER,
              NAME        VARCHAR2(50),
              CITY        VARCHAR2(50)
          );

create table PRODUCTS (
             PRODUCT_ID   NUMBER,
             PRODUCT_NAME VARCHAR2(100),
             PRICE        NUMBER
         );

create table ORDERS (
           ORDER_ID    NUMBER,
           CUSTOMER_ID NUMBER,
           ORDER_DATE  DATE
       );

create table ORDER_ITEMS (
                ORDER_ID   NUMBER,
                PRODUCT_ID NUMBER,
                QUANTITY   NUMBER
            );

-- 고객
INSERT INTO CUSTOMERS VALUES (1, '홍길동', '서울');
INSERT INTO CUSTOMERS VALUES (2, '김철수', '부산');

-- 상품
INSERT INTO PRODUCTS VALUES (1, '노트북', 1000000);
INSERT INTO PRODUCTS VALUES (2, '마우스', 20000);

-- 주문
INSERT INTO ORDERS VALUES (1, 1, SYSDATE);
INSERT INTO ORDERS VALUES (2, 2, SYSDATE);

-- 주문상세
INSERT INTO ORDER_ITEMS VALUES (1, 1, 1);
INSERT INTO ORDER_ITEMS VALUES (1, 2, 2);
INSERT INTO ORDER_ITEMS VALUES (2, 2, 1);

commit;
select * from CUSTOMERS;
select * from PRODUCTS;
select * from ORDERS;
select * from ORDER_ITEMS;

-- 1. 주문한 고객 이름과 주문일 조회 (주문을 한 고객의 이름과 주문일을 조회하시오.)
select c.NAME, o.ORDER_DATE
from CUSTOMERS c
join ORDERS o on c.CUSTOMER_ID = o.CUSTOMER_ID;

-- 2. 주문한 상품 이름 조회 (각 주문번호에 포함된 상품명을 조회하시오.)
select oi.ORDER_ID, p.PRODUCT_NAME
from PRODUCTS p
join ORDER_ITEMS oi on p.PRODUCT_ID = oi.PRODUCT_ID;

-- 3. 고객이 주문한 상품 목록 조회 (고객이 어떤 상품을 몇 개 주문했는지 조회하시오)
select c.name, p.PRODUCT_NAME, oi.quantity
from ORDERS o
join CUSTOMERS c on o.CUSTOMER_ID = c.CUSTOMER_ID
join ORDER_ITEMS oi on o.ORDER_ID = oi.ORDER_ID
join PRODUCTS p on oi.PRODUCT_ID = p.PRODUCT_ID;

-- 4. 고객별 총 구매 금액 계산 (고객별로 총 구매 금액을 계산하시오.)
select c.name, sum(p.price * oi.quantity)
from ORDERS o
join CUSTOMERS c on o.CUSTOMER_ID = c.CUSTOMER_ID
join ORDER_ITEMS oi on o.ORDER_ID = oi.ORDER_ID
join PRODUCTS p on oi.PRODUCT_ID = p.PRODUCT_ID
group by c.name;

-- 5. 주문하지 않은 고객도 포함하여 조회 (모든 고객을 기준으로 주문 여부를 조회하시오.)
select c.name, o.ORDER_ID
from CUSTOMERS c
left join ORDERS o on c.CUSTOMER_ID=o.ORDER_ID;

-- 6. 서울에 사는 고객이 주문한 상품 조회 (서울에 사는 고객이 주문한 상품명을 조회하시오.)
select c.name,p.PRODUCT_NAME
from CUSTOMERS c
join ORDERS o on o.CUSTOMER_ID = c.CUSTOMER_ID
join ORDER_ITEMS oi on o.ORDER_ID = oi.ORDER_ID
join PRODUCTS p on oi.PRODUCT_ID = p.PRODUCT_ID
where c.CITY='서울';

-- 7. 총 구매 금액이 100만원 이상 고객 조회 (총 구매 금액이 100만원 이상 고객 조회)
select c.name, sum(p.PRICE*oi.QUANTITY)
from ORDERS o
join CUSTOMERS c on o.CUSTOMER_ID = c.CUSTOMER_ID
join ORDER_ITEMS oi on o.ORDER_ID = oi.ORDER_ID
join PRODUCTS p on oi.PRODUCT_ID = p.PRODUCT_ID
group by c.name
having
    sum(p.PRICE*oi.QUANTITY) >1000000;

-- 8. 가장 많이 팔린 상품 TOP 1 조회 (전체 주문 내역에서 가장 많이 팔린 상품 1개를 조회하시오.)
select p.PRODUCT_NAME, count(oi.QUANTITY)
from PRODUCTS p
join ORDER_ITEMS oi on oi.PRODUCT_ID = p.PRODUCT_ID
group by  p.PRODUCT_NAME
order by count(p.PRODUCT_NAME) desc
fetch first 1 rows only ;

-- 9. 고객별 최근 주문일 조회 (고객별로 가장 최근 주문일을 조회하시오.)
select c.name, max(o.ORDER_DATE)
from CUSTOMERS c
join ORDERS o on o.CUSTOMER_ID = c.CUSTOMER_ID
group by c.name ;

-- 10. 주문 금액 기준 상위 고객 TOP 3 (총 주문 금액이 높은 고객 상위 3명을 조회하시오.)
select c.name, sum(p.PRICE*oi.QUANTITY)
from ORDERS o
         join CUSTOMERS c on o.CUSTOMER_ID = c.CUSTOMER_ID
         join ORDER_ITEMS oi on o.ORDER_ID = oi.ORDER_ID
         join PRODUCTS p on oi.PRODUCT_ID = p.PRODUCT_ID
group by c.name
order by sum(p.PRICE*oi.QUANTITY) desc
fetch first 3 rows only ;

--JAVA와 SQL에 대한 개발자 이해

--crud
--select한 결과를 Java API에 담는다 > 한 건의 row, 여러 건의 row
--그러면 객체 하나를 row 하나, 객체 여러 개를 여러 건의 row
-- DB 테이블 동일한 class 하나 > 한 건 -> row 객체 하나 > 여러 건 -> row 객체 여러개 (Array, Collection)

--MVC패턴
--Model : Java
--DTO(VO,DOMAIN) >Table 하나당 Class 하나 > create table emp() > class Emp()
--JOIN 쿼리 > 담고 싶다 > 결과를 담는 DTO(JOIN)
--DTO 만들고 싶지 않다 > Map > 사용 > List에 Map을 담는다
--DAO (DB연결, CRUD 기능 구현)

--DTO 작업
--DAO
/*
CRUD 기능 가지고 있는 함수
Read > 한건, 여러건

JDBC API  기준 (DML auto commit)
1. 전체 조회 : select * from emp;
2. 조건 조회 : select * from emp where empno=? >emp=7788;
3. 삽입 : insert into emp(empno,ename,job,sal,hiredate) values(?,?,?,?,?);
4. 수정 : update emp set ename =?, job=?, sal=?, hiredate=? where empno=?;
5. 삭제 : delete from emp where empno=?;

1~5 처리하는 함수
1.  public list<Emp> getEmpList(){return null;}
2.  public Emp getEmpListByEmpno(int empno){return null;}
3.  public int insertEmp(Emp emp){return 0;}
4.  public int updateEmp(Emp emp){return 0;}
5.  public int deleteEmp(int empno){return 0;}
추가사항 :  Like 등등

작업 하나당 DAO 하나
mybatis, jpa가 어느 정도 자동화해준다
*/

-- DDL 부분
select * from tab;
select * from tab where tname ='EMP';
select * from col where tname ='EMP';
select * from USER_CONSTRAINTS where table_name ='EMP';
--오라클은 not null을 제약조건으로 본다 (DB마다 차이, MS-SQL not null 옵션)

--가상컬럼(조합 컬럼)
--정규화 입장에서 총점은 관리되어야 하는 컬럼이 된다
--많이 쓰는 컬럼은 계산하는 것보다 넣어두는 게 좋다 (조회 성능 측면)

--학생 성적 테이블
--학번, 국어, 영어, 수학, 합계
--데이터 무결성 >> 국어 변경 >> 합계도 변경 보장 = 가상컬럼 >> 무결성 보장 (계산된 컬럼 or Trigger)

create table vtab(
    no1 number,
    no2 number,
    no3 number generated always as (no1+no2) virtual
);

insert into vtab (no1, no2)
values (100,50);

select * from vtab;

update vtab
set no1 = 50;

select * from vtab;
commit;

--실무에서 사용하는 코드
--제품 (입고) : 분기별 데이터 추출(1,2,3 1분기 ... 10,11,12 4분기)
create table vtable2(
                        no number, --순번
                        p_code char(4), --제품코드 (A001 , B003)
                        p_date char(8), --입고일 (20250101 , 20251010)
                        p_qty  number, --수량
                        p_bungi number(1) GENERATED ALWAYS as (
                            case when substr(p_date,5,2) in ('01','02','03') then 1
                                 when substr(p_date,5,2) in ('04','05','06') then 2
                                 when substr(p_date,5,2) in ('07','08','09') then 3
                                 else 4
                                end
                            ) VIRTUAL --많이 쓰는 데이터이기 때문에
);

insert into vtable2(p_date) values('20240101');
insert into vtable2(p_date) values('20240501');
insert into vtable2(p_date) values('20240601');
insert into vtable2(p_date) values('20241201');
commit;

select * from vtable2;
select * from vtable2 where p_bungi = 1;
--정규화 입장에서 합계는 있을 필요가 없다 -> 하지만 써야할 때가 생긴다 (virtual)

--기본적인 DDL 수업
--1. 테이블 생성하기
create table temp6(id number);
desc temp6;

--2. 테이블 생성 후에 컬럼 추가하기
alter table temp6
add ename varchar2(20);
desc temp6;

--3.  기존 테이블에 있는 컬럼이름 (ename -> username) Tool 사용
alter table temp6
rename column ename to username;
desc temp6

--4. 기존 테이블에 있는 기존 컬럼의 타입 크기 수정(modify)
alter table temp6
modify (username varchar2(2000));
desc temp6;

--5. 기존 테이블에 있는 기존 컬럼 삭제
alter table temp6
drop column username;
desc temp6;

--Tool , Script, AI 자유의 영역

--6. 테이블에 있는 데이터가 필요 없어요
--delete from emp;
--처음 테이블 생성 : 1Kbyte> 10만건 (10M)> delete ... 10만건 삭제> 테이블의 크기 (10M) (크기는 줄어들지 않아요)

--truncate 데이터 삭제 (크기도 줄어요)
--truncate 단점 : where절 불가능
--truncate table emp; 데이터 삭제, 공간 삭제

--7. drop table
--기본 DDL END

--DDL + 제약조건

/*
제 약 조 건
설     명
PRIMARY KEY(PK)  >> 유일하게 테이블의 각행을 식별(NOT NULL과 UNIQUE조건을 만족)
FOREIGN KEY(FK)  >> 열과 참조된 열 사이의 외래키 관계를 적용하고 설정합니다.
UNIQUE key(UK)   >> 테이블의 모든 행을 유일하게 하는 값을 가진 열(NULL을 허용)
NOT NULL(NN)     >> 열은 NULL값을 포함할 수 없습니다.
CHECK(CK)        >> 참이어야 하는 조건을 지정함(대부분 업무 규칙을 설정)
*/

/*
PRIMARY KEY(PK) : not null, unique (유일값 보장)
emp > empno 컬럼 > pk > where empno=7788 > 데이터 1건 보장
PK : 조회를 많이 한다 > 성능 > index(default)

PK : 테이블당 몇 개 -> 1개 > 컬럼 1개 > 여러 개의 컬럼을 묶어서 1개 (복합키)

언제 사용?
1. create table 생성 PK
2. create table 생성 후에 필요에 따라서 (alter table add constraint ...)
3. select * from user_constraints;
4. select * from user_constraints where table_name = 'EMP';
 */

create table temp7( --id number primary key 권장하지 않아요 오라클 서버가 자동으로 제약이름
    id number constraint pk_temp7_id primary key, --동작_테이블명_컬럼명
    --제약 조건 이름이 누락되었거나 부적합합니다.
    name varchar2(20) not null, --SYS_C008303 시스템이 붙인 이름
    addr varchar2(50)
);

desc temp7;
select * from USER_CONSTRAINTS where table_name ='TEMP7'; --테이블명 대문자
select * from USER_INDEXES where table_name = 'TEMP7';

-- not null, unique
--Unique (uk)
--내부적으로 index 생성
--중복값을 허용하지 않는다
-- null 허용 (null은 중복 체크 하지 않는다)
--제약 2가지 : not null, unique
--몇 개? 테이블당 컬럼수 만큼

create table temp8(
                      id number constraint pk_temp8_id primary key,
                      name varchar2(20) not null,
                      jumin nvarchar2(6) constraint uk_temp8_id unique, --null 중복 허용
                      addr varchar2(50)
);

desc temp8;
select * from USER_CONSTRAINTS where table_name = 'TEMP8';
select * from USER_INDEXES where table_name = 'TEMP8';

insert into temp8(id,name,jumin,addr)
values(1,'김유신', '123456','한양');

insert into temp8(id,name,jumin,addr)
values(2,'유신', '123456','강남'); --무결성 제약 조건(KOSA. UK_TEMP8_ID) 에 위배됩니

insert into temp8(id,name,addr)
values(2,'유신','강남');

insert into temp8(id,name,addr)
values(3,'신','강북');

select  * from temp8;
commit;

--테이블 생성 후 나중에 제약 조건 건다면
create table temp9(id number);

alter table temp9
add constraint pk_temp9_id primary key(id);

select * from USER_CONSTRAINTS where table_name = 'TEMP9';

-- 복합키
-- alter table temp9
-- add constraint pk_temp9_id_num primary key(id,num);

create table temp10(id number , userid number);

alter table temp10
    add constraint pk_temp10_id_userid primary key(id,userid);

select * from user_constraints where table_name='TEMP10';
SELECT   *  FROM USER_INDEXES where table_name='TEMP10';

/*
복합키 (순서) 조회 ... index > Table full scan
1. where id=1 and userid='hong'
2. where id=1
3. where userid='hong'  Table Full Scan - 비효율
*/

--ETC not null 추가하고싶어요 가능
--alter table temp9
--modify (ename not null)

-- Check 제약 (무결성 보장하는데 탁월)
-- 업무규칙 : where gender in ('남','여'), where age >=19

create table temp11(
    id number constraint pk_temp11_id primary key,
    name varchar2(20) not null,
    jumin nvarchar2(6) constraint uk_temp11_jumin unique,
    addr varchar2(50),
    age number constraint  ck_temp11_age check ( age>=19 )--Check 제약은 where 빼고 조건 쓰면 된다
);

select * from USER_CONSTRAINTS where table_name = 'TEMP11';
select * from USER_INDEXES where table_name = 'TEMP11';

--Foreign 키
--1. 관계를 정의 (1:1 , 1:N , M:N)
--2. 관계 안에서 참조 ....

create table c_emp
as
select empno , ename , deptno from emp where 1=2;

create table c_dept
as
select deptno , dname from dept where 1=2;

select * from c_emp;
select * from c_dept;


select * from user_constraints where table_name='C_EMP';
select * from user_constraints where table_name='C_DEPT';

--중요 (PK 선행)
alter table c_dept
    add constraint pk_c_dept_deptno primary key(deptno);


alter table c_emp
    add constraint fk_c_emp_deptno foreign key(deptno) references c_dept(deptno);

--------------------------------------------------------------------------------
--부서
insert into c_dept(deptno , dname) values(100,'인사팀');
insert into c_dept(deptno , dname) values(200,'관리팀');
insert into c_dept(deptno , dname) values(300,'회계팀');
commit;


--신입입사
insert into c_emp(empno,ename,deptno)
values(1,'신입이',400);  --참조제약
--ORA-02291: 무결성 제약조건(KOSA.FK_C_EMP_DEPTNO)이 위배되었습니다- 부모 키가 없습니다

insert into c_emp(empno,ename,deptno)
values(1,'신입이',100);
select * from c_emp;
commit;

--개발자 관점에서 fk 바라보기
/*
1. master와 detail에 관한 문제
c_emp, c_dept, fk(deptno) 관계에서 (pk를 주고 있는게 master)
부모 : c_dept 자식 : c_emp
master : c_dept - detail : c_emp

2. 데이터 삭제
참조되고 있는 데이터 삭제 금지
delete from d_dept where deptno=100

    1. 참조하고 있는 fk
    delete from c_emp where empno=1;

    2. pk 데이터 삭제
    delete from d_dept where deptno=100
    + 추가질문) c_dept 참조되고 있는 상황에서 삭제 -> cascade (실무에서 쓰지 말기, 이런 게 있다~)
*/

/*
column datatype [CONSTRAINT constraint_name]
 REFERENCES table_ name (column1[,column2,..] [ON DELETE CASCADE])
column datatype,
. . . . . . . ,
[CONSTRAINT constraint_name] FOREIGN KEY (column1[,column2,..])
 REFERENCES table_name (column1[,column2,..] [ON DELETE CASCADE]


ON DELETE CASCADE 부모 테이블과 생명을 같이 하겠다

alter table c_emp
add constraint fk_c_emp_deptno foreign key(deptno)
references c_dept(deptno) ON DELETE CASCADE;


delete from c_emp where empno=1  >> deptno >> 100번
delete from c_dept where deptno=100; 삭제 안되요 (참조 하고 있으니까)

ON DELETE CASCADE 걸면 삭제돼요
delete from c_dept where deptno=100;  --> c_emp 1 번 사원데이터 삭제
부모삭제 >> 참조하고 있는 자식도 삭제


MS-SQL , my-sql
ON DELETE CASCADE
ON UPDATE CASCADE
*/

-- 조별과제
create table department(
                           department_id number constraint pk_department_id primary key,
                           name varchar2(50) not null
);

create table student_grade(
    student_id number constraint pk_department_id primary key,
    name varchar2(10) not null,
    korean number DEFAULT 0, english number DEFAULT 0, math number DEFAULT 0,
    department_id number references department(department_id),
    sum number generated always as (korean+ english +math) virtual,
    avg number generated always as (((korean+ english +math)/3))virtual
);

insert into department values(1,'멀티미디어공학과');
COMMIT;
insert into student_grade (student_id, name, korean, english, math, department_id) values(1,'안정민',100, 100, 100,1);

select s.STUDENT_ID,s.name,s.sum,s.avg,s.department_id,d.name from student_grade s
join department d on s.department_id = d.department_id;

/* 사원 */
CREATE TABLE EMP (
                     empno NUMBER NOT NULL, /* 사번 */
                     ename VARCHAR2(20), /* 이름 */
                     sal NUMBER, /* 급여 */
                     deptno NUMBER /* 부서번호 */
);

/* 부서 */
CREATE TABLE DEPT (
                      deptno NUMBER, /* 부서번호 */
                      dname VARCHAR2(20) /* 부서명 */
);

ALTER TABLE EMP
    ADD CONSTRAINT PK_EMP_EMPNO	PRIMARY KEY (empno);

ALTER TABLE DEPT
    ADD CONSTRAINT PK_DEPT_DEPTNO PRIMARY KEY (deptno);

ALTER TABLE EMP
    ADD CONSTRAINT FK_DEPT_TO_EMP FOREIGN KEY (deptno) REFERENCES DEPT (deptno);
--제약 END

--개발자에게 필요한 SQL
/*
 SEQUENCE 특징
1) 자동적으로 유일 번호를 생성합니다.
2) 공유 가능한 객체
3) 주로 기본 키 값을 생성하기 위해 사용됩니다.
4) 어플리케이션 코드를 대체합니다.
5) 메모리에 CACHE되면 SEQUENCE 값을 액세스 하는 효율성을 향상시킵니다.


CREATE  SEQUENCE  sequence_name
[INCREMENT  BY  n]
[START  WITH  n]
[{MAXVALUE n | NOMAXVALUE}]
[{MINVALUE n | NOMINVALUE}]
[{CYCLE | NOCYCLE}]
[{CACHE | NOCACHE}];
*/
desc board;
drop table board;


create table board (
                       boardid number constraint pk_board_boardid primary key,
                       title nvarchar2(50)
);

select * from user_constraints where table_name='BOARD';

-- PK : not null , unique , index (where )
/*
insert into board(boardid,title) values(1,'방가');
insert into board(boardid,title) values(2,'방가..');

boardid 들어가는 데이터 (규칙)

처음 : 1
그다음 : 2 , 3  1씩 증가하게 ...

*/
insert into board(boardid, title)
values((select count(boardid)+1 from board),'방가_1');

insert into board(boardid, title)
values((select count(boardid)+1 from board),'방가_2');

insert into board(boardid, title)
values((select count(boardid)+1 from board),'방가_3');

select * from board;

--이때 누군가 2번 삭제
delete from board where boardid=2;

select * from board;

insert into board(boardid, title)
values((select count(boardid)+1 from board),'방가_4');
--ORA-00001: 무결성 제약 조건(KOSA.PK_BOARD_BOARDID)에 위배됩니다

--글을 삭제 다시 쓰면 논리가 맞지 않아요 (x)
--max
rollback;

insert into board(boardid, title)
values((select nvl(max(boardid),0) + 1  from board),'방가_1');

insert into board(boardid, title)
values((select nvl(max(boardid),0) + 1  from board),'방가_2');

insert into board(boardid, title)
values((select nvl(max(boardid),0) + 1  from board),'방가_3');

select * from board;

delete from board where boardid=2;

select * from board;

insert into board(boardid, title)
values((select nvl(max(boardid),0) + 1  from board),'방가_4');

select * from board;
commit;

--고민해보기
--시퀀스 객체 (순번) 번호표뽑기
--공유객체

create sequence board_num;

--실 채번
select board_num.nextval from dual;  --채번
select board_num.currval from dual;  --마지막 채번 번호 확인

-- A  > board_num  > 1
-- B  > board_num  > 2
-- A  > board_num  > 3
-- A  > board_num  > 4
-- B  > board_num  > 5
--테이블마다 사용 공유객체 ....

create sequence kboard_num;

create table kboard(
                       num number constraint pk_kboard_num primary key,
                       title nvarchar2(20)
);

insert into kboard(num,title)
values(kboard_num.nextval, '글쓰기');

select * from kboard;

delete from kboard where num=2;

select * from kboard;

insert into kboard(num,title)
values(kboard_num.nextval, '글쓰기...');

select * from kboard;
--------------------------------------------------------------------------------
--sequence 객체
/*
1. 오라클  (0)
2. my-sql (x)
3. ms-sql (0) 버전: 2012버전
4. Mariadb(0)
5. PostgreSQL(0)

my-sql
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);
insert into orders(name) values('제목')


MS-sql
create table board(boardnum int identity(1,1) , title)
insert into board(title) values('제목');
*/
--
/* 옵션
CREATE  SEQUENCE  sequence_name
[INCREMENT  BY  n]
[START  WITH  n]
[{MAXVALUE n | NOMAXVALUE}]
[{MINVALUE n | NOMINVALUE}]
[{CYCLE | NOCYCLE}]
[{CACHE | NOCACHE}];
*/

create sequence seq_num
    start with 10
    increment by 2;

select seq_num.nextval from dual;

--------------------------------------------------------------------------------
---게시판 글
--1, 2, 3, 4 ..... 1000

--가장 최근에 쓴글 (글번호 가장 큰것)
--select * from board order by num desc
--------------------------------------------------------------------------------

-- 04.29 3시~
--rownum
--1. 의사컬럼 : 실제 물리적으로 존재하는 컬럼이 아니고 논리적 존재
--create table 생성되는 컬럼은 아니고
--rownum : 실제로 테이블에 컬럼으로 존재하지 않지만 내부적으로 행번호를 부여하는 컬럼

select * from emp;

select rownum, empno
from emp;

select rownum, empno,ename,sal --먼저 실행된다
from emp
order by sal; --뒤죽박죽 번호
--포인트 : rownum
-- From -> Where -> Rownum -> Select -> Order by
-- From -> Select -> Order by

-- 샘플
select *
from EMP
where rownum=3;
-- rownum은 서브쿼리 안에서 사용한다

--활용
select empno,ename,sal
from emp
order by sal -- 기준 데이터 가상 테이블

-- 1. 기준 데이터를 만든다 (subquery) > 순번을 부여 한다
select rownum, e.*
from(
        select empno,ename,sal
        from emp
        order by sal -- sal asc 후에 정렬된 데이터 순번을 가져온다 (기준 데이터 생성)
    ) e;

--상위 n명이라는 기준 설정 가능
--Top n 쿼리
--MS-SQL :select top 3, * from emp order by sal asc;

--Oracle (Top(n)-> x)
-- 1. 정렬 선행
-- 2. 정렬 기준 rownum 조건

select rownum,e.empno, e.ename, e.sal
from  (
          select empno,ename,sal
          from emp
          order by sal desc
      ) e;

-- 급여를 많이 받는 사원 5명
select *
from (
         select rownum as num,e.empno, e.ename, e.sal
         from  (
                   select empno,ename,sal
                   from emp
                   order by sal desc
               )e
     ) n where num<=5;
--게시판에 페이징 처리 (대용량 데이터 페이지 성능 -> rownum)

/*
rownum 자리 >> FETCH

OFFSET = 몇 개 건너뛸지
FETCH  = 몇 개 가져올지

SELECT 컬럼
FROM 테이블
ORDER BY 컬럼
OFFSET 시작위치 ROWS
FETCH NEXT 가져올개수 ROWS ONLY;

1페이지 (1~10번)
SELECT *
FROM BOARD
ORDER BY ID DESC
OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY;

(11~20)
SELECT *
FROM BOARD
ORDER BY ID DESC
OFFSET 10 ROWS FETCH NEXT 10 ROWS ONLY;
*/

--게시판 paging 처리

/*
 게시판 데이터 104건
 total account = 104
 pagesize =10 (한 화면에 보여지는 데이터 수 10 row)
 pagecount > 11개 page

 [1] [2] [3] [4] [5] [6] [7] [8] [7] [10] [11]
 <a href = "list.do?page=1">[1]</a>

 10개의 묶음에서 첫 번째 묶음
 1~10 > num between 1 and 10
 11~20 > num between 11 and 20

 startrow = ?
 endrow = ?

 [1] [2] [3] [4] [5] 다음
 [6] [7] [8] [7] [10] 다음
 이전 [11] -4건
*/

--HR로 이동
show user;

select count(*) from employees; --107
--pagesize=10
--pagecount=11
-- [1] [2] [3] [4] [5] [6] [7] [8] [7] [10] [11]
-- [1] 클릭 1~10 데이터

--1.1 단계 (기준 데이터 : 정렬) 사번
select *
from employees
order by  employee_id asc;

--2.2단계 (기준 데이터 순번)
select rownum as num, e.*
from (
         select *
         from employees
         order by  employee_id asc
    ) e
where rownum<=50;

--[1][2][3]
--3.3 단계
-- 1번 클릭
select *
from(
        select rownum as num, e.*
        from (
                 select *
                 from employees
                 order by  employee_id asc
             ) e
        where rownum<=10
    ) ee where num>=1;

-- 2번 클릭
select *
from(
        select rownum as num, e.*
        from (
                 select *
                 from employees
                 order by  employee_id asc
             ) e
        where rownum<=20
    ) ee where num>=11; -- 데이터 다 가져 와야 해서 비효율

-- 3번 클릭
select *
from(
        select rownum as num, e.*
        from (
                 select *
                 from employees
                 order by  employee_id asc
             ) e
        where rownum<=30
    ) ee where num>=21; -- 데이터 다 가져 와야 해서 비효율
--숫자만 바꾸면 된다

--그런데 시대의 흐름에 따라 세대교체
--1.
    /*
    1. 세대교체
    SELECT 컬럼
    FROM 테이블
    ORDER BY 컬럼
    OFFSET 시작위치 ROWS
    FETCH NEXT 가져올개수 ROWS ONLY;
    */
--2.
select *
from (
    select row_number() over (order by employee_id asc) as num, e.* --over 쿼리 장점
    from employees e
     ) n
where n.num between  1 and 10;

--오라클 12c 이상
SELECT *
FROM employees
ORDER BY employee_id
OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY;

--OFFSET : 시작 위치 지정 (0부터 시작)
--FETCH NEXT : 가져올 개수
--예: OFFSET 10 ROWS FETCH NEXT 10 ROWS ONLY; → 11번째부터 20번째까지

--그럼 성능
--Keyset Pagination FETCH NEXT 기반  WHERE employee_id > 100 제어가 가장 좋고
SELECT *
FROM employees
WHERE employee_id > 100
ORDER BY employee_id ASC
    FETCH NEXT 10 ROWS ONLY;

/*
12C 이상에서는 OFFSET/FETCH
그 이하 버전에서는 ROWNUM 페이징  쓰는 것이 일반적입니다
OFFSET/FETCH 와 ROWNUM 성능은 큰 차이가 없다 하니까요 ^^
*/

--4월 30일 수업 일정
--model, 개발자에게 필요한 sql -> PL/SQL -> 모델링