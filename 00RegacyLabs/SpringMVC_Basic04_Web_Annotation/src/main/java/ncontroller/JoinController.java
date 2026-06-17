package ncontroller;

import dao.MemberDao;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
  private MemberDao memberDao;

  @Autowired
  public void setMemberDao(MemberDao memberDao){
    this.memberDao = memberDao;
  }

  @GetMapping("join.do") // join/join.do.do
  public String join(Member member) throws SQLException, ClassNotFoundException {
    System.out.println(member.toString());

    memberDao.insert(member);

    //insert, update > redirect:index.do
    return "redirect:index.do";
  }

  //로그인 처리 (security)
  //@GetMapping("login.do")
}
