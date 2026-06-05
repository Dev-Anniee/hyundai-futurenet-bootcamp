package kr.or.kosa.service;

import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.EmpDao;

public class MgrListService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();

            EmpDao dao = new EmpDao();
            List<Integer> mgrList = dao.getMgrList(); // DISTINCT MGR 번호 목록 가져오기

            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < mgrList.size(); i++) {
                json.append(mgrList.get(i));
                if (i < mgrList.size() - 1) json.append(",");
            }
            json.append("]");

            out.print(json.toString());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 화면 이동 없음 → JSON 응답만
    }
}
