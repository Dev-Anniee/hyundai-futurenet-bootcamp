package com;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
/*
1. servlet 정의 : java 파일인데 웹에 사용할 수 있는 자바 파일 (request, response 객체활용)
1.1 java 파일에 extends HttpServlet 상속관계 (일반자바 웹 객체(request, response) 사용가능)
1.2 웹용 사용할수 있는 java 파일 : servlet

Tip) 자바 웹 개발언어가 아니고 ... 웹 ..... java .... request, response ... 상속 ... servlet 웹서비스
     servlet 자바코드 편하고 UI 만드는 작업 힘들어요 >> 그래서 탄생 >> jsp
     servlet 형 -> jsp 동생 (UI 구성 편하고 + JAVA 코드 제어 힘들어 졌어요)


    HttpServletRequest request,   자동으로 request 생성되고 그 주소를 할당   //HttpServletRequest 인터페이스
    HttpServletResponse response  자동으로 response  생성되고 그 주소를 할당


	 1. 요청
	 JSP
	 http://192.168.4.150:8090/default.jsp

	 servlet
	 http://192.168.4.150:8090/SimpleController.java (x) 요청

	 web.xml
	 또는
	 @WebServlet
	 어떤 요청 주소가 들어어면 SimpleController.java 컴파일하고 실행 ... 정의
	 http://192.168.4.150:8090/Simple

	 web.xml
	 어떤 요청이 오면 어떤 servlet 파일 컴파일 하고 실행 결정 ....url_pattern 정의
	 <servlet>
		<servlet-name>simplecontroller</servlet-name>
		<servlet-class>com.SimpleContoller</servlet-class>
  	</servlet>
  	<servlet-mapping>
		<servlet-name>simplecontroller</servlet-name>
		<url-pattern>/simple</url-pattern>
  	</servlet-mapping>

  컴파일 하고 실행할 거야 ...

  주소 창에 : http://localhost:8090/simple 엔터
  web.xml 설정된 servlet-mapping  근거로 SimpleContoller.java 컴파일 하고 실행

  protected void doGet() 함수 아니면
  protected void doPost() 둘중에 하나가 자동 실행

  동기식 .... 둘중에 하나의 방식으로 요청 보내요
  GET   >>  void doGet() 실행 (자동)
  POST  >>  void doPost() 실행(자동)


  localhost:8080/WEBServlet_1/simple
*/

//@WebServlet("/SinpleController")
public class SimpleController extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public SimpleController() {
    super();
    System.out.println("SimpleController 생성자 함수 초기화 ...");
  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("클라이언트의 요청이 GET 자동 실행 함수");

    //역할 분리 (MVC)

		/*
		 1. 한글처리
		 2. 데이터 받기
		 3. 비지니스 로직 DB연결 ,암호화 , 객체 검색 ...
		 4. *결과를 담기 (session , request )
		 5. view 지정 (board.jsp ...)
		 7. view forward
		 */

    //1. 한글처리
    request.setCharacterEncoding("UTF-8");

    //2. 데이터 받기
    String type = request.getParameter("type");

    //3. 비지니스 (요구사항)
    Object resultObj = null;
    if(type == null || type.equals("greeting")) {
      resultObj="hello world";
    }else if(type.equals("data")) {
      resultObj = new Date();
    }else {
      resultObj = "invalid String type";
    }

    //4. 데이터 담기 (view (jsp) 에게 전달) (request 단 (include , forward) 다은 페이지 공유)
    request.setAttribute("result", resultObj);
    //  request 객체 xy번지

    //5. view 정의
		 /*
		   개발자 관습적 ...써왔다 ...
		   1. view > WEB-INF > views >  board > board.jsp , boardList.jsp   > image , css , js (webapp 아래 )
		                             >  member> member.jsp , memberWrite.jsp

		  */
    RequestDispatcher dis = request.getRequestDispatcher("/simpleview.jsp");
    //RequestDispatcher dis  통해서 forward 방식으로 request 공유 ..

    dis.forward(request, response);
    //xy번지


    //요청 주소 localhost:8080/WEBServlet_1/simple  > 결과는 담는 buffer (8kb)
    //forward 만나면 현재 buffer 비우고 현재 buffer 의 내용을 /simpleview.jsp 의 내용 채워요
    //전달 ( request.setAttribute("result", resultObj); > include forward 페이지 공유



  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("클라이언트의 요청이 POST 자동 실행 함수");
  }

}
