
package ncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import dao.MemberDao;
import lombok.RequiredArgsConstructor;
import service.MemberService;
import vo.Member;

@Controller
@RequiredArgsConstructor
@RequestMapping("/joinus/")
public class JoinController {

	//private MemberDao memberDao;

	private final MemberService memberService;
	
	//@Autowired
	//public void setMemberDao(MemberDao memberDao) {
	//	this.memberDao = memberDao;
	//}
	
	@GetMapping("join.do")   //   /joinus/join.do
	public String join() {
		return "joinus/join";
	}
	
	
	
	@PostMapping("join.do")  //  /joinus/join.do
	public String join(Member member) {
		System.out.println(member.toString());
		String url = null;
		try {
			   url = memberService.insert(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//insert , update > redirect:index.do
		return url;
	}
	
	//로그인 처리 (security)
	//@GetMapping("login.do")
	@GetMapping("login.do")    //   /joinus/login
	public String login() {
		return "joinus/login"; //view 주소
	}
}
