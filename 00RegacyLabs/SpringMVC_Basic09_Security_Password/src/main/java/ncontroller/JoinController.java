package ncontroller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import service.JoinService;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JoinService service;

	@GetMapping("join.do")
	public String join() {
		return "joinus/join";
	}

	@PostMapping("join.do")
	public String join(Member member) throws ClassNotFoundException, SQLException {
		System.out.println(member.toString());

		member.setPwd(this.bCryptPasswordEncoder.encode(member.getPwd()));
		int result = service.insertMember(member);

		if (result > 0) {
			System.out.println("join success");
			return "redirect:/index.do";
		}

		System.out.println("join failed");
		return "join.do";
	}

	@GetMapping("login.do")
	public String login() {
		return "joinus/login";
	}

	@GetMapping("accessDenied.do")
	public String Denied() {
		return "joinus/accessDenied";
	}
}
