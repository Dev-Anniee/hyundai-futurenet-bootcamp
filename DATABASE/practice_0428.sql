--select from where order by
--null
--문자열 함수 , 숫자 함수 , 날짜함수  , 변환함수(이쁘게) to_char() , to_date()
--------------------------------------------------------------------------------
show user;
--USER이(가) "KOSA"입니다.

select 'A' as a , 10 as b , null as c , empno
from emp;
--------------------------------------------------------------------------------
--일반함수
/*
SQL (변수 , 제어문 개념이 없어요)
PL-SQL (변수 , 제어문 > 프로시져 , 함수 , 커서 , 트리거)

nvl() 일반함수   null 처리

특정 함수를 사용하면 제어문 처럼

1. decode : 자바 if문 >  통계 데이터 분석 > pivot, cube, rollup 다차원 분석 함수 : 읽어내고 분석할 수 있어야 한다
2. case : 자바 switch문
*/

select comm , nvl(comm,0) from emp;

create table temp01
(
    id number(6), --정수 6자리
    job nvarchar2(20)  --한글자 2byte  한글영문 상관없이 20자 (20*2) 40byte
);

desc temp01;

/*
insert into temp01(id,job) values(100,'IT');
insert into temp01(id,job) values(200,'SALES');
insert into temp01(id,job) values(300,'MANAGER');
insert into temp01(id) values(400);
insert into temp01(id,job) values(500,'MANAGER');
commit;
*/

select * from temp01;
select id , decode(id, 100,'아이티',
                   200,'영업',
                   300,'관리',
                   '기타') as 부서이름
from temp01;
--decode 보기 좋게 줄을 맞춰서 적는다

select empno , ename , deptno , decode(deptno, 10 ,'인사팀',
                                       20 ,'관리팀',
                                       30 ,'회계팀',
                                       40 ,'일반부서',
                                       'ETC') as 부서이름
from emp; -- 값이 채워진 컬럼을 만드는 것

--1234567
--decode(substr('1234567',1,1), 1 ,'남성')... 활용

/*
부서번호가 20번인 사원중에서 SMITH 라는 이름을 가진 사원이라면 HELLO 문자 출력하고
부서번호가 20번인 사원중에서 SMITH 라는 이름을 가진 사원이 아니라면 WORLD 문자 출력하고
부서번호가 20번인 사원이 아니라면 ETC 라는 문자를 출력하세요
논리: if 안에 if 안에  > decode

EMP
*/
select ename , deptno ,
       decode(deptno,20,decode(ename,'SMITH','HELLO',
                               'WORLD'),
              'ETC') as "컬럼"
from emp;

--case (PL-SQL) 함수가 아닌데 .. 유일하게 SQL 사용가능
/*
 CASE 조건식 WHEN 결과1 THEN 출력1
            WHEN 결과2 THEN 출력2
            WHEN 결과3 THEN 출력3
            WHEN 결과4 THEN 출력4
            ELSE 출력5
  END "컬럼명"
*/

create table t_zip
(
    zipcode number(10)
);

/*
insert into t_zip(zipcode) values(2);
insert into t_zip(zipcode) values(31);
insert into t_zip(zipcode) values(32);
insert into t_zip(zipcode) values(41);
commit;
*/

select '0'|| to_char(zipcode) ,  case zipcode when 2 then '서울'
                                              when 31 then '경기'
                                              when 41 then '제주'
                                              else '기타'
                                              end 지역이름
from t_zip;

/*
1. case 컬럼명 when 결과 then 출력
2. case when 조건 비교식 then 출력
*/

select case when sal <= 1000 then '4급'
            when sal between 1001 and 2000 then '3급'
            when sal between 2001 and 3000 then '2급'
            when sal between 3001 and 4000 then '1급'
            else '특급'
           end 급수 , empno, ename , sal
from emp;

/*
decode 함수 (조건)
case 제어구문 (PL-SQL) > SQL 가지고 와서 사용
*/
--------------------------------------------------------------------------------
--문자열 함수 , 숫자 함수 , 날짜 함수 , 변환함수 , 일반함수(nvl , decode , case식)
--------------------------------------------------------------------------------

