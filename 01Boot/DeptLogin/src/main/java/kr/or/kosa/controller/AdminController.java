package kr.or.kosa.controller;

import kr.or.kosa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("users", userService.findAllUsers());
		return "/admin/index";
	}

	@PostMapping("/roles")
	public String grantRole(@RequestParam Long userId, @RequestParam String roleName) throws Exception {
		userService.grantRole(userId, roleName);
		return "redirect:/admin";
	}
}
