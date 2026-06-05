package kr.or.kosa.service;

import java.text.SimpleDateFormat;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.EmpDao;
import kr.or.kosa.dto.Emp;

public class InsertEmpService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = new ActionForward();
        try {
            request.setCharacterEncoding("UTF-8");

            int empno = Integer.parseInt(request.getParameter("empno"));
            String ename = request.getParameter("ename");
            int sal = Integer.parseInt(request.getParameter("sal"));
            String hiredateStr = request.getParameter("hiredate");
            int deptno = Integer.parseInt(request.getParameter("deptno"));
            int mgr = Integer.parseInt(request.getParameter("mgr"));
            String job = request.getParameter("job");

            java.util.Date hiredate = new SimpleDateFormat("yyyy-MM-dd").parse(hiredateStr);
 
            Emp emp = new Emp(empno, ename, job, mgr, hiredate, 0, deptno, sal, null);

            EmpDao dao = new EmpDao();
            int result = dao.insertEmp(emp);

            if(result > 0) {
                request.setAttribute("emp_msg", "사원 등록 성공 ✅");
                request.setAttribute("emp_url", "EmpList.do");  
            } else {
                request.setAttribute("emp_msg", "사원 등록 실패 ❌");
                request.setAttribute("emp_url", "InsertEmpForm.do");
            }

            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/redirect.jsp");


            forward.setRedirect(false);  
            forward.setPath("/WEB-INF/views/redirect.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
