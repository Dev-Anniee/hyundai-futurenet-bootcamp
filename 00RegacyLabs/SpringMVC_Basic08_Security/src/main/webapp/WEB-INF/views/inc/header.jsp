<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

	
<div id="header">
	<div class="top-wrapper">
		<h1 id="logo">
			<a href="/"><img src="" alt="로고" /></a>
		</h1>
		<h2 class="hidden">메인메뉴</h2>
		<ul id="mainmenu" class="block_hlist">
			<li><a href="">kosa가이드</a></li>
			<li><a href="">kosa과정</a></li>
			<li><a href="">kosa</a></li>
		</ul>
		<form id="searchform" action="" method="get">
			<fieldset>
				<legend class="hidden"> 과정검색폼 </legend>
				<label for="query">과정검색</label> <input type="text" name="query" />
				<input type="submit" class="button" value="검색" />
			</fieldset>
		</form>
		<h3 class="hidden">로그인메뉴</h3>
		<ul id="loginmenu" class="block_hlist">
			<li><a href="${pageContext.request.contextPath}/index.do">HOME</a></li>
			
			<!-- security 제공하는 서버에서  사용할 수 있는 script 언어 :  security:authorize-->
			<!-- 인증되지 않았다면  or 로그인 하지 않았다면  -->
			<security:authorize access="!hasRole('ROLE_USER')"> 
				<li><a href="${pageContext.request.contextPath}/joinus/login.do">로그인</a></li>
			</security:authorize>
			
			<!-- 
			인증이 성공되면 : session 객체 사용자의ID 담아 넣는다 
			스프링은 인증과 권한에 대한 별도의 객체를 만들어서 관리 (session 관리)
			
			spring 인증을 위해서 만든 객체 : userPrincipal 객체 자동 생성 ( name 속성을 통해서 id 관리)
			
			loginuser 변수에 사용자의 ID 값을 저장  
			 -->
			<security:authentication property="name" var="loginuser"/>
     			
     			
     		<!-- 로그인 되어 있는 상황이라면  /logout 요청 스프링이 처리  -->	
			<security:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
				 <li><a href="${pageContext.request.contextPath}/logout">${loginuser}:로그아웃</a></li>		
			</security:authorize>
			
			
			<li><a href="${pageContext.request.contextPath}/joinus/join.do">회원가입</a></li>
		</ul>
		<h3 class="hidden">회원메뉴</h3>
		<ul id="membermenu" class="clear">
			<li>
				<a href="">
				<img src="${pageContext.request.contextPath}/images/menuMyPage.png" alt="마이페이지" />
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/customer/notice.do">
					<img src="${pageContext.request.contextPath}/images/menuCustomer.png" alt="고객센터" />
				</a>
			</li>
		</ul>
	</div>
</div>