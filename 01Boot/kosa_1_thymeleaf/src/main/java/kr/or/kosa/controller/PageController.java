package kr.or.kosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("content", "user/home");
        return "layout/user-layout";
    }

    @GetMapping("/user/profile")
    public String profile(Model model) {
        model.addAttribute("content", "user/profile");
        return "layout/user-layout";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("content", "admin/dashboard");
        return "layout/admin-layout";
    }

    @GetMapping("/admin/member-list")
    public String memberList(Model model) {
        model.addAttribute("content", "admin/member-list");
        return "layout/admin-layout";
    }
}

/*
model.addAttribute("content", "user/home");
return "layout/user-layout";

의미:
user-layout.html을 먼저 열고
그 안에 user/home.html의 content 조각을 삽입한다.

핵심 코드:
<div th:replace="~{${content} :: content}"></div>

즉,
${content} = user/home

*/