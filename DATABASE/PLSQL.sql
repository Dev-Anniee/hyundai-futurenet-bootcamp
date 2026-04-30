--PL-SQL
--PL/SQL 은 Oracle's Procedural Language extension to SQL. 의 약자 입니다.
--SQL문장에서 변수정의, 조건처리(IF), 반복처리(LOOP, WHILE, FOR)등을 지원하며,
--오라클 자체에 내장되어 있는Procedure Language입니다
--DECLARE문을 이용하여 정의되며, 선언문의 사용은 선택 사항입니다.
--PL/SQL 문은 블록 구조로 되어 있고PL/SQL 자신이 컴파일 엔진을 가지고 있습니다.

--Tool > 보기 > DBMS 출력창 > + 버튼 클릭 > 사용자 접속(개발자)
--DBMS 출력창 : 이클립스 console 창

--pl-sql (java : System.out.println()) 결과 확인
--DBMS 출력 창에서

--1.pl-sql 블럭 단위 실행
BEGIN
    DBMS_OUTPUT.PUT_LINE('HELLO WORLD');
END;

--pl-sql
--선언부(변수)
--실행부(변수 값을 할당 , 제어구문)
--예외부(Exception)

DECLARE --선언
    vno number(4);
    vname varchar2(20);
BEGIN
    vno := 100; -- 할당 >  String s; s = "홍길동"
    vname := 'kglim';
    DBMS_OUTPUT.PUT_LINE(vno); --화면 출력
    DBMS_OUTPUT.PUT_LINE(vname || '입니다');
END;

--변수 선언 방법 (타입)
--DECLARE
--v_job varchar2(10);
--v_count number := 10; --초기값 설정
--v_date date := sysdate + 7; --초기값 설정
--v_valid boolean not null := true
--------------------------------------------------------------------------------
DECLARE
    vno number(4);
    vname varchar2(20);
BEGIN
    select empno ,ename
    into vno , vname --pl-sql 사용하는 구분 (into) . 실행결과 [ 변수에 담기 ] --중요
    from emp
    where empno=&empno; --& 자바 scanner  역할 (입력값 받기)

    DBMS_OUTPUT.PUT_LINE('변수값 : ' || vno || '/' || vname);
END;

--실습 테이블 만들기
create table pl_test(
                        no number , name varchar2(20) , addr varchar2(50));

DECLARE
    v_no number := '&NO';
    v_name varchar2(20) := '&NAME';
    v_addr varchar2(50) := '&ADDR';
BEGIN
    insert into pl_test(no,name,addr)
    values(v_no , v_name , v_addr);
    commit;
END;

select * from pl_test;


desc emp;

select * from pl_test;
--변수 제어하기(타입)
--1.1 타입 : v_empno number(10)
-- POINT
--1.2 타입 : v_empno emp.empno%TYPE  (emp 테이블에 있는 empno 컬럼의 타입 사용)
--1.3 타입 : v_row emp%ROWTYPE (v_row 변수는 emp 테이블 모든 컬럼 타입 정보)
--                    v_row.empno , v_row.ename

--QUIZ
--두개의 정수를 입력받아서 그 합을   변수에 담아서 출력

DECLARE
    v_no1 number := 100;
    v_no2 number := 200;
    result number;
BEGIN
    result := v_no1 + v_no2;
    DBMS_OUTPUT.PUT_LINE('result : ' || result);
END;

--------------------------------------------------------------------------------
DECLARE
    v_emprow emp%ROWTYPE;
BEGIN
    select *
    into v_emprow -- empno , ename , ,..... deptno
    from emp
    where empno=7788;

    DBMS_OUTPUT.PUT_LINE(v_emprow.empno || '-' || v_emprow.ename || '-' || v_emprow.sal);
END;

--------------------------------------------------------------------------------
create sequence empno_seq
    increment by 1
    start with 8000
    maxvalue 9999
    nocycle
    nocache;

DECLARE
    v_empno emp.empno%TYPE;
BEGIN
    select empno_seq.nextval
    into v_empno
    from dual;

    insert into empdml(empno ,ename)
    values(v_empno,'홍길동');
    commit;
END;

select * from empdml;

create table empdml
as
select * from emp where 1=2;

--여기까지 변수 선언 , 타입 , 값 할당
--------------------------------------------------------------------------------
--pl-sql 제어문
DECLARE
    vempno emp.empno%TYPE;
    vename emp.ename%TYPE;
    vdeptno emp.deptno%TYPE;
    vname varchar2(20) := null;
BEGIN
    select empno , ename , deptno
    into vempno , vename , vdeptno --변수
    from emp
    where empno=7788;
    --제어문 if(조건문){실행문}
    IF(vdeptno = 10) THEN vname := 'ACC'; -- if(vdeptno==10) { vname = "ACC"}
    ELSIF(vdeptno=20) THEN vname := 'IT'; --ELSEIF
    ELSIF(vdeptno=30) THEN vname := 'SALES';
    END IF;
    DBMS_OUTPUT.PUT_LINE('당신의 직종은 : ' || vname);
END;

--IF() THEN 실행문
--ELSIF() THEN 실행문
--ELSIF() THEN 실행문
--ELSE 실행문 (선택)

--사번이 7788번인 사원의 사번 , 이름 , 급여를 변수에 담고
--변수에 담긴 급여가 2000 이상이면 '당신의 급여는 BIG' 출력하고
--그렇지 않으면(ELSE) '당신의 급여는 SMALL' 이라고 출력하세요

