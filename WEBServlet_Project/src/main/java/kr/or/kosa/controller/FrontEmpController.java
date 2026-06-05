package kr.or.kosa.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.DeptNoListService;
import kr.or.kosa.service.EmpListByDeptService;
import kr.or.kosa.service.EmpListService;
import kr.or.kosa.service.EmpnoCheckService;
import kr.or.kosa.service.InsertEmpService;
import kr.or.kosa.service.MgrListService;

@WebServlet("*.do")
public class FrontEmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontEmpController() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    	
    	String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlCommand = requestUri.substring(contextPath.length());

        System.out.println("urlCommand = " + urlCommand);  
        
        Action action = null;
        ActionForward forward = null; 
        
        if(urlCommand.equals("/DeptnoList.do")) {
            action = new DeptNoListService();
            forward = action.execute(request, response);
        } else if(urlCommand.equals("/EmpListByDept.do")) {
            action = new EmpListByDeptService();
            forward = action.execute(request, response);
        } else if(urlCommand.equals("/EmpnoCheck.do")) {
            action = new EmpnoCheckService();
            forward = action.execute(request, response);
        } else if (urlCommand.equals("/EmpList.do")) {
            action = new EmpListService();
            forward = action.execute(request, response);
        } else if(urlCommand.equals("/InsertEmp.do")) {
            action = new InsertEmpService();
            forward = action.execute(request, response);
        } else if(urlCommand.equals("/InsertEmpForm.do")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/insertEmployee.jsp");
        } else if(urlCommand.equals("/DeptnoListForm.do")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/deptnoList.jsp");
        } else if(urlCommand.equals("/DeptnoListAjax.do")) {
            action = new DeptNoListService();    
            forward = action.execute(request, response);
        } else if(urlCommand.equals("/MgrListAjax.do")) {
            action = new MgrListService();      
            forward = action.execute(request, response);
        }

        
        if(forward != null) {
        	if(forward.isRedirect()) { 
        		response.sendRedirect(forward.getPath());
        	}else { 
        		  RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
        		  dis.forward(request, response);
        	}
        }
        
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
