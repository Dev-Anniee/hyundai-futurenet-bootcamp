package kr.or.kosa.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.MemoAddService;
import kr.or.kosa.service.MemoService;
import java.io.IOException;


@WebServlet("*.do")  // *.do > list.do , write.do 모든 요청을 FrontController 처리
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//FrontController 는  MemoSerivce  에 의존 합니다 (나는 너의 주소가 필요해)
	private MemoService memoService;  //포함 (연관) 전체집합
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.memoService = new MemoService();
	}
   
    public FrontController() {
        super();
       
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//클라이언의 모든 요청 FrontController 처리
    	//단 클라이언트의 요청 *.do 끝나야  한다
    	
    	//판단 : 목록보기요청, 글쓰기 요청
    	//1. command    ?cmd=list
    	//2. url        /list.do 마지막 주소로 판단
    	
    	//request.setCharacterEncoding("UTF-8");
    	//필요가 없다 (filter) 설정
    	//request.setCharacterEncoding(this.encoding);
    	
    	String requestUri = request.getRequestURI();
    	String contextPath = request.getContextPath(); //사이트명
    	String urlCommand = requestUri.substring(contextPath.length());  
    	
    	//localhost:8080/WEBJSP/list.do   >> /list.do
    	//URL
    	System.out.println("urlCommand = " + urlCommand);
    	
    	/*
    	  FrontController 처리 방법
    	  기준(약속 , 표준)
    	  
    	  화면 >
    	  처리 > (DB 처리 + 이동)

    	 */
    	
    	  Action action = null;
    	  ActionForward forward = null;
    	  
    	  if(urlCommand.equals("/MemoAdd.do")) { //글쓰기 처리
    		  //UI + 로직
    		  action = new MemoAddService();
    		  action.execute(request, response);
    		  
    	  }else if(urlCommand.equals("/MemoView.do")) {
    		  //UI
    		  forward = new ActionForward();
    		  forward.setRedirect(false);
    		  forward.setPath("/WEB-INF/views/memoview.jsp");
    	  }else if(urlCommand.equals("/MemoUpdate.do")) {
    		   action = new MemoAddService();
    		   action.execute(request, response);
    	  }else if(urlCommand.equals("/login.do")) {
    		  //UI
    		  forward = new ActionForward();
    		  forward.setRedirect(false);
    		  forward.setPath("/WEB-INF/views/login.jsp");
    	  }
    	  
    	  if(forward != null) {
    		  if(forward.isRedirect()) {
    			  response.sendRedirect(forward.getPath());
    		  }else {
    			  RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    		     dis.forward(request, response);
    		  }
    	  }
    	
    	
    	
    	
    	
    	   String viewPage;
    	
    	   switch (urlCommand) {
           case "/memo.do":
               viewPage = memoService.memoForm(request);
               break;
           case "/memodetail.do":
               viewPage = memoService.memoDetail(request);
               break;
           case "/memolist.do":
               viewPage = memoService.memoList(request);
               break;
           case "/memoinsert.do":
               viewPage = memoService.memoInsert(request);
               break;
           case "/memodelete.do":
               viewPage = memoService.memoDelete(request);
               break;
           case "/memoupdate.do":
               viewPage = memoService.memoUpdateForm(request);
               break;
           case "/memoupdateok.do":
               viewPage = memoService.memoUpdateOk(request);
               break;
           default:
               // 미매핑 요청 처리
               request.setAttribute("error", "요청하신 페이지를 찾을 수 없습니다.");
               viewPage = "/WEB-INF/views/common/error.jsp"; // 프로젝트에 맞는 경로로 수정
       }
    	RequestDispatcher dis = request.getRequestDispatcher(viewPage);
    	dis.forward(request, response);
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
