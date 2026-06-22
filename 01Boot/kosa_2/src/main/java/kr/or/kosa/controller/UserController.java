package kr.or.kosa.controller;

import java.util.List;
import kr.or.kosa.model.User;
import kr.or.kosa.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping({"", "/list"})
  public String listUsers(Model model) {
    List<User> users = userService.getAllUsers();
    model.addAttribute("users", users);
    return "user/list";
  }

  @GetMapping("/new")
  public String form() {
    return "user/form";
  }

  @PostMapping
  public String createUser(User user) {
    userService.createUser(user);
    return "redirect:/users";
  }

  @GetMapping("/{id}/edit")
  public String showEditForm(@PathVariable("id") long id, Model model) {
    User user = userService.getUserById(id);
    model.addAttribute("user", user);
    return "user/edit";
  }

  @PostMapping("/{id}/edit")
  public String updateUser(@PathVariable("id") long id, User user) {
    user.setId(id);
    userService.updateUser(user);
    return "redirect:/users";
  }

  @GetMapping("/{id}/delete")
  public String deleteUser(@PathVariable("id") long id) {
    userService.deleteUser(id);
    return "redirect:/users";
  }
}
