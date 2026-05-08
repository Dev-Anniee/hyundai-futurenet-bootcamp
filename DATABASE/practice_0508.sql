drop table sales;

CREATE TABLE sales (
                       sales_no       NUMBER,
                       sales_year     NUMBER,
                       customer_name  VARCHAR2(20),
                       price          NUMBER
)
    PARTITION BY RANGE (sales_no) ( --수평분할
                                      PARTITION sales_p1 VALUES LESS THAN (3),
                                      PARTITION sales_p2 VALUES LESS THAN (5),
                                      PARTITION sales_p3 VALUES LESS THAN (MAXVALUE)
                                  );

-- 데이터 입력
INSERT INTO sales VALUES (1, 2026, '1', 5000);
INSERT INTO sales VALUES (2, 2026, '2', 5000);
INSERT INTO sales VALUES (3, 2026, '3', 5000);
INSERT INTO sales VALUES (4, 2026, '4', 5000);
INSERT INTO sales VALUES (5, 2026, '5', 5000);

COMMIT;

-- 전체 조회
SELECT * FROM sales;

-- 특정 파티션 조회 (성능을 높일 수 있다)
SELECT * FROM sales PARTITION (sales_p1);
SELECT * FROM sales PARTITION (sales_p2);
SELECT * FROM sales PARTITION (sales_p3);

-- 물리적 모델링
CREATE TABLE EMP (
                     empno VARCHAR2(20) NOT NULL,
                     ename VARCHAR2(20) NOT NULL,
                     sal NUMBER NOT NULL,
                     job VARCHAR2(20),
                     mgr VARCHAR2(20),
                     deptno NUMBER
);

COMMENT ON TABLE EMP IS '사원';

COMMENT ON COLUMN EMP.empno IS '사원번호';

COMMENT ON COLUMN EMP.ename IS '사원이름';

COMMENT ON COLUMN EMP.sal IS '급여';

COMMENT ON COLUMN EMP.job IS '직종';

COMMENT ON COLUMN EMP.mgr IS '관리자 ';

COMMENT ON COLUMN EMP.deptno IS '부서번호';

CREATE TABLE TABLE2 (
                        deptno NUMBER NOT NULL,
                        dname VARCHAR2(20) NOT NULL,
                        loc VARCHAR2(20) NOT NULL
);

COMMENT ON TABLE TABLE2 IS '부서';

COMMENT ON COLUMN TABLE2.deptno IS '부서번호';

COMMENT ON COLUMN TABLE2.dname IS '부서이름';

COMMENT ON COLUMN TABLE2.loc IS '부서위치';

CREATE UNIQUE INDEX PK_TABLE2
    ON TABLE2 (
               deptno ASC
        );

ALTER TABLE TABLE2
    ADD
        CONSTRAINT PK_TABLE2
            PRIMARY KEY (
                         deptno
                );

ALTER TABLE EMP
    ADD
        CONSTRAINT FK_TABLE2_TO_EMP
            FOREIGN KEY (
                         deptno
                )
                REFERENCES TABLE2 (
                                   deptno
                    );

select * from emp;
select deptno from emp;