package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemoDao;
import kr.or.kosa.dto.Memo;

public class MemoAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
	    String id = request.getParameter("id");
        String email = request.getParameter("email");
        String content = request.getParameter("content");

        Memo memo = new Memo(id, email, content);
        MemoDao memoDao = new MemoDao();
        memoDao.insertMemo(memo);
        
        request.setAttribute("list", memo);
        
        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/memoadd.jsp");
		
		
		return forward;
	}

}
