/*
@C:\Edu\DataBase\Data\Data.sql

RDBMS (Relational Database Management System) 정의
관계형 데이터베이스 관리 시스템(RDBMS, Relational Database Management System)은
데이터를 테이블(Table) 형식으로 관리하는 데이터베이스 시스템입니다.

1. RDBMS 특징
테이블 기반 데이터 저장: 데이터를 행(Row)과 열(Column)로 구성된 테이블 형태로 저장
관계(Relation) 형성: 테이블 간 키(Key) 를 이용하여 관계를 설정
SQL (Structured Query Language) 사용: 데이터를 조작하고 조회하는 표준 언어 제공
데이터 무결성 유지: 제약 조건(Constraints) 을 통해 데이터의 정확성 보장
트랜잭션 지원: ACID (원자성, 일관성, 고립성, 지속성) 원칙 준수


2. RDBMS 주요 개념
테이블(Table): 데이터를 저장하는 기본 단위 (엑셀의 시트와 유사)
레코드(Record) / 행(Row): 테이블의 한 줄 (한 개의 데이터 항목)
속성(Attribute) / 열(Column): 데이터의 속성을 나타내는 필드
기본 키(Primary Key, PK): 테이블에서 각 행을 유일하게 식별하는 키
외래 키(Foreign Key, FK): 다른 테이블의 기본 키를 참조하여 관계 설정

3. RDBMS의 대표적인 시스템
MySQL: 오픈 소스 RDBMS, 웹 애플리케이션에서 널리 사용됨
PostgreSQL: 강력한 기능을 제공하는 오픈 소스 RDBMS
Oracle Database: 기업 환경에서 많이 사용되는 고성능 RDBMS
Microsoft SQL Server: 마이크로소프트에서 개발한 RDBMS
MariaDB: MySQL의 포크로 높은 성능과 안정성을 제공

4. RDBMS의 장점
 데이터 정합성 보장: 키와 관계를 통해 데이터의 일관성 유지
 중복 최소화: 정규화를 통해 데이터 중복을 방지
 강력한 쿼리 기능: SQL을 사용하여 복잡한 데이터 검색 가능
 데이터 보안: 사용자 권한 관리 및 접근 제어 가능

5. RDBMS의 단점
 확장성 제한: 대량의 데이터를 처리하는 데 한계가 있음 (수직 확장 필요)
 복잡한 스키마: 변경이 어려워 유연성이 낮음
 고비용: 일부 상용 RDBMS는 라이선스 비용이 높음
*/


/*
1. 오라클 소프트웨어 다운로드
https://www.oracle.com/database/technologies/xe-downloads.html

2. Oracle Database 21c Express Edition (XE) (무료설치)

3. Oracle 설치(SYS, SYSTEM 계정의 대한 암호 : 1004)

4.Sqlplus 프로그램 제공(CMD) : GUI 환경 일반개발자 사용 불편

5.별도의 Tool 설치 무료(SqlDeveloper , https://dbeaver.io/)  ,
                 유료(토드 , 오렌지 , SqlGate) 프로젝트시 설치 활용 ^^

6. SqlDeveloper 툴을 통해서 Oracle Server 접속 ....
   >> HR 계정 :

   6.1 스키마 다운로드 :  https://github.com/oracle-samples/db-sample-schemas
   6.2 압출 풀어서 저장 : C:\Edu\DataBase\Data
   6.3 C:\Edu\DataBase\Data\db-sample-schemas-main\human_resources\hr_install.sql
   6.4 SYSTEM 계정에서
   질의 작성기 창에서
   @C:\Edu\DataBase\Data\db-sample-schemas-main\human_resources\hr_install.sql
   ctrl + enter
   >암호입력
   >Users
   >Yes
   실습 계정 생성 및 테이블 스크립트 생성


7.새로운 계정 계정 확인 : "KOSA"
7.1 마우스 SYSTEM 계정에서 새 사용자 생성을 통해서 ...

-- USER SQL
CREATE USER "KOSA" IDENTIFIED BY "1004"
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";

-- QUOTAS
ALTER USER "KOSA" QUOTA UNLIMITED ON "USERS";

-- ROLES
GRANT "CONNECT" TO "KOSA" WITH ADMIN OPTION;
GRANT "RESOURCE" TO "KOSA" WITH ADMIN OPTION;
ALTER USER "KOSA" DEFAULT ROLE "CONNECT","RESOURCE";

-- SYSTEM PRIVILEGES
*/

