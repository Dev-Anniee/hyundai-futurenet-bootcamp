-- 게시판 테이블 설계
CREATE TABLE jspboard(
  idx NUMBER CONSTRAINT pk_jspboard_idx PRIMARY KEY, -- 글번호
  writer VARCHAR2(30) NOT NULL, -- 글쓴이
  pwd VARCHAR2(20) NOT NULL, -- 비밀번호
  subject VARCHAR2(50) NOT NULL, -- 제목
  content VARCHAR2(100) NOT NULL, -- 글내용
  writedate DATE DEFAULT SYSDATE, -- 작성일
  readnum NUMBER DEFAULT 0, -- 조회수
  filename VARCHAR2(200), -- 파일명
  filesize NUMBER, -- 파일크기(byte)
  homepage VARCHAR2(50),
  email VARCHAR2(100),
  refer NUMBER DEFAULT 0, -- 답글 그룹 번호
  depth NUMBER DEFAULT 0, -- 답글 깊이
  step NUMBER DEFAULT 0 -- 답글 정렬 순서
);
CREATE SEQUENCE jspboard_idx START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE TABLE reply(
  no NUMBER PRIMARY KEY,
  writer VARCHAR2(30),
  userid VARCHAR2(30),
  pwd VARCHAR2(30),
  content VARCHAR2(100),
  writedate DATE DEFAULT SYSDATE,
  idx_fk REFERENCES jspboard(idx)
);
CREATE SEQUENCE reply_no START WITH 1 INCREMENT BY 1 NOCACHE;
SELECT * FROM jspboard ORDER BY idx DESC;
SELECT * FROM reply ORDER BY no DESC;
