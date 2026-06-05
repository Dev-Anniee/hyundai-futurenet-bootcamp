package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.dao.EmpDao;

@WebServlet("/EmpnoCheck")
public class EmpnoCheck extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmpnoCheck() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            int empno = Integer.parseInt(request.getParameter("empno"));
            EmpDao dao = new EmpDao();
            boolean isDuplicate = dao.isEmpnoDuplicate(empno);

            if (isDuplicate) {
                out.print("DUPLICATE");
            } else {
                out.print("AVAILABLE");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("ERROR");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }
}