/*
실습코드

CREATE TABLE EMP
(EMPNO number not null,
ENAME VARCHAR2(10),
JOB VARCHAR2(9),
MGR number ,
HIREDATE date,
SAL number ,
COMM number ,
DEPTNO number );
--alter session set nls_date_format='YYYY-MM-DD HH24:MI:SS';

INSERT INTO EMP VALUES
(7369,'SMITH','CLERK',7902,'1980-12-17',800,null,20);
INSERT INTO EMP VALUES
(7499,'ALLEN','SALESMAN',7698,'1981-02-20',1600,300,30);
INSERT INTO EMP VALUES
(7521,'WARD','SALESMAN',7698,'1981-02-22',1250,200,30);
INSERT INTO EMP VALUES
(7566,'JONES','MANAGER',7839,'1981-04-02',2975,30,20);
INSERT INTO EMP VALUES
(7654,'MARTIN','SALESMAN',7698,'1981-09-28',1250,300,30);
INSERT INTO EMP VALUES
(7698,'BLAKE','MANAGER',7839,'1981-04-01',2850,null,30);
INSERT INTO EMP VALUES
(7782,'CLARK','MANAGER',7839,'1981-06-01',2450,null,10);
INSERT INTO EMP VALUES
(7788,'SCOTT','ANALYST',7566,'1982-10-09',3000,null,20);
INSERT INTO EMP VALUES
(7839,'KING','PRESIDENT',null,'1981-11-17',5000,3500,10);
INSERT INTO EMP VALUES
(7844,'TURNER','SALESMAN',7698,'1981-09-08',1500,0,30);
INSERT INTO EMP VALUES
(7876,'ADAMS','CLERK',7788,'1983-01-12',1100,null,20);
INSERT INTO EMP VALUES
(7900,'JAMES','CLERK',7698,'1981-10-03',950,null,30);
INSERT INTO EMP VALUES
(7902,'FORD','ANALYST',7566,'1981-10-3',3000,null,20);
INSERT INTO EMP VALUES
(7934,'MILLER','CLERK',7782,'1982-01-23',1300,null,10);

COMMIT;

CREATE TABLE DEPT
(DEPTNO number,
DNAME VARCHAR2(14),
LOC VARCHAR2(13) );

INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');
INSERT INTO DEPT VALUES (30,'SALES','CHICAGO');
INSERT INTO DEPT VALUES (40,'OPERATIONS','BOSTON');

COMMIT;



CREATE TABLE SALGRADE
( GRADE number,
LOSAL number,
HISAL number );

INSERT INTO SALGRADE VALUES (1,700,1200);
INSERT INTO SALGRADE VALUES (2,1201,1400);
INSERT INTO SALGRADE VALUES (3,1401,2000);
INSERT INTO SALGRADE VALUES (4,2001,3000);
INSERT INTO SALGRADE VALUES (5,3001,9999);
COMMIT;

*/
-- Sqlplus 접속시
--C:\Users\kosa>    sqlplus HR/1004@localhost:1521/XEPDB1
--                  sqlplus KOSA/1004@localhost:1521/XEPDB1

--1. 날짜 세팅
select sysdate from dual;
alter session set nls_date_format='YYYY-MM-DD HH24:MI:SS';
--TOOL 에서 NLS 설정을 바꾸어서  접속하면 날짜 >> YYYY-MM-DD HH24:MI:SS
select sysdate from dual;
select hiredate from emp;

--2. 개발자 RDBS : CRUD
/*
DDL (데이터 정의어) : create , alter , drop (truncate)  개발자
DML (데이터 조작어) : insert (Create) , update , delete , select(Read)(70%) 개발자
DCL (데이터 제어어) : grant , revoke (DBA)
TCL (트랜잭션)     :  개발자
*/

