package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.EmpDao;

public class EmpnoCheckService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int empno = Integer.parseInt(request.getParameter("empno"));
            EmpDao dao = new EmpDao();
            boolean isDuplicate = dao.isEmpnoDuplicate(empno);

            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter out = response.getWriter();
            if(isDuplicate) {
                out.print("DUPLICATE");
            } else {
                out.print("AVAILABLE");
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  
    }
}
