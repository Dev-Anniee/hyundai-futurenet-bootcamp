package ncontroller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/") // 부분 경로 미리 설정
public class CustomerController {
  //1. CustomerController는 NoticeDao에 의존합니다

  private NoticeDao noticedao;

  @Autowired //by type
  public void setNoticedao(NoticeDao noticedao) {
    this.noticedao = noticedao;
  }

  // 전체 조회 /cutomer/notice.do > notice.do
  @RequestMapping("/notice.do")
  public String notice(String _page, String _field, String _query, Model model)
      throws SQLException, ClassNotFoundException {
    //default 값 설정
    int page = 1;
    String field="TITLE";
    String query = "%%";
    if(_page != null   && ! _page.equals("")) {
      page  = Integer.parseInt(_page);
    }

    if(_field != null   && ! _field.equals("")) {
      field = _field;
    }

    if(_page != null   && ! _page.equals("")) {
      page  = Integer.parseInt(_page);
    }

    if(_query != null   && ! _query.equals("")) {
      query = _query;
    }

    //DAO 작업
    List<Notice>  list = noticedao.getNotices(page,field,query);

    //Spring  적용 Model model 스프링이 데이터를 담을 수 있는 모델 객체 주소 주입

    model.addAttribute("list", list); // 담기만 하면 자동 forward, return 하지 않아도 view로 이동


    //ModelAndView 잠깐 버리고..
    //Parameter DTO  자동 forward
    return "cutomer/notice";
    //WEB-INF/views + /customer/notice + .jsp
  }

  @RequestMapping("/noticeDetail.do") //noticeDetail.do?seq=${n.seq}
  public String noticeDetail(String seq, Model model) throws SQLException, ClassNotFoundException {
    Notice n = noticedao.getNotice(seq);
    model.addAttribute("n", n);
    return "cutomer/noticeDetail";
  }
}