/*
DB분야
개발자 (CRUD) + DDL + 기본관리(JDBC) > OLTP
관리자 DBA 백업하고 복원 ,네트워크 관리 , 자원관리(튜닝) : 로그, 차등 , 풀백업 > 복원
튜너 SQL문장 튜닝 > 속도 > 프로그램원리 , DBA , 프르젝트 수행 > index ..
모델러 : 분석 설계 (PM) 도메인 지식 (병원,증권....)
분석가 : 빅데이터 (OLAP) 데이터 전처리 가공 ... 통계 ....
*/

select * from emp;
select * from dept;
select * from salgrade;
--1. 사원테이블 있는 모든 데이터 출력
select * from emp;

--2. 특정 컬럼의 데이터 출력
select empno , ename , sal
from emp;

--약식
desc emp;--컬럼명 , null , 타입

--3. 별칭 (가명칭) alias
select empno 사번 , ename 이름
from emp;

--표준 AN-SI
select empno as "사   번" , ename as "이   름"
from emp;

--Oracle 데이터 (문자열) 엄격하게 대소문자 구분
select *
from emp
where ename = 'king';

select *
from emp
where ename = 'KING';

--실행순서
select *                --3
from emp                --1
where ename = 'KING';   --2


--Oracle Query (질의어)
--연산자
--JAVA : 산술 , 논리 , 관계
-- + JAVA : 결합 , 연산
-- +  Oracle 산술연산
-- || Oracle 결합연산

--MS-SQL + (산술,결합)


select '사원의 이름은 ' || ename || ' 입니다' as 사원정보
from emp;

/*
JAVA   : class  >> class Emp { privte int empno; private String ename}
ORACLE : Table  >> create table Emp ( empno number, ename varchar2(20))

varchar2(20) > 20 byte > 한글 10자 , 영문자, 특수문자 공백 20자
*/

select empno + 10000 from emp;
--select ename + 10000 from emp;
select empno + ename from emp;
--7788 + 'SMITH' 연산할 수 없다

select empno || ename from emp; --가능 (내부적으로 형변환) 결합 가능


--사장님 ... 우리 회사에 직종이 몇개나 있나 (신입)
select job from emp;


--중복된 행 제거 키워드 distinct
select distinct job from emp;

select distinct job , deptno
from emp
order by job;
--distinct job , deptno   > group by > 순서가 중요

select distinct deptno , job
from emp
order by deptno;

--Oracle 언언
--JAVA   : + , - , * , / , %
--Oracle : + , - , * , / ,  >> 연산자 중에서 함수로 만들자 mod() 나머지 함수
--  % 문자열 검색 패턴 : where ename like '%김%'


--사원테이블에서 사원의 급여를 100달러 인상한 결과를 출력
select empno , ename , sal , sal + 100 as "급여인상분" from emp;

--쿼리 : 테이블 구조 명확하게 분석(컬럼) ...

--Oracle : print 기능 없어요
--JAVA : System.out.println()
--임시 가상 테이블 : dual (임시, 가상테이블)

select 100+100 from dual;
select 100 || 100 from dual;
select '100' + 100 from dual; --**숫자형 문자 (숫자)**
select 'A100' + 100 from dual;

--비교 연산자
--JAVA ( == 같다 , =  할당)
--Oracle ( = 같다 , != 같지 않다)
--JavaScript (== , === )

--논리연산 (AND , OR , NOT)
select empno , ename , sal
from emp
where sal >= 2000;

--사번이 7788번이 사원의 사번 , 이름 , 직종 , 입사일을 출력하세요
select empno , ename, job , hiredate
from emp
where empno=7788;

--미만 <> 초과
--이상 <> 이하 (= 포함)

--급여가 2000달라 이상이면서 직종이 manager 인 사원의 모든 정보를 출력하세요
select *
from emp
where sal >= 2000 and job='MANAGER';


--급여가 2000달라 이상이거나(또는) 직종이 manager 인 사원의 모든 정보를 출력하세요
select *
from emp
where sal >= 2000 or job='MANAGER';

