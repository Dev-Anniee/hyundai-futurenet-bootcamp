package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.dao.EmpDao;

@WebServlet("/MgrListAjax")
public class MgrListAjax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        EmpDao dao = new EmpDao();
        List<Integer> mgrList = dao.getMgrList(); // DAO에 distinct mgr 목록 가져오기

        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < mgrList.size(); i++) {
            json.append(mgrList.get(i));
            if (i < mgrList.size() - 1) json.append(",");
        }
        json.append("]");

        out.print(json.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }
}
