package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson; // JSON 변환 (Gson 라이브러리 사용)

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.EmpDao;
import kr.or.kosa.dto.Emp;

public class EmpListByDeptService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int deptno = Integer.parseInt(request.getParameter("deptno"));
            EmpDao dao = new EmpDao();
            List<Emp> empList = dao.getEmpListByDeptNo(deptno);

            // JSON 변환
            Gson gson = new Gson();
            String json = gson.toJson(empList);

            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();

            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