--급여가 2000달라 초과이면서 직종이 manager 인 사원의 모든 정보를 출력하세요
select *
from emp
where sal > 2000 or job='MANAGER';

--날짜 또는 언어 설정(환경정보)
select * from nls_session_parameters;
/*
NLS_LANGUAGE	KOREAN
NLS_DATE_FORMAT	YYYY-MM-DD HH24:MI:SS
NLS_DATE_LANGUAGE	KOREAN
NLS_TIME_FORMAT	HH24:MI:SSXFF

설정변경 가능
alter session set nls_date_format='YYYY-MM-DD HH24:MI:SS';
*/
--날짜 데이터 검색하기

select sysdate from dual;

select hiredate
from emp
where hiredate='1980-12-17';

select hiredate
from emp
where hiredate='1980/12/17';

select hiredate
from emp
where hiredate='1980.12.17';

select hiredate
from emp
where hiredate='80-12-17'; --RR-MM-DD > 지금 YYYY-MM-DD

--사원의 급여가 2000달라 이상이면서 4000이하인 모든 사원의 정보를 출력하세요
select *
from emp
where sal >= 2000 and sal <= 4000;

-- between A and B (무조건 = 을 포함)

select *
from emp
where sal between 2000 and 4000;

--사원의 급여가 2000달라 초과이면서 4000미만인 모든 사원의 정보를 출력하세요
select *
from emp
where sal > 2000 and sal < 4000;


--부서번호가 10번 또는 20번 또는 30번인 사원의 사번 , 이름 , 급여 , 부서번호를 출력하세요
select empno, ename , sal , deptno
from emp
where deptno = 10 or deptno=20 or deptno=30;

--IN 연산자
select *
from emp
where deptno in (10,20,30);
--deptno = 10 or deptno=20 or deptno=30;

--부서번호가 10번 또는 20번이 아닌 모든 사원 정보를 출력하세요
select *
from emp
--where deptno not in (10,20);  -- not in >> != and
where deptno != 10 and deptno != 20;

--Today Point
--null
--값이 없다

--설계자 : 필요악
-- create table emp (empno number not null , ename varchar2(2))

--문법)
--비교) where comm = null (x) ,  where comm is null or where comm is not null
--처리) null 과의 모든 연산의 결과 > null > nvl(), nvl2()   >> mysql( ifnull())


create table member(
                       userid varchar2(20) not null, --필수입력
                       name varchar2(20) not null, --필수입력
                       hobby varchar2(50)  --default null 허용
);

desc member;

insert into member(userid,name,hobby)
values('hong','길동','도둑');

select * from member;

--DML 작업 실반영 , 취소 (강제로 commit , rollback)
commit;

insert into member(userid,name)
values('hong2','길동2');

select * from member;
--select  * from member where hobby = null;
select  * from member where hobby is null;
select  * from member where hobby is not null;
commit;

/*
오라클은 DML 작업에 대해서 기본적으로
commit , rollback 강제합니다

insert ..
insert ..
insert ..
select * from emp

rollback or commit 실처리(실반영은 되지 않는다)

그런데 JDBC (JAVA) > auto commit >

MS-SQL--------------------------------------------
insert , update , delete 수행함과 동시에 commit (실반영)

begin tran 실행
   insert
   update...

   select * 실반영
commit tran
*/

--수당(comm) 받지 않는 모든 사원의 정보를 출력
select * from emp
where comm = null; --(x)

select * from emp
where comm is null;


--수당을 받는 사람
select *
from emp
where comm is not null;

--사윈테이블에서 사번 , 이름 , 급여 , 수당 , 총급여(급여 + 수당) 을 출력
select empno , ename , sal , comm , sal + comm as 총급여
from emp;

/*
null 이녀석
1. null 과의 모든 연산 결과는 null > 100+null > null
2. null 처리하는 함수 : nvl(), nvl2()
*/
select 1000 + null from dual;
select 1000 + nvl(null,0) from dual;


select empno , ename , sal , comm , sal + nvl(comm,0) as 총급여
from emp;

select nvl(null,'hello') from dual;

