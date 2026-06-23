package kr.or.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

  @GetMapping("/")
  public String home() {
    return "redirect:/intro";
  }

  @GetMapping("/intro")
  public String intro(Model model) {
    model.addAttribute("pageTitle", "자기 소개");
    model.addAttribute("content", "user/intro");
    return "layout";
  }

  @GetMapping("/career")
  public String career(Model model) {
    model.addAttribute("pageTitle", "경력");
    model.addAttribute("content", "user/career");
    return "layout";
  }

  @GetMapping("/place")
  public String place(Model model) {
    model.addAttribute("pageTitle", "사는 곳");
    model.addAttribute("content", "user/place");
    return "layout";
  }
}
