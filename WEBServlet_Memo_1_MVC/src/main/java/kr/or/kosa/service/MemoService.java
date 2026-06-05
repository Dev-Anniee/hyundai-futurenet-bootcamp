package kr.or.kosa.service;

import jakarta.servlet.http.HttpServletRequest;
import kr.or.kosa.dao.MemoDao;
import kr.or.kosa.dto.Memo;

import java.sql.SQLException;
import java.util.List;

public class MemoService {

    private final MemoDao memoDao;

    // 필요에 따라 DI 가능 (기본 생성자는 내부 생성)
    public MemoService() {
        this.memoDao = new MemoDao();
    }
    //public MemoService(MemoDao memoDao) {
    //    this.memoDao = memoDao;
    //}

    /** 단순 입력 폼 이동 */
    public String memoForm(HttpServletRequest request) {
        return "/WEB-INF/views/memo/memo_insert.jsp";
    }

    /** 상세보기 */
    public String memoDetail(HttpServletRequest request) {
        String id = request.getParameter("id");
        Memo memoDetail = memoDao.getMemoListById(id);
        request.setAttribute("memodetail", memoDetail);
        return "/WEB-INF/views/memo/memo_detail.jsp";
    }

    /** 목록 */
    public String memoList(HttpServletRequest request) {
        try {
            List<Memo> memoList = memoDao.getMemoList();
            request.setAttribute("memoList", memoList);
            return "/WEB-INF/views/memo/memo_list.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "메모 목록 조회 중 오류가 발생했습니다.");
            return "/WEB-INF/views/common/error.jsp"; // 필요시 존재하는 에러 JSP로 변경
        }
    }

    /** 등록 */
    public String memoInsert(HttpServletRequest request) {
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String content = request.getParameter("content");

        Memo memo = new Memo(id, email, content);
        memoDao.insertMemo(memo);

        request.setAttribute("memodetail", memo);
        return "/WEB-INF/views/memo/memo_detail.jsp";
    }

    /** 삭제 */
    public String memoDelete(HttpServletRequest request) {
        String id = request.getParameter("id");
        memoDao.deleteMemo(id);

        // 삭제 후 목록으로
        return memoList(request);
    }

    /** 수정 폼 (기존 데이터 조회) */
    public String memoUpdateForm(HttpServletRequest request) {
        String id = request.getParameter("id");
        Memo memoDetail = memoDao.getMemoListById(id);
        request.setAttribute("memoupdate", memoDetail);
        return "/WEB-INF/views/memo/memo_update.jsp";
    }

    /** 수정 처리 */
    public String memoUpdateOk(HttpServletRequest request) {
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String content = request.getParameter("content");

        Memo memo = new Memo(id, email, content);
        memoDao.updateMemo(memo);

        request.setAttribute("memodetail", memo);
        return "/WEB-INF/views/memo/memo_detail.jsp";
    }
}
