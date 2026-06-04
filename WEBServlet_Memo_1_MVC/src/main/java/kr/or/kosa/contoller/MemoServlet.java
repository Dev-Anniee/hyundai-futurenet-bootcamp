package kr.or.kosa.contoller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.dao.MemoDao;
import kr.or.kosa.dto.Memo;


@WebServlet("/MemoServlet")
public class MemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*
		 1. 한글처리
		 2. 데이터 받기
		    요구사항 판단 (frontController : 글쓰기 ,목록보기 판단) > servet 자체 글쓰기 
		 3. DAO 객체 생성
		 4. DAO 함수 실행
		 5. 결과 처리  
		 */
   	System.out.println("MemoServlet");
   	
   	request.setCharacterEncoding("UTF-8");
   	response.setContentType("text/html;charset=UTF8");
   	PrintWriter out = response.getWriter();
   	
   	String id = request.getParameter("id");
   	String email = request.getParameter("email");
   	String content = request.getParameter("memo");
   	
   	try {
			 
   			MemoDao memoDao = new MemoDao();
			int row = memoDao.insertMemo(new Memo(id,email,content)); 
			
			if(row > 0) {
				  out.print("<script>");
	    	     	out.print("alert('등록성공..');");
	    	     	out.print("location.href='MemoList';");  //jsp  >>   /MemoList
	    	      out.print("</script>");
			}else {
				//문제 ... 처리 바보
			}
			
		} catch (Exception e) {
			//insert 하다 문제 생기면 (null, pk)
			  out.print("<script>");
	  		      out.print("alert('등록실패..');");
	  		      out.print("location.href='memo.html';");
	  		      out.print("</script>");
	  		  out.print("<b> 오류 :" +  e.getMessage()  + "</b>");
		}
 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