DECLARE
    vempno emp.empno%TYPE;
    vename emp.ename%TYPE;
    vsal   emp.sal%TYPE;
BEGIN
    select empno , ename , sal
    into vempno , vename , vsal
    from emp
    where empno=7788;
    --제어문 if(조건문){실행문}
    IF(vsal >=  2000) THEN
        DBMS_OUTPUT.PUT_LINE('당신의 급여는 BIG ' || vsal);
    ELSE
        DBMS_OUTPUT.PUT_LINE('당신의 급여는 SMALL ' || vsal);
    END IF;
END;

-------------------------------------------------------------------------------
--CASE
DECLARE
    vempno emp.empno%TYPE;
    vename emp.ename%TYPE;
    vdeptno emp.deptno%TYPE;
    v_name varchar2(20);
BEGIN
    select empno, ename , deptno
    into vempno, vename , vdeptno
    from emp
    where empno=7788;

    --    v_name := CASE vdeptno
    --                WHEN 10  THEN 'AA'
    --                WHEN 20  THEN 'BB'
    --                WHEN 30  THEN 'CC'
    --                WHEN 40  THEN 'DD'
    --              END;

    v_name := CASE
                  WHEN vdeptno=10  THEN 'AA'
                  WHEN vdeptno in(20,30)  THEN 'BB'
                  WHEN vdeptno=40  THEN 'CC'
                  ELSE 'NOT'
        END;
    DBMS_OUTPUT.PUT_LINE('당신의 부서명:' || v_name);
END;
--------------------------------------------------------------------------------
--pl-sql (반복문)
--Basic loop
/*
LOOP
  문자;
  EXIT WHEN (조건식)
END LOOP
*/
DECLARE
    n number :=0;
BEGIN
    LOOP
        DBMS_OUTPUT.PUT_LINE('n value : ' || n);
        n := n + 1;
        EXIT WHEN n > 5;
    END LOOP;
END;

/*
WHILE(n < 6)
LOOP
   실행문;
END LOOP
*/
DECLARE
    num number := 0;
BEGIN
    WHILE(num < 6)
        LOOP
            DBMS_OUTPUT.PUT_LINE('num 값 : ' || num);
            num := num +1;
        END LOOP;
END;

--for
--java for(int i = 0 ; i <= 10 ; i++) {}
BEGIN
    FOR i IN 0..10 LOOP
            DBMS_OUTPUT.PUT_LINE(i);
        END LOOP;
END;

--위 FOR 문을 사용해서 (1~100까지 합) 구하세요
DECLARE
    total number :=0;
BEGIN
    FOR i IN 1..100 LOOP
            total := total + i;
        END LOOP;
    DBMS_OUTPUT.PUT_LINE('1~100 총합 : ' || total);
END;

--11g 이전 (continue (x))
--11g (continue 추가)
DECLARE
    total number := 0;
BEGIN
    FOR i IN 1..100 LOOP
            DBMS_OUTPUT.PUT_LINE('변수 : ' || i);
            CONTINUE WHEN i > 5; --skip
            total := total + i; -- 1 , 2 , 3 , 4, 5
        END LOOP;
    DBMS_OUTPUT.PUT_LINE('합계 : ' || total);
END;
--------------------------------------------------------------------------------
--활용
DECLARE
    v_empno emp.empno%TYPE;
    v_name  emp.ename%TYPE := UPPER(:name);
    v_sal   emp.sal%TYPE;
    v_job   emp.job%TYPE;
    v_deptno emp.deptno%TYPE;
BEGIN
    select empno , job ,sal , deptno
    into v_empno, v_job , v_sal , v_deptno
    from emp
    where ename = v_name;

    IF v_job IN('MANAGER','ANALYST') THEN
        v_sal := v_sal * 1.5;
    ELSE
        v_sal := v_sal * 1.2;
    END IF;

    update emp
    set sal = v_sal
    where deptno=v_deptno;

    DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT || '개의 행이 갱신 되었습니다');

    --예외처리
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE(v_name || '는 자료가 없습니다');
    WHEN TOO_MANY_ROWS THEN
        DBMS_OUTPUT.PUT_LINE(v_name || '는 동명 이인입니다');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('기타 에러가 발생했습니다');
END;

SELECT * FROM EMP;
--제어문 끝 (04.30 ~2시 50분)

/*
질의는 하나의 행만 RETURN 해야 합니다. PL/SQL 블록 내의 SELECT 문장은 다음 규칙을
적용하는 Embedded SQL 의 ANSI 범주에 속합니다. 질의의 결과는 하나의 행만을 RETURN 해
야  하고  하나의  행  이상  또는  행이  없는  것은  에러를  생성합니다.  PL/SQL 은
NO_DATA_FOUND 와 TOO_MANY_ROWS 를 예외로 블록의 예외 섹션에서 추적할 수 있는 표준 예
외를 조성하여 처리 합니다.
*/
select * from emp where ename='SMITH';
rollback;

-- pl-sql 기본 구문  END

--변수 , 연산자 , 제어문 (PL-SQL 하기 위한 기본 학습)
--암기 할 필요 없어요 ...

--------------------------------------------------------------------------------
-- cursor , procedure(목표) , function , Trigger 고급자원
-- 이 내용은 프로젝트에 반영 되었으면 합니다
-- 그럼 프로젝트가 고급으로 올라가요^^
-- ex) 배치 프로그램
-- (04.30 ~3시 10분) 오늘 수업 끝