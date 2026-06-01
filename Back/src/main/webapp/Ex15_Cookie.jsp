<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  
	정보(자료) 데이터 저장 > 어디에 (where)
	
	웹이라는 환경 관점 : 데이터는 어디에 저장
	
	클라이언트(개인 로컬 PC)    :   서버(웹서버(웹서비스가능) or DB서버)
	
	
	어디에 저장(보관)
	고민 (기준점) :  보안(데이터 중요성) , 소멸(일시적 , 영속적)
	
	보안 : 클라이언트 - 서버
	소멸 : 메모리 , 파일 , DB 
	
	Client (Local PC) : 웹 브라우져
	1. Cookie (메모리 쿠키 - 파일 쿠키 (local 디스크에 txt 암호화 시켜서 저장 : 소멸타임)
	   단점) 크기 제한적 (4096byte) , 보안 (사용자 삭제 , 해커 파일 노출 위험)
	2. local storage(key ,value) 형태 > JSON 형태로 read , write
	   장점) 크기 , 객체 사용(데이터 : 의미있는 데이터 저장)
	
	Server (웹 서버 (WAS) : 메모리 + 파일(보조) , DB서버)
	1. server memory : session 객체 (사이트 접속하는 [사용자]마다 [고유]하게 부여되는 메모리(객체) > 서버 리부팅 , 개발 코드 변경 > 소멸 > 생성
	2. server memory : application 객체 (사이트 접속하는 [모든 사용자]에게 부여되는 메모리(객체) > 서버 리부팅 , 개발 코드 변경 > 소멸 > 생성
	3. server 영속적 : 파일.txt  (관리가 힘들어요)
	4. DB server    : 보안, 영속적인 데이터 관리 >> 비용 >> 클라우드 
-->          
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Cookie mycookie = new Cookie("cname","1004");
		//서버 쿠키 생성
		//Client 전달하지 않았어요
		//서버 -> 쿠키  (클라이언트에게 전달)
		
		response.addCookie(mycookie); //웹브라져는 메모모리 쿠키 생성
	%>
</body>
</html>




