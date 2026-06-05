package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.EmpDao;

public class DeptNoListService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();

            EmpDao dao = new EmpDao();
            List<Integer> deptList = dao.getDeptNoList();

            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < deptList.size(); i++) {
                json.append(deptList.get(i));
                if (i < deptList.size() - 1) json.append(",");
            }
            json.append("]");

            out.print(json.toString());
            out.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null; 
    }
}

