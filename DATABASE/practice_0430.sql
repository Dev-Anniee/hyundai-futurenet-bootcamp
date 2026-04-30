--View 객체
/*
CREATE  [OR  REPLACE]  [FORCE | NOFORCE]  VIEW view_name [(alias[,alias,...])]
AS Subquery
[WITH  CHECK  OPTION  [CONSTRAINT  constraint ]]
[WITH  READ  ONLY]
*/

--설명 : 가상테이블 (쿼리 문장을 가지고 있는 객체 - 물리적인 테이블이 아니다)
--내가 원하는 데이터만 볼 수 있는 쿼리문을 가지고 있는 객체
--사용방법 : 기존 테이블과 동일한 방법

--1. 개발자의 편리성(성능과 무관) : 복잡한 쿼리 + subquery 데이터 > view 생성
--2. 보안 (입사:회계(급여 내역) > Emp > 관리자 > view 생성(사번, 이름, 기타정보) > view 사용

-- view 통해서 원본 테이블 : select 할 수 있다 (view가 볼 수 있는 데이터만)
-- view 통해서 DML : insert , update ,delete 가능하다 (하지 마세요) (view 가 볼수 있는 데이터 DML 가능)

--자기 부서의 평균월급 보다 더 많은 월급을 받는 사원의 사번 , 이름 , 부서번호 ,
--부서별 평균월급을 출력하세요
--만약에 부서와 부서별 평균월급 담고 있는 테이블 제공(in line view) 가상테이블
/*
select e.empno , e.ename , e.deptno , e.sal , s.avgsal
from emp e join (select deptno, avg(sal) as avgsal from emp group by deptno) s
on e.deptno = s.deptno
where e.sal > s.avgsal;
*/

--권한 : create view > DBA  > grant create view

create view view001
as
    select empno, ename, sal
    from emp;

select * from view001; -- view 가지고 있는 query 문이 실행
select * from view001 where empno=7788;
select job from view001; --ORA-00904: "JOB": 부적합한 식별자
--views는 볼수 없는 컬럼

select empno , ename ,sal from view001;

create view deptavg
as
select deptno, avg(sal) as avgsal --반드시 컬럼명
from emp
group by deptno;

select e.empno , e.ename , e.deptno , e.sal , s.avgsal
from emp e join deptavg s  -- view 영속적 존재 필요할 때 (가상 테이블 활용)
                on e.deptno = s.deptno
where e.sal > s.avgsal;

--with 절
--in line view
--view 객체 생성 (영속적인 객체 형태로 등록)
/*
CREATE OR REPLACE VIEW emp_10 (employee_no,employee_name,job_title)
AS
    SELECT empno as employee_no ,ename,job
    FROM emp
    WHERE deptno = 10;
*/

create or replace view view001  --없으면 생성 있으면 overwrite
as
select empno , ename , job, sal
from emp;


--편리성 (JOIN 반복적으로)
create or replace view view_002
as
select e.empno , e.ename , e.deptno , d.dname
from emp e join dept d
                on e.deptno = d.deptno;

select * from view_002;
select * from view_002 where deptno in(10,20);

--View 문법, 기존 쿼리문을 모르면 안된다
--직종별 평균급여를 볼 수 있는 view
-- view_003

create or replace view view_003
as
select job, avg(sal) as avgsal
from emp
group by job;


select * from view_003;

--View 를 통한 DML
select * from copyemp;

create or replace view v_emp
as
select empno , ename , sal
from copyemp;

--view를 통한 DML (view 볼 수 있는 원본 테이블 데이터 변경)
--view DML 가능하지만 최대한 조회의 목적으로 쓰자
update v_emp
set ename='AAA'
where empno=7788;

select * from v_emp;

update v_emp
set ename='BBBB'
where empno=7782;

commit;

-- 30번 부서 사원들의  직종, 이름, 월급을 담는 VIEW를 만드는데,
-- 각각의 컬럼명을 직종, 사원이름, 월급으로 ALIAS를 주고 월급이
-- 300보다 많은 사원들만 추출하도록 하라.  view100
create view view100
as
    select ename 사원이름, job 직종, sal 월급
    from emp
    where deptno=30 and sal>300;

select * from view100

--with절, 서브쿼리, 영속적으로 쓰고 싶다면 -> view
--VIEW END

--분석함수
--통계 데이터 쿼리 만들기
--집계 행 데이터 -> 열 데이터 바꾸기
--집계 열 데이터 -> 행 데이터 바꾸기

--decode, case
--pivot 행 데이터, 열 데이터 변환
--차트 그린다, 데이터 분석
/*
deptno cnt
10     3
20     5
30     6

차트 , 분석용 데이터
deptno_10 , deptno_20 , deptno_30
    3          5            6
로우 데이터 -> 열 데이터로 바꾸는 작업
*/

--일반 row 데이터
select deptno,count(*) as cnt
from emp
group by deptno
order by  deptno asc;

--case 활용
--1단계
select deptno , case when deptno=10 then 1 else 0 end dept_10,
       case when deptno=20 then 1 else 0 end dept_20,
       case when deptno=30 then 1 else 0 end dept_30
from emp
order by deptno asc;

--2단계
select deptno , sum(case when deptno=10 then 1 else 0 end) as dept_10,
       sum(case when deptno=20 then 1 else 0 end) as dept_20,
       sum(case when deptno=30 then 1 else 0 end) as dept_30
from emp
group by deptno
order by deptno asc;

--3단계
--deptno 컬럼은 의미 없다
select sum(case when deptno=10 then 1 else 0 end) as dept_10,
       sum(case when deptno=20 then 1 else 0 end) as dept_20,
       sum(case when deptno=30 then 1 else 0 end) as dept_30
from emp
order by deptno asc;

--차트 , 분석용 데이터
/*
deptno_10 , deptno_20 , deptno_30
    3          5            6
*/

--
select  case when deptno=10 then cnt else null end dept_10,
        case when deptno=20 then cnt else null end dept_20,
        case when deptno=30 then cnt else null end dept_30
from  (
          --기준 데이터
          select deptno , count(*) as cnt
          from emp
          group by deptno
      ) x;


select  max(case when deptno=10 then cnt else null end) as dept_10,
        max(case when deptno=20 then cnt else null end) as dept_20,
        max(case when deptno=30 then cnt else null end) as dept_30

from  (
          --기준 데이터
          select deptno , count(*) as cnt
          from emp
          group by deptno
      ) x;
--

--pivot은 오라클 버전 11 이하에서는 사용할 수 없다 -> decode 사용 해야 한다

/*
집계함수(값열)	SUM, AVG, COUNT, MAX, MIN 등 사용 가능
FOR 피벗할 열과행에서 열로 변환할 기준 열
IN (열값1, 열값2...)	행 값을 열로 바꿀 값 목록 (고정되어 있어야 함)
*/

--오라클 11g PIVOT
/*
11g 이전 decode , case 통계데이터

select *
from (피벗 대상 쿼리문)
pivot(그룹함수(집계함수) for 피벗컬럼 IN(피벗컬럼값 AS 별칭)
*/

--직종별 입사 건수 (월별)
select * from emp;
/*
직종  1월 , 2월 , 3월 , 4월
IT    0     0    2     0
MA    1     1    1     0
SA    0     0    0     0
*/

--1단계
select job , to_char(hiredate,'FMMM') || '월' as hire_month
from emp;

--2단계
--기준데이터
select *
from (
    select job , to_char(hiredate,'FMMM') || '월' as hire_month
    from emp
)
    pivot (
    count(*) for hire_month IN('1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월')
    );

--------------------------------------------------------------------------------
--decode
select job , to_char(hiredate,'FMMM') || '월' as hire_month , hiredate
from emp;

--1단계
select job
     ,decode(to_char(hiredate,'FMMM'),'1',1,0) "1월"
     ,decode(to_char(hiredate,'FMMM'),'2',1,0) "2월"
     ,decode(to_char(hiredate,'FMMM'),'3',1,0) "3월"
     ,decode(to_char(hiredate,'FMMM'),'4',1,0) "4월"
     ,decode(to_char(hiredate,'FMMM'),'5',1,0) "5월"
     ,decode(to_char(hiredate,'FMMM'),'6',1,0) "6월"
     ,decode(to_char(hiredate,'FMMM'),'7',1,0) "7월"
     ,decode(to_char(hiredate,'FMMM'),'8',1,0) "8월"
     ,decode(to_char(hiredate,'FMMM'),'9',1,0) "9월"
     ,decode(to_char(hiredate,'FMMM'),'10',1,0) "10월"
     ,decode(to_char(hiredate,'FMMM'),'11',1,0) "11월"
     ,decode(to_char(hiredate,'FMMM'),'12',1,0) "12월"
from emp;


--2단계
select job
     ,sum(decode(to_char(hiredate,'FMMM'),'1',1,0)) "1월"
     ,sum(decode(to_char(hiredate,'FMMM'),'2',1,0)) "2월"
     ,sum(decode(to_char(hiredate,'FMMM'),'3',1,0)) "3월"
     ,sum(decode(to_char(hiredate,'FMMM'),'4',1,0)) "4월"
     ,sum(decode(to_char(hiredate,'FMMM'),'5',1,0)) "5월"
     ,sum(decode(to_char(hiredate,'FMMM'),'6',1,0)) "6월"
     ,sum(decode(to_char(hiredate,'FMMM'),'7',1,0)) "7월"
     ,sum(decode(to_char(hiredate,'FMMM'),'8',1,0)) "8월"
     ,sum(decode(to_char(hiredate,'FMMM'),'9',1,0)) "9월"
     ,sum(decode(to_char(hiredate,'FMMM'),'10',1,0)) "10월"
     ,sum(decode(to_char(hiredate,'FMMM'),'11',1,0)) "11월"
     ,sum(decode(to_char(hiredate,'FMMM'),'12',1,0)) "12월"
from emp
group by job;

/*
문법

SELECT *
FROM (
    SELECT 열1, 열2, 값열
    FROM 테이블명
)
PIVOT (
    집계함수(값열)
    FOR 피벗할열 IN (열값1 AS 별칭1, 열값2 AS 별칭2, ...)
);


집계함수(값열)	SUM, AVG, COUNT, MAX, MIN 등 사용 가능
FOR 피벗할열	행에서 열로 변환할 기준 열
IN (열값1, 열값2...)	행 값을 열로 바꿀 값 목록 (고정되어 있어야 함)
*/

CREATE TABLE sales (
                       dept   VARCHAR2(10),
                       year   NUMBER(4),
                       amount NUMBER
);

INSERT INTO sales (dept, year, amount) VALUES ('A', 2022, 100);
INSERT INTO sales (dept, year, amount) VALUES ('A', 2023, 150);
INSERT INTO sales (dept, year, amount) VALUES ('B', 2022, 200);
INSERT INTO sales (dept, year, amount) VALUES ('B', 2023, 250);
COMMIT;

select * from sales;
--년도별 amount sum 구하세요  (PIVOT)

--기준데이터
select dept , year , amount
from sales;

select *
from (
    select dept , year , amount
    from sales
)
    pivot(
    sum(amount) for year IN(2022 as "year2022", 2023 as "year2023")
    );

------------------
--직종별 , 부서별 급여 합계

select job , deptno , sum(sal)
from emp
group by job , deptno
order by job;


select *
from (
    select job , deptno ,sal from emp
)
    pivot (
    sum(sal) for deptno in('10' as d10 , '20' as d20 , '30' as d30)
    );

--문제
select *
from (
    select deptno , job , sal from emp
)
    pivot(
    sum(sal) for job
    in('PRESIDENT' as PRESIDENT ,
        'ANALYST' as  ANALYST,
        'MANAGER' as MANAGER ,
        'SALESMAN' as  SALESMAN ,
        'CLERK' as CLERK)
    );


--동일한 결과 decode 생성하세요
SELECT DEPTNO, SUM(DECODE(JOB,'PRESIDENT',SAL)) AS E_PRESIDENT
     , SUM(DECODE(JOB,'ANALYST',SAL)) AS E_ANALYST
     , SUM(DECODE(JOB,'MANAGER',SAL)) AS E_MANAGER
     , SUM(DECODE(JOB,'SALESMAN',SAL)) AS E_SALESMAN
     , SUM(DECODE(JOB,'CLERK',SAL)) AS E_CLERK
FROM EMP
GROUP BY DEPTNO
ORDER BY DEPTNO DESC;

--동일한 결과 case 생성하세요
SELECT DEPTNO, SUM(CASE WHEN JOB='PRESIDENT' THEN SAL END) AS E_PRESIDENT
     , SUM(CASE WHEN JOB='ANALYST' THEN SAL END) AS E_ANALYST
     , SUM(CASE WHEN JOB='MANAGER' THEN SAL END) AS E_MANAGER
     , SUM(CASE WHEN JOB='SALESMAN' THEN SAL END) AS E_SALESMAN
     , SUM(CASE WHEN JOB='CLERK' THEN SAL END) AS E_CLERK
FROM EMP
GROUP BY DEPTNO
ORDER BY DEPTNO DESC;
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--Rollup , cube , Rank 관련 함수
/*
Rollup , cube (OLAP) 환경
다차원 분석 쿼리 (레포팅 용도)
*/

select job , sum(sal)
from emp
group by rollup(job);
--직종별 급여의 합 또 모든 직종 급여의 합

select job, deptno , sum(sal) ,count(sal)
from emp
group by job , deptno
order by job ,deptno;

select job , deptno ,sum(sal)
from emp
group by rollup(job,deptno);
/*
CLERK	    20	1900
SALESMAN	30	5600
MANAGER	    20	2975
MANAGER	    30	2850
MANAGER	    10	2450
ANALYST	    20	6000
PRESIDENT	10	5000
CLERK	    30	950
CLERK	    10	1300
CLERK		    4150
SALESMAN	    5600
MANAGER		    8275
ANALYST		    6000
PRESIDENT		5000
		       29025


*/
select job , deptno ,sum(sal)
from emp
group by rollup(deptno,job);

------------------
select deptno , job , sum(sal)
from emp
group by deptno, job
union all
select deptno , null , sum(sal)
from emp
group by deptno
union all
select null , job , sum(sal)
from emp
group by job;
--------------------------------------------------------------------------------
select deptno , job , sum(sal)
from emp
group by cube(deptno,job)
order by deptno, job;
--------------------------------------------------------------------------------
--순위함수
--1. rownum Top n
--2. RANK
--3. DENSE_RANK
--동율처리

select ename ,sal,
       rank() over(order by sal desc) as 순위,
       dense_rank() over(order by sal desc) as 순위2
from emp
order by sal desc;

/*
rank() over(order by sal desc) as 순위
KING	5000	1
FORD	3000	2
SCOTT	3000	2
JONES	2975	4 ---
*/

/*
dense_rank() over(order by sal desc) as 순위2
KING	5000		1
FORD	3000		2
SCOTT	3000		2
JONES	2975		3  ---
*/
/*
현업 순위 반드시 반드시 동률
기준을 주세요
10명 POINT : 100,98,97,97,97,97,97,97,97,97 >> ,97 ,97 ,
현업 추가적인 기준을
현업 : 가입일 우선 , 성별 여자인 ...
*/

select ename , sal , comm
     , rank() over(order by sal desc ,comm desc) as 순위
from emp
order by sal desc;

--기본 수업 END

/*
오라클에서 분석함수를 사용할 때 PARTITION BY를 사용하여 그룹으로 묶어서 연산을 할 수 있다.
GROUP BY 절을 사용하지 않고, 조회된 각 행에 그룹으로 집계된 값을 표시할 때 OVER 절과 함께
PARTITION BY 절을 사용하면 된다.

분석함수([칼럼]) OVER(PARTITION BY 칼럼1, 칼럼2... [ORDER BY 절] [WINDOWING 절])

분석함수를 사용할 때는 OVER 절을 함께 사용해야 하며, OVER 절 내부에 PATITION BY 절을
사용하지 않으면 쿼리 결과 전체를 집계하며 PARTITION BY 절을 사용하면 쿼리 결과에서
 해당 칼럼을 그룹으로 묶어서 결과를 표시한다.
*/

--SUM 함수

SELECT empno , ename , job , sal , SUM(sal) OVER(PARTITION BY job)
FROM emp WHERE job IN ('MANAGER', 'SALESMAN') ORDER BY job;

--조회된 결과의 직군(job) 별로 합산된 급여(sal) 값을 각행에 표시한다.
--MAX 함수

SELECT empno , ename , job , sal , MAX(sal) OVER(PARTITION BY job)
FROM emp WHERE job IN ('MANAGER', 'SALESMAN') ORDER BY job;

--조회된 결과의 직군(job) 별 급여(sal) 최댓값을 각행에 표시한다.


--ROW_NUMBER 함수

SELECT empno , ename , job , sal ,
       ROW_NUMBER() OVER(PARTITION BY job ORDER BY sal) AS rn
FROM emp WHERE job IN ('MANAGER', 'SALESMAN') ORDER BY job;

--조회된 결과에서 직군(job) 별 급여(sal)가 낮은 순으로 순번을 표시한다.
--급여가 동일한 경우 또 다른 기준을 부여하고 싶을 때에는 ORDER BY 절에 추가로 칼럼을 추가한다.
--(예, ORDER BY sal, empno)