--집계함수
/*
비교 : Stream API 선택 기준 (데이터처리  잘 하는 놈 : DB - 사용 권장)
1. count() , count(*)
2. sum()
3. avg()
4. max()
5. min()
기타 등등

집계함수
1. 집계함수는 group by 절과 같이 사용
2. 모든 집계함수는 null 값 무시
3. select 절에 집계함수 이외에 다른 컬럼이 오면 반드시 그 컬럼은 group by 절에 명시 - 중요
*/

select count(*) from emp; --row 수 14건

select count(empno) from emp; --데이터 건수 14건

select count(comm) from emp; --집계 함수는 null을 보지 못한다! 6

select comm from emp;

select count(nvl(comm,0)) from emp; --데이터 건수 14건

--급여의 합
select sum(sal) from emp;

--회사내 comm 총 수당?
select sum(comm) from emp; --4330 달러

--select (300+200+30+300+3500+0)

--우리 회사 수당의 평균 (null 값 처리 X)
select avg(comm) from emp; --721

--회사의 기준
--노사측 : select (300+200+30+300+3500+0) / 14 from dual;  --309
--사측   : select (300+200+30+300+3500+0) / 6 from dual;   --721

--노사측 평균 (null 값 처리)
select avg(nvl(comm,0)) from emp;

select max(sal) from emp;
select min(sal) from emp;

select sum(sal) , avg(sal) , max(sal) , min(sal) , count(*) , count(sal)
from emp;

select empno , count(empno)
from emp
group by empno;
--https://docs.oracle.com/error-help/db/ora-00937/00937. 00000 -  "not a single-group group function"

--부서별 평균을 구하세요
select deptno , avg(sal)
from emp
group by deptno;

--직종별 평균 급여와 급여합을 구하세요
select job, avg(sal) , sum(sal)
from emp
group by job;
--group by 쓸 거면 select절에도 해당 요소가 함께 들어있는 게 당연

/*
group으로 처리
1.distinct 컬럼명1, 컬럼명2
2.order by 컬럼명1, 컬렴명2
3.group by 컬럼명1, 컬렴명2

*/

--부서별 직종별 급여의 합을 구하세요
select deptno , job , sum(sal) , count(sal)
from emp
group by deptno,job
order by deptno;

/*
select     4
from       1
where      2
group by   3
order by   5
*/

--직종별 평균급여가 3000달러 이상인 사원의 직종과 평균급여를 출력하세요
-- select job , avg(sal)
-- from emp
-- where 3000 <= avg(sal)
-- group by job;  --(x)

--group by 된 결과를 가지고 조건을 걸고 싶어요
--from ~ where
--group by ~ having

select job , avg(sal)
from emp
group by job
having avg(sal) >= 3000;

/*
단일 테이블 기준

select     5
from       1
where      2
group by   3
having     4
order by   6
*/
/*
사원테이블에서 직종별 급여합을 출력하되 수당은 지급 받고 급여의 합이 5000 이상인
사원들의 목록을 출력하세요  (comm 0인 놈도 받는 것으로 ....)
급여의 합이 낮은 순으로 출력하세요
*/
select job , sum(sal) as sumsal
from emp
where comm is not null
group by job
having sum(sal) >= 5000
order by sumsal asc;  --select 컬럼 사용가능
/*
사원테이블에서 부서 인원이 4명보다 많은 부서의 부서번호 ,인원수 ,
급여의 합을 출력하세요
*/
select deptno , count(*) as 인원 , sum(sal) as 합
from emp
group by deptno
having count(*) > 4;
--"행의 전체 개수"를 셀 때는 COUNT(*)가 훨씬 안전하고 권장

/*
사원테이블에서 직종별 급여의 합이 5000를 초과하는 직종과 급여의 합을 출력하세요
단 판매직종(salesman) 은 제외하고 급여합으로 내림차순 정렬하세요
*/
select job , sum(sal) as sumsal
from emp
where job != 'SALESMAN'
group by job
having sum(sal) > 5000
order by sumsal desc;
--------------------------------------------------------------------------------
--이동 HR
show user;
--USER이(가) "HR"입니다.
/*
EMPLOYEES 테이블을 이용하여 다음 조건에 만족하는 행을 검색하세요.
부서번호가 있고, 부서별 근무 인원수가 2명 이상인 행을 검색하여
부서별 최대 급여와 최소 급여를 계산하고 그 차이를 검색합니다.
가) 테이블 : employees
나) 검색 : department_id, MAX(salary), MIN(salary), difference
        - MAX(salary) 와 MIN(salary)의 차이를 DIFFERENCE로 검색
다) 조건
    ① 부서번호가 NULL이 아닌 사원
    ② 부서별 근무 인원수가 2명 이상인 집합
라) 그룹 : 부서번호가 같은 행
마) 정렬 : department_id
*/
SELECT department_id, MAX(salary),
       MIN(salary) ,MAX(salary) - MIN(salary) AS difference
