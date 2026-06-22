package ncontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.MemberService;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//지금은 member field 주입 .... setter 주입 권장...
	
	//비밀번호 입력 화면
	@GetMapping("memberconfirm.do")
	public String memberConfirm() {
		return "joinus/memberConfirm";
	}
	
	@PostMapping("memberconfirm.do")
	public String memberConfirm(@RequestParam("password") String rawPassword , Principal principal) {
		
		//회원정보 가지고 와서 (비번(암호)하고 : 사용자가 입력한 비번(평문)
		String viewPage="";
		
		//회원정보
		Member member = memberService.getMember(principal.getName()); //인증된 사용자의 userid 값
		
		//DB에서 가지고 온 암호화된 값
		String encodedPassword = member.getPwd();
		
		System.out.println("사용자가 입력한 비번 : " + rawPassword);
		System.out.println("DB에서 가져온 비번 : " + encodedPassword);
		
		//함수
		boolean result = bCryptPasswordEncoder.matches(rawPassword, encodedPassword); //true 
		
		if(result) {
			viewPage = "redirect:memberUpdate.do";
		}else {
			viewPage = "redirect:memberconfirm.do";
		}
		
		return viewPage;
	}
	
	@GetMapping("memberUpdate.do")
	public String memberUpdate(Model model , Principal principal) {
		
		Member member = memberService.getMember(principal.getName());
		model.addAttribute("member", member);
		return "joinus/memberUpdate";
	}
	
	@PostMapping("memberUpdate.do")
	public String memberUpdate(Model model , Member member , Principal principal ) {
		
		Member updatemember = memberService.getMember(principal.getName());
		
		updatemember.setName(member.getName());
		updatemember.setCphone(member.getCphone());
		updatemember.setEmail(member.getEmail());
		
		//암호화 작업 (새로 입력받은 비밀번호는 평문) > 암호화
		updatemember.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
		
		memberService.updateMember(updatemember);
		
		return "redirect:/index.do";
	}
			
}


