package kr.or.kosa.controller;

import java.security.Principal;
import kr.or.kosa.dto.Users;
import kr.or.kosa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping({"", "/"})
	public String home(Model model, Principal principal) {
		String loginId = principal != null ? principal.getName() : "guest";
		model.addAttribute("loginId", loginId);
		return "index";
	}

	@GetMapping("/guest")
	public String guest() {
		return "/guest/index";
	}

	@GetMapping("/exception")
	public String exception(Authentication auth, Model model) {
		log.info("access denied");
		model.addAttribute("msg", "접근 권한이 없습니다.");
		model.addAttribute("auth", auth);
		return "/exception";
	}

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@GetMapping("/join")
	public String join() {
		return "/join";
	}

	@PostMapping("/join")
	public String joinPro(Users user) throws Exception {
		int result = userService.join(user);
		if (result > 0) {
			return "redirect:/login";
		}
		return "redirect:/join?error";
	}
}