FROM employees
WHERE department_id IS NOT NULL
GROUP BY department_id
HAVING COUNT(*) >= 2
ORDER BY department_id ;
--------------------------------------------------------------------------------
--이동 KOSA
show user;
--USER이(가) "KOSA"입니다.

/*
1. Cartesian Product (카테시안 곱) 모든 가능한 행들의 Join (조건이 없는 조인) 행*행
2. Equijoin Join 조건이 정확히 일치하는 경우 사용(일반적으로PK와 FK사용) >> 등가조인 (1:1 컬럼)
3. Non-Equijoin Join 조건이 정확히 일치하지 않는 경우에 사용(등급,학점) >> 비등가조인
4. Outer Join Join 조건이 정확히 일치하지 않는 경우에도 모든 행들을 출력 >> 조인 만족하지 않는 데이터(left , right , full)
5. Self Join 하나의 테이블에서 행들을 Join하고자 할 경우에 사용 >> 자기참조
*/

/*
create table M (M1 char(6) , M2 char(10));
create table S (S1 char(6) , S2 char(10));
create table X (X1 char(6) , X2 char(10));

insert into M values('A','1');
insert into M values('B','1');
insert into M values('C','3');
insert into M values(null,'3');
commit;

insert into S values('A','X');
insert into S values('B','Y');
insert into S values(null,'Z');
commit;

insert into X values('A','DATA');
commit;
*/

select * from m;
select * from s;
select * from x;

/*
1. 등가조인 Equijoin
1.1 원테이블과 대응대는 테이블 있는 컬럼의 데이터를 1:1 매핑
1.2 오라클 문법
1.3 안시표준(ANSI)(0) : join  on절
*/

select m.m1 , m.m2 , s.s1 , s.s2
from m , s
where m.m1 = s.s1;

--ANSI
select m.m1 , m.m2 , s.s1 , s.s2
from m join s  --등가조인
on m.m1 = s.s1;

select * from emp;
select * from dept;

--사원번호 , 사원이름 , 부서번호 , 부서이름을 출력하세요
select e.empno , e.ename , e.deptno , d.dname
from emp e join dept d   --ANSI
on e.deptno = d.deptno;

--------------------------------------------------------------------------------
--테이블 3개 ... 4개 ....
select m.m1 , m.m2 , s.s2 , x.x2
from m , s, x
where m.m1 = s.s1 and s.s1 = x.x1;

--ANSI
select *
from m join s on m.m1 = s.s1
       join x on s.s1 = x.x1
       --join y on x.x1 = y.y2;

select * from emp;
select * from dept;

--HR 이동
select * from employees;
select * from departments;
select * from locations;

select count(*) from employees;  --107명 사원

--사번 , 이름(last_name) , 부서번호  ,부서이름
select e.employee_id, e.last_name , e.department_id , d.department_name
from employees e join departments d
on e.department_id = d.department_id;

--이상하네 why : 106
select * from employees where department_id is null;
---Grant	KGRANT	44.1632.960033	2017-05-24 00:00:00	SA_REP	7000	0.15	149

--사번 , 이름 , 부서번호 , 부서이름 , 지역코드 , 도시명  (FK 명확하게 파악한다)
--3개
select e.employee_id, e.last_name , e.department_id , d.department_name , d.location_id , l.city
from employees e join departments d  on e.department_id = d.department_id
                 join locations l    on d.location_id = l.location_id;
--where ....
--------------------------------------------------------------------------------
--계정이동
select user from dual;
show user; --표준 :  select user from dual;
--------------------------------------------------------------------------------
--2. Non-Equijoin (비등가) : 1:1 매핑(비교) 컬럼이 없다
-- 비등가조인 문법이 없다 >> 등가조인 문법
-- 개념적

select * from emp;
select * from salgrade;