--사윈의 급여가 1000 이상이고 수당을 받지 않는 사원의 사번 , 이름 , 직종 , 급여 , 수당 , 총급여(급여+수당) 출력
select empno , ename , sal , comm , sal + nvl(comm,0) as 총급여
from emp
where sal >= 1000 and comm is null;

/*
null 비교
is null
is not null

null 모든 연산
nvl(null,0)
*/


--문자열 검색
--주소검색 >> 역삼 검색 > 역삼이라는 단어가 있는 모든 주소 출력
-- Like 문자열 패턴 검색
-- 와일드 카드 문자 ( % 모든것 , _ 한문자)
-- 와일드 카드 문자 부족 : 정규표현식 지원

select *
from emp
where ename like '%A%'; --A 포함되어 있으면


select *
from emp
where ename like 'A%'; --A 로 시작하는 단어


select *
from emp
where ename like '%S';

--ename index 걸었어요 :
--1. where ename like '%A%';
--2. where ename like 'A%';   검색속도 ...향상
--3. where ename like '%A';

select *
from emp
where ename like '%LL%';  --ALLEN , MILLER


select *
from emp
where ename like '%A%A%'; --A가 두개만 있으면   ADAMS


select *
from emp
where ename like '_A%';  -- _문자의 순서를 정의  WARD  MARTIN  JAMES


--정규표현식
--샘플 5개 (해석)
--regexp_like(ename,'[A-C]')

--데이터 정렬하기
--order by 컬럼명 :문자열 , 날짜 , 숫자 정렬가능
--오름차순 : asc 낮은순 : default
--내림차순 : desc 높은순
--DB 입장에서 가장 비용(cost) 정렬

select *
from emp
order by sal;   --default asc


select *
from emp
order by sal asc;

select *
from emp
order by sal desc;

--입사일이 가장 늦은 순으로 정렬해서 사번 , 이름 , 급여 , 입사일 출력하세요
--가장 최근에 입사한 순으로
select empno, ename, sal , hiredate
from emp
order by hiredate desc;

/*
select     3
from       1
where      2
order by   4  (select 결과를 정렬한다)
*/

select empno , ename , sal ,job , hiredate
from emp
where job='MANAGER'
order by hiredate desc;


select job , deptno
from emp
order by job asc , deptno desc;
--group 안에서 다시 정렬 ...

/*
distinct grouping
order by grouping
*/


/*
연산자
합집합(union)    : 테이블과 테이블의 데이터 합치는 것 (중복값 배제)
합집합(union all): 테이블과 테이블의 데이터 합치는 것 (중복값 허용)

create table uta(name varchar2(20));
insert into uta(name) values('AAA');
insert into uta(name) values('BBB');
insert into uta(name) values('CCC');
insert into uta(name) values('DDD');
commit;

create table ut(name varchar2(20));
insert into ut(name) values('AAA');
insert into ut(name) values('BBB');
insert into ut(name) values('CCC');
commit;
*/


select * from uta;
select * from ut;

select * from ut
union
select * from uta;   --중복 데이터 제거

select * from ut
union all
select * from uta;   --중복 데이터 허용

/*
Union
1. [대응]대는 [컬럼]의 [타입] [동일]
2. [대응]대는 [컬럼]의 [개수] [동일]  > 맞지 않는다면 null
*/

select empno , ename from emp
union
select dname , deptno from dept;

--ORA-01790: 대응하는 식과 같은 데이터 유형이어야 합니다


select empno , ename from emp
union
select deptno , dname from dept;



select empno , ename , job , sal from emp
union
select deptno , dname , loc , null from dept;

-- ORA-01789: 질의 블록은 부정확한 수의 결과 열을 가지고 있습니다.

--subquery : 가상테이블
select *
from (
        select empno , ename , job , sal from emp
        union
        select deptno , dname , loc , null from dept
     ) m
order by m.empno asc;

--------------------------------------------------------------------------------
--자바에서 제어문 END--
--초급 개발자 [단일 테이블에 대상으로 기본 쿼리]를 작성가능하다 ------------------------
--select   from   where   order by ---------------------------------------------

