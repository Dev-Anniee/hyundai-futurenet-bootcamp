package kr.or.kosa.service;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.EmpDao;
import kr.or.kosa.dto.Emp;

public class EmpListService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = new ActionForward();
        try {
            EmpDao dao = new EmpDao();
            List<Emp> emplist = dao.getEmpAllList(); 

            request.setAttribute("emplist", emplist);

            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/empList.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