select e.empno , e.ename , e.sal , s.grade
from emp e join salgrade s
on e.sal between s.losal and s.hisal;

--------------------------------------------------------------------------------
--3 Outer Join (equi 조인을 먼저 선행하고  > 남아있는 (조인 만족하지 않는) 데이터를 가져오는 방버
--주종관계 (주인이되는 쪽에 남아있는 데이터를 가져오는 방법)
--1. left outer join
--2. right outer join
--3. full outer join ( left , right > union 결과)

select *
from m left outer join s
on m.m1 = s.s1;

select *
from m right outer join s
on m.m1 = s.s1;

--HR 이동
--사번 , 이름(last_name) , 부서번호  ,부서이름
select e.employee_id, e.last_name , e.department_id , d.department_name
from employees e left outer join departments d
on e.department_id = d.department_id;
--178, Grant, 총 107 데이터

--KOSA 이동
select *
from m full outer join s
on m.m1 = s.s1;

--------------------------------------------------------------------------------
--4. Self Join
--자기참조 (테이블이 하나만 있어요)
--등가조인
--테이블이 하나 더 있는 것처럼

select * from emp;

select e.empno, e.ename , m.empno , m.ename
from emp e
join emp m
on e.mgr = m.empno;

select count(*) from emp where mgr is null;

select e.empno, e.ename , m.empno , m.ename
from emp e
left outer join emp m
on e.mgr = m.empno;

--------------------------------------------------------------------------------
--Cartesian Product
select *
from emp
cross join dept;
--14 * 4 > 56 건
--------------------------------------------------------------------------------
--기타 조인
--NATURAL JOIN
--등가조인
--두 테이블 [동일한 이름]을 가지는 컬럼은 [모두 조인]

select *
from emp
natural join dept;   --둘다 deptno 컬럼


--join using 구문에 사용한 컬럼만 조인
--NATURAL JOIN 컬럼의 이름이 동일 조인 OK
select *
from emp
join dept
using(deptno);

--------------------------------------------------------------------------------

--오라클 JOIN TEST
-- 1. 사원들의 이름, 부서번호, 부서이름을 출력하라.
SELECT E.ENAME, E.DEPTNO, D.DNAME
FROM EMP E  join DEPT D on E.DEPTNO=D.DEPTNO;

-- 2. DALLAS에서 근무하는 사원의 이름, 직종, 부서번호, 부서이름을 출력하라.
SELECT E.ENAME, E.JOB, D.DEPTNO, D.DNAME
FROM EMP E  join DEPT D on E.DEPTNO=D.DEPTNO
WHERE  D.LOC='DALLAS';

-- 3. 이름에 'A'가 들어가는 사원들의 이름과 부서이름을 출력하라.
SELECT E.ENAME, D.DNAME
FROM EMP E  join DEPT D  on D.DEPTNO=E.DEPTNO
WHERE  E.ENAME LIKE '%A%';

-- 4. 사원이름과 그 사원이 속한 부서의 부서명, 그리고 월급을 출력하는데 월급이 3000이상인 사원을 출력하라.
SELECT E.ENAME, D.DNAME, E.SAL
FROM EMP E  join DEPT D on E.DEPTNO=D.DEPTNO
WHERE E.SAL>=3000;

-- 5. 직위(직종)가 'SALESMAN'인 사원들의 직종과 그 사원이름, 그리고 그 사원이 속한 부서 이름을 출력하라.
SELECT E.JOB, E.ENAME, D.DNAME
FROM EMP E  join DEPT D on E.DEPTNO=D.DEPTNO
WHERE E.JOB='SALESMAN';

-- 6. 커미션이 책정된 사원들의 사원번호, 이름, 연봉, 연봉+커미션,
-- 급여등급을 출력하되, 각각의 컬럼명을 '사원번호', '사원이름',
-- '연봉','실급여', '급여등급'으로 하여 출력하라.
--(비등가 ) 1 : 1 매핑 대는 컬럼이 없다
SELECT         E.EMPNO AS "사원번호",
               E.ENAME AS "사원이름",
               E.SAL*12 AS "연봉",
               --E.SAL*12+NVL(COMM,0) AS "실급여",
               E.SAL*12+COMM AS "실급여",
               S.GRADE AS "급여등급"
FROM EMP E  join SALGRADE S on E.SAL BETWEEN S.LOSAL AND S.HISAL
WHERE E.Comm is not null;

-- 7. 부서번호가 10번인 사원들의 부서번호, 부서이름, 사원이름, 월급, 급여등급을 출력하라.
-- inner 는 생략 가능
SELECT E.DEPTNO, D.DNAME, E.ENAME, E.SAL, S.GRADE
FROM EMP E  join DEPT D on E.DEPTNO=D.DEPTNO
            join SALGRADE S on E.SAL BETWEEN S.LOSAL AND S.HISAL
WHERE E.DEPTNO=10;
SELECT * FROM SALGRADE;


-- 8. 부서번호가 10번, 20번인 사원들의 부서번호, 부서이름,
-- 사원이름, 월급, 급여등급을 출력하라. 그리고 그 출력된
-- 결과물을 부서번호가 낮은 순으로, 월급이 높은 순으로
-- 정렬하라.
-- inner 는 생략 가능
SELECT E.DEPTNO, D.DNAME, E.ENAME, E.SAL, S.GRADE
FROM EMP E  join DEPT D        on E.DEPTNO=D.DEPTNO
            join SALGRADE S    on E.SAL BETWEEN S.LOSAL AND S.HISAL
WHERE  E.DEPTNO<=20 --WHERE E.DEPTNO IN (10,20)  -- e.deptno = 10 or
ORDER BY E.DEPTNO ASC,  E.SAL DESC;


-- 9. 사원번호와 사원이름, 그리고 그 사원을 관리하는 관리자의
-- 사원번호와 사원이름을 출력하되 각각의 컬럼명을 '사원번호',
-- '사원이름', '관리자번호', '관리자이름'으로 하여 출력하라.
--SELF JOIN (자기 자신테이블의 컬럼을 참조 하는 경우)

SELECT E.EMPNO, E.ENAME , M.EMPNO, M.ENAME
FROM EMP E  left outer join EMP M
                            on e.MGR = m.EMPNO;

--------------------------------------------------------------------------------
--Subquery ( SQL 꽃 ... 만능 해결사)
-- 1. 단일(함수)  > 다중(union , JOIN) > JOIN 안되는 경우 (조인할 테이블 존재하지 않는 경우)  > 가상 제공

--사원테이블에서 사원들의 평균 월급보다 더 많은 월급을 받는 사원의 사번 , 이름 , 급여 출력

--1. 평균월급
select avg(sal) from emp; --2073

--2. 평균 2073
select *
from emp
where sal > 2073;

--1번 , 2번 쿼리를 통합
select *
from emp
where sal > (select avg(sal) from emp);

--subquery 선행 실행 > 그 결과를 가지고 main 쿼리 실행
/*
스칼라 서브쿼리 :  select 절 : 단일컬럼 , 단일행을 반환
인라인뷰       :  from 절 (임시 테이블 , 가상 테이블)
중첩 서브쿼리   : where 절 : 다중 컬럼 또는 다중 행 반환
*/

/*
1. single row subquery : 실행 결과가 단일컬럼에 단일로우 (한개 값)
ex)select sum(sal) , avg(sal) , max(sal)
연사자) = !=  <  >

2. multi row subquery : 실행 결과가 단일컬럼 로우가 여러개
ex) select deptno from dept 4건
연산자) IN , NOT IN , ANY , ALL

ALL(모든 것을 만족) : sal> 1000 and sal > 4000
ANY(어떤 것이 와도 상관 없어 : sal > 1000 or sal > 4000
사용되는 연산자의 차이로 single - multi 를 구분


문법)
1. 괄호안에 있어야 한다(쿼리문)
2. 단일 컬럼( select max(sal)
3. 서브쿼리 단독 실행 가능

실행순서 : 서브쿼리가 실행되고 그 결과를 가지고 메인 쿼리 실행
*/
-- 사원테이블에서 jones 의 급여보다 더 많은 급여를 받는 사원의 사번 , 이름, 급여 출력
select sal from emp where ename='JONES'; --2975

select *
from emp
where sal > (select sal from emp where ename='JONES');

--부서번호가 30번이 사원과 같은 급여를 받는 사원의 모든 정보출력하세요
select sal from emp where deptno=30;
/*
컬럼 1개
1600
1250
1250
2850
1500
950
*/

select *
from emp
where sal in (select sal from emp where deptno=30);
-- where sal = 10 or sal= 20 ....
--ORA-01427: 단일 행 하위 질의에 2개 이상의 행이 리턴되었습니다.

--부하직원이 있는 사원의 사번과 이름을 출력하세요
--내 사번이 mgr 컬럼 최소한번은 나온다

select mgr from emp;

select *
from emp
where empno in (select mgr from emp);

--부하직원 없는 사원의 모든 정보 출력

select *
from emp
where empno not in (select nvl(mgr,0) from emp);
-- != and != and null  >  null

--20번 부서의 사원 중에서 가장 많은 월급을 받는 사원보다 더 많은 월급을 받는 사원의
--사번, 이름 , 급여 , 부서번호를 출력하세요

select max(sal) from emp where deptno=20;

select empno, ename ,sal
from emp
where sal > (select max(sal) from emp where deptno=20);

--POINT
--다중테이블 : JOIN 하다하다 ... 안되지 ... 만약 테이블 있다면 ... >> 가상테이블  >> in line view(subquery) >> View
--좀 더 편한것 with 절 (가상테이블)


--자기 부서의 평균월급 보다 더 많은 월급을 받는 사원의 사번 , 이름 , 부서번호 ,
--부서별 평균월급을 출력하세요
--만약에 부서와 부서별 평균월급 담고 있는 테이블 제공(in line view) 가상테이블

select e.empno , e.ename , e.deptno , e.sal , s.avgsal
from emp e join (select deptno, avg(sal) as avgsal from emp group by deptno) s
                on e.deptno = s.deptno
where e.sal > s.avgsal;

--------------------------------------------------------------------------------
--with 절
/*
오라클의 WITH 절 (Common Table Expression, CTE)
"임시 테이블을 만들어서 쿼리를 더 읽기 쉽게 만드는 기능"

WITH 임시테이블명 AS (
    서브쿼리
)
SELECT * FROM 임시테이블명;

--쿼리 가독성 ↑
*/

WITH dept_avg AS (
    SELECT DEPTNO, AVG(SAL) AS avg_sal
    FROM EMP
    GROUP BY DEPTNO
)
SELECT *
FROM dept_avg;


WITH dept_avg AS (
    SELECT DEPTNO, AVG(SAL) AS avg_sal
    FROM EMP
    GROUP BY DEPTNO
)
SELECT e.ENAME, e.SAL, d.avg_sal
FROM EMP e JOIN dept_avg d  ON e.DEPTNO = d.DEPTNO
WHERE e.SAL >= d.avg_sal;

/*
select e.empno , e.ename , e.deptno , e.sal , s.avgsal
from emp e join (select deptno, avg(sal) as avgsal from emp group by deptno) s
on e.deptno = s.deptno
where e.sal > s.avgsal;

*/

-- 직무별 평균 급여 구하고 3000 이상만 출력

WITH job_avg AS (
    SELECT JOB, AVG(SAL) avg_sal
    FROM EMP
    GROUP BY JOB
)
SELECT *
FROM job_avg
WHERE avg_sal >= 3000;

--subquery 와 with 절 같이 보세요 (가상 테이블) in line view
--------------------------------------------------------------------------------
--1. 'SMITH'보다 월급을 많이 받는 사원들의 이름과 월급을 출력하라.
SELECT ENAME, SAL
FROM EMP
WHERE SAL>(SELECT SAL
           FROM EMP
           WHERE ENAME='SMITH');

--2. 10번 부서의 사원들과 같은 월급을 받는 사원들의 이름, 월급,
-- 부서번호를 출력하라.
SELECT ENAME, SAL, DEPTNO
FROM EMP
WHERE SAL IN(SELECT SAL
             FROM EMP
             WHERE DEPTNO=10);

--3. 'BLAKE'와 같은 부서에 있는 사원들의 이름과 고용일을 뽑는데
-- 'BLAKE'는 빼고 출력하라.
SELECT ENAME, HIREDATE
FROM EMP
WHERE DEPTNO=(SELECT DEPTNO
              FROM EMP
              WHERE ENAME='BLAKE')
  AND ENAME!='BLAKE';

--4. 평균급여보다 많은 급여를 받는 사원들의 사원번호, 이름, 월급을
-- 출력하되, 월급이 높은 사람 순으로 출력하라.
SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE SAL>(SELECT  AVG(SAL)  FROM EMP)
ORDER BY SAL DESC;

--5. 이름에 'T'를 포함하고 있는 사원들과 같은 부서에서 근무하고
-- 있는 사원의 사원번호와 이름을 출력하라.
SELECT EMPNO, ENAME
FROM EMP
WHERE DEPTNO IN(SELECT DEPTNO
                FROM EMP
                WHERE ENAME LIKE '%T%');
--where deptno = 20 or deptno= 30


--6. 30번 부서에 있는 사원들 중에서 가장 많은 월급을 받는 사원보다
-- 많은 월급을 받는 사원들의 이름, 부서번호, 월급을 출력하라.
--(단, ALL(and) 또는 ANY(or) 연산자를 사용할 것)
SELECT ENAME, DEPTNO, SAL
FROM EMP
WHERE SAL > (SELECT MAX(SAL)
             FROM EMP
             WHERE DEPTNO=30);

SELECT ENAME, DEPTNO, SAL
FROM EMP
WHERE SAL > ALL(SELECT SAL
                FROM EMP
                WHERE DEPTNO=30)

--where sal > 1600 and sal > 1250 and sal > 2850 and sal > 1500 and sal > 950


--7. 'DALLAS'에서 근무하고 있는 사원과 같은 부서에서 일하는 사원의
-- 이름, 부서번호, 직업을 출력하라.
SELECT ENAME, DEPTNO, JOB
FROM EMP
WHERE DEPTNO IN(SELECT DEPTNO    -- = 이 맞는데  IN
                FROM DEPT
                WHERE LOC='DALLAS');

--8. SALES 부서에서 일하는 사원들의  같은 부서번호, 이름, 직업을 갖는 사원정보를 출력하라.
SELECT DEPTNO, ENAME, JOB
FROM EMP
WHERE DEPTNO IN(SELECT DEPTNO
                FROM DEPT
                WHERE DNAME='SALES')



--9. 'KING'에게 보고하는 모든 사원의 이름과 급여를 출력하라
--king 이 사수인 사람 (mgr 데이터가 king 사번)
SELECT ENAME, SAL
FROM EMP
WHERE MGR=(SELECT EMPNO
           FROM EMP
           WHERE ENAME='KING');

--10. 자신의 급여가 평균 급여보다 많고, 이름에 'S'가 들어가는
-- 사원과 동일한 부서에서 근무하는 모든 사원의 사원번호, 이름,
-- 급여를 출력하라.
SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE SAL > (SELECT AVG(SAL)
             FROM EMP)
  AND DEPTNO IN(SELECT DEPTNO
                FROM EMP
                WHERE ENAME LIKE '%S%');

--select * from emp
--where  deptno in  (
--                      select deptno from emp where sal > (select avg(sal) from emp)
--                      and ename like '%S%'
--                   )

--11. 커미션을 받는 사원과 부서번호, 월급이 같은 사원의
-- 이름, 월급, 부서번호를 출력하라.
SELECT ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO IN(SELECT DEPTNO
                FROM EMP
                WHERE COMM IS NOT NULL)
  AND SAL IN( SELECT SAL
              FROM EMP
              WHERE COMM IS NOT NULL);

--12. 30번 부서 사원들과 월급과 커미션이 같지 않은
-- 사원들의 이름, 월급, 커미션을 출력하라.
SELECT ENAME, SAL, COMM
FROM EMP
WHERE SAL NOT IN(SELECT SAL
                 FROM EMP
                 WHERE DEPTNO=30)
  AND COMM NOT IN(SELECT NVL(COMM, 0)
                  FROM EMP
                  WHERE DEPTNO=30 and comm is not null);

--SELECT NVL(COMM, 0)
--FROM EMP
--WHERE DEPTNO=30 and comm is not null;

--SELECT COMM
--FROM EMP
--WHERE DEPTNO=30 and comm is not null;

--------------------------------------------------------------------------------
--기본적인 DB 역량 (신입)
/*
select         5
from           1
where          2
group by       3
having         4
order by       6
+
기본 함수 (문자 ,숫자 , 날짜 , 변환) , 일반(decode) 집계함수 활용
+
다중테이블 데이터 처리 (JOIN , UNION)
+
Subquery (with 절)
+
DML(insert, update , delete)
+
기본적인 DDL
*/

/* 여기서부터! (26.04.28 오후 4시~)
1. DDL : create , alter , drop (truncate) , rename , modify
2. DML : insert , update , delete (TCL : commit , rollback)

친구 취업 배포 ... 실 서버 구동
>> select
>> insert , update , delete 시점부터

1. commit , rollback 하지 않으면 ....
2. DML 작업 >> Oracle DB >> log write >> DML 수행 >> 백업 하지 않으면 >> log DISK FULL >> back up >> log file 삭제

오전 오후 insert , update , delete

   savepoint : 특정 지점에서 다시 시작할 수 있는 것
3. DQL : select
4. DCL : grant , revoke
5. TCL : commit , rollback , savepoint

*/
--------------------------------------------------------------------------------
--DML 수업
--------------------------------------------------------------------------------
--INSERT
select * from tab; --접속한 계정이 볼수 있는 table목록

select * from tab where tname='BOARD';
select * from tab where tname='EMP';

create table temp(
                     id number primary key,
                     name varchar2(20)
);

insert into temp(id,name)
values(100,'홍길동');

select * from temp;
commit;

--문제발생
insert into temp(id,name)
values(100,'김유신');
--ORA-00001: 무결성 제약 조건(KOSA.SYS_C008299)에 위배됩니다

insert into temp(name)
values('김유신');
--ORA-01400: NULL을 ("KOSA"."TEMP"."ID") 안에 삽입할 수 없습니다

--PL-SQL(변수 , 제어문) 데이터 넣고 .....
create table temp2( id varchar2(50));

--PL-SQL
/*
BEGIN
    FOR i IN 1..100 LOOP
        insert into temp2(id) values('A' || to_char(i));
    END LOOP;
END;
*/

select * from temp2;
commit;

create table temp3(
                      memberid number(3) not null,
                      name varchar2(10), --default null 허용
                      regdate date default sysdate --DB서버 날짜
);

insert into temp3(memberid,name,regdate)
values(100,'홍길동','2026-04-28');

select * from temp3;

insert into temp3(memberid,name)
values(200,'김유신');

select * from temp3;

commit;

--대량 데이터 삽입하기
create table temp4(id number);
create table temp5(num number);

insert into temp4(id) values(1);
insert into temp4(id) values(2);
insert into temp4(id) values(3);
insert into temp4(id) values(4);
insert into temp4(id) values(5);
insert into temp4(id) values(6);
insert into temp4(id) values(7);
insert into temp4(id) values(8);
insert into temp4(id) values(9);
insert into temp4(id) values(10);
commit;

select * from temp4;
select * from temp5;
--** 대량 데이터 삽입

--insert into 테이블명(컬럼리스트) values ...
--insert into 테이블명(컬럼리스트) select 절 ...

insert into temp5(num)
select id from temp4; --타입 맞고 컬럼 개수 맞아야 한다

select * from temp5;
commit;

--** 대량 데이터 삽입
--데이터를 담을 테이블도 없는 경우
--테이블 구조 (스키마) 복제 >> 데이터 삽입
--단 제약정보 복제 되지 않아요

create table copyemp
as
select empno ,ename ,sal
from emp
where deptno=20;

select * from copyemp;

create table copyemp2
as
select *
from emp
where 1=2;  -- 거짓 조건 구조 복제

select * from copyemp2;
-- 여기까지 insert

/*
update 테이블명
set 컬럼명=값 , 컬럼명1= 값1 , ...
where 조건절


update 테이블명
set 컬럼명=(subquery) , 컬럼명1= 값1 , ...
where (subquery)
*/

select * from copyemp;

update copyemp
set sal=0;

select * from copyemp;

rollback;

select * from copyemp;

update copyemp
set sal = 0
where empno=7369;

select * from copyemp;
commit;

update copyemp
set sal = (select sum(sal) from emp);

select * from copyemp;
rollback;

update copyemp
set ename='AAAA',sal=1000
where empno=7369;

select * from copyemp;
commit;
-- 여기까지 update

delete from copyemp;

select * from copyemp;

rollback;

delete from copyemp
where empno=7369;

select * from copyemp;

commit;

-- 여기까지 delete
-- 4.29 배울 예정
-- table 생성, 제약
-- 개발자에게 필요한 sql 진행 예정(view, 분석 함수)