--이제 부터 함수 ..들어갑니다 ^^
SELECT
    ename,
    NVL(comm,0),
    TO_CHAR(hiredate,'YYYY-MM'),
    ROUND(sal,0),
    CASE WHEN sal > 3000 THEN 'HIGH' ELSE 'LOW' END
FROM emp;

/*
단일 행 함수의 종류
1) 문자형 함수 : 문자를 입력 받고 문자와 숫자 값 모두를 RETURN할 수 있다.
2) 숫자형 함수 : 숫자를 입력 받고 숫자를 RETURN한다.
3) 날짜형 함수 : 날짜형에 대해 수행하고 숫자를 RETURN 하는 MONTHS_BETWEEN 함수를
제외하고 모두 날짜 데이터형의 값을 RETURN한다.
4) 변환형 함수 : 어떤 데이터형의 값을 다른 데이터형으로 변환한다.
5) 일반적인 함수 : NVL, DECODE




*/
SELECT empno,ename,LOWER(job),deptno
FROM emp
WHERE LOWER(ename) = 'scott';

select ename , lower(ename) as "ename" from emp;

select length('abcd') from dual;

select concat('a','b') from dual;

select 'a'|| 'b' || 'c' from dual;

select concat(ename, job) from emp;

select substr('ABCDE',2,3) from dual; --BCD
select substr('ABCDE',1,1) from dual;  --A
--C만 출력
select substr('ABCDE',3,1) from dual;  --C

select substr('DSFJODFNOESDNFBOIFNOINFOINFAE',3) from dual;

/*
/*
사원테이블에서 ename 컬럼의 데이터에 대해서 첫글자는 소문자로 나머지 글자는 대문자로
출력하되 하나의 컬럼으로 만들어 출력하고 컬럼의 별칭 fullname  하고 첫글자와 나머지
문자사이에는 공백하나를 넣어서 출력

문서보고 하세요
a().b() (x)
a(b())  (0)

*/

select lower(substr(ename,1,1)) from emp;
select substr(ename,2) from emp;
select substr(ename,2,length(ename)) from emp;


select lower(substr(ename,1,1)) || ' ' || upper(substr(ename,2)) as fullname
from emp;

select lpad('ABC',10,'*') from dual;
select rpad('ABC',10,'*') from dual;

select rpad(substr('hong1004',1,2),length('hong1004'),'*') from dual;
select rpad(substr('k123',1,2),length('k123'),'*') from dual;


select rpad(substr(ename,1,2),length(ename),'*')
from emp;

select rtrim('MILLER','ER') from dual;

select '>' || 'MILLER      ' || '<' from dual;
select '>' || rtrim('MILLER      ',' ') || '<' from dual;

--치환함수
select ename , replace(ename,'A','와우') from emp;

--------------------------------------------------------------------------------
--문자열 함수 END----------------------------------------------------------------

--[숫자함수]
--round (반올림 함수)
--trunc (절삭함수)
--mod() 나머지 구하는 함수

-- -3  -2  -1  0  1  2  3
select round(12.345,0) from dual;  --정수부만 남겨라  12
select round(12.567,0) from dual;  --반올림 13
select round(12.345,1) from dual;  --12.3
select round(12.564,1) from dual;  --12.6
select round(12.345,-1) from dual; --10
select round(15.345,-1) from dual; --20

select round(15.345,-2) from dual;  --0

select trunc(12.345,0) from dual;  --정수부만 남겨라  12
select trunc(12.567,0) from dual;  --12
select trunc(12.345,1) from dual;  --12.3
select trunc(12.564,1) from dual;  --12.5
select trunc(12.345,-1) from dual; --10
select trunc(15.345,-1) from dual; --10

--나머지
select 12/10 from dual;

--나머지 함수
select mod(12,10) from dual;

select mod(0,0) from dual;

--------------------------------------------------------------------------------
--숫자 함수 END------------------------------------------------------------------

--날짜함수 : 연산가능
select sysdate from dual;

--POINT
--1. Date + Number >> Date
--2. Date - Number >> Date
--3. Date - Date   >> Number (일수)

