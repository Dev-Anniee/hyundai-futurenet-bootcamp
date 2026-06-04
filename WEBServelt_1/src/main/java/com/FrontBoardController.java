package com;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
    description = "게시판 요청을 처리하는 servlet 입니다",
    urlPatterns = { "/Board" }
)
public class FrontBoardController extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public FrontBoardController() {
    super();

  }
  private void doProcess(HttpServletRequest request, HttpServletResponse response , String method) throws ServletException, IOException {
    //공통함수
    System.out.println("클라이언트 요청방식 : " + method);

    //Front 역할 (게시판 관련 요청 ... 모두 처리하겠다)
    	/*
    	  1. command 방식 : 약속 > list.do?cmd=list 게시판 조회 , list.do?cmd=insert 글쓰기
    	  1.1 cmd 변수에 있는 값을 추출해서 : 업무 판단

    	  2. url 방식
    	  2.1  localhost:8080/WEB/list.do
    	  2.2  마지막 주소 /list.do 추출 : 그 이름으로 업무 판단
    	  2.3 localhost:8080/WEB/write.do  : /write.do 글쓰기
    	*/

    //1. 한글처리
    request.setCharacterEncoding("UTF-8");

    //2. 데이터 받기
    //cmd
    String cmd = request.getParameter("cmd");

    //cmd > null > error.jsp
    //cmd > boardlist  > BoardList.jsp
    //cmd > boardwrite > BoardWrite.jsp


    //3. 업무처리 (요구사항 : 암호화 , 관리자 페이지 , DB연결 (CRUD)
    String viewPage = null;
    if(cmd == null) {
      viewPage= "/error/error.jsp";
    }else if(cmd.equals("boardlist")) {
      //DB연결 .. select 결과
      //Model (Board DTO , BoardDao DAO (CRUD)함수 구현
      //BoardDao boarddao = new BoardDao();
      //List<Board> list = boarddao.getAllBoardList();
      //request.setAttribute("list",list);


      viewPage = "/board/boardlist.jsp";
    }else if(cmd.equals("boardwrite")) {
      viewPage = "/board/boardwrite.jsp";
    }else if(cmd.equals("boarddelete")) {
      //..
    }else if(cmd.equals("login")) {
      viewPage = "/WEB-INF/views/login/login.jsp";
    }else {
      viewPage = "/error/error.jsp";
    }

    //4. 결과저장
    //전체 페이지 공유 : session.set...
    //현재 , include , forward 페이지 : request.set...
    //request.setAttribute("list", null);

    //5. view 지정
    RequestDispatcher dis =  request.getRequestDispatcher(viewPage);

    //6. view 에게 전달
    dis.forward(request, response);
    //현재 내가 가지고 있는 buffer 의 내용을 비우고 그 buffer viewPage 내용을 채위서 서비스 한다
    //주소는 안바뀐다 ..... 내 버퍼에 jsp 담아서 서비스 ....

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response, "GET");
  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response, "POST");
  }

}