select sysdate + 100 from dual;
select sysdate + 1000 from dual;
select sysdate - 1000 from dual;

select hiredate from emp;

select months_between('2025-01-01','2023-01-01') from dual; -- 24

select trunc(months_between(sysdate,'2023-01-01'),0) from dual;

--초급개발자 실수
select to_date('2025-01-01') + 100 from  dual;

/*
사원테이블에서 사원들의 입사일에서 현재 날짜까지의 근속월수를 구하세요
--사원이름 , 입사일 , 근속월수 출력
--단 근속월수는 정수만 출력(반올림 하지 마세요)
*/
select ename , hiredate , trunc(months_between(sysdate,hiredate),0) as 근속월수
from emp;
--------------------------------------------------------------------------------
--문자열 함수 , 숫자함수 , 날짜함수 (연산) 기본 END----------------------------------
--------------------------------------------------------------------------------

--변환함수
--오라클 숫자 , 문자열 , 날짜
--to_char() 숫자를 > 형식을 가진 문자 (1000000 ->$1,000,000 -> format 문자열
--to_char() 날짜를 > 형식을 가진 문자 ('2026-01-01' ->2025년01월01일 -> format 문자열


--to_date() : 날짜 형식의 문자열 -> 날짜
select to_date('2025-01-01') + 100 from dual;

--to_number() : 문자열 형식 숫자 -> 숫자 (자동형변환)
select to_number('100') + 100 from dual;

--format 정해진 양식
--표를 보고 값

select sysdate ,
    to_char(sysdate,'YYYY') || '년' as yyyy
     ,to_char(sysdate,'MM')
     ,to_char(sysdate,'DD')
     ,to_char(sysdate,'DAY')
from emp;

--입사일이 12월인 사원의 사번 , 이름 , 입사일 , 입사년도 , 입사월을 출력하세요
select empno , ename , hiredate , to_char(hiredate,'YYYY') , to_char(hiredate,'MM')
from emp
where to_char(hiredate,'MM') = '12';

--계정 이동
show user;
--USER이(가) "HR"입니다.

select * from employees;

select employee_id , first_name , last_name , hire_date , salary from employees;
/*
사원테이블(employees)에서 사원의 이름은 last_name , first_name 합쳐서 fullname 별칭
부여해서 출력하고 입사일은  YYYY-MM-DD 형식으로 출력하고 연봉(급여 *12)을 구하고
연봉의 10%(연봉 * 1.1)인상한 값을
출력하고 그 결과는 1000단위 콤마 처리해서 출력하세요
단 2005년 이후 입사자들만 출력하세요 그리고 연봉이 높은 순으로  출력하세요
*/
SELECT LAST_NAME || FIRST_NAME AS "FULLNAME",
       TO_CHAR(HIRE_DATE, 'YYYY-MM-DD') AS "입사일",
       salary,
       salary*12 as "연봉",
       TO_CHAR((SALARY * 12) * 1.1, '$999,999,999') AS SAL
FROM EMPLOYEES
WHERE TO_CHAR(HIRE_DATE, 'YYYY') >= '2005'
ORDER BY 연봉 DESC;

/*
EMPLOYEES 테이블을 이용하여 다음 조건에 만족하는 행을 검색하세요.
2005년이후에 입사한 사원 중에 부서번호가 있고, 급여가 5000~10000 사이인 사원을 검색합니다.
가) 테이블 : employees
나) 검색 : employee_id, last_name, hire_date, job_id, salary, department_id
다) 조건
    ① 2005년 1월 1일 이후 입사한 사원
    ② 부서번호가 NULL이 아닌 사원
    ③ 급여가 5,000보다 크거나 같고, 10,000 보다 작거나 같은 사원
    ④ 위의 조건을 모두 만족하는 행을 검색
라) 정렬: department_id 오름차순, salary 내림차순

*/
SELECT employee_id, last_name, hire_date, job_id, salary, department_id
FROM employees
WHERE hire_date > '2005/01/01'
  AND department_id IS NOT NULL
  AND salary BETWEEN 5000 AND 10000
ORDER BY department_id, salary DESC ;