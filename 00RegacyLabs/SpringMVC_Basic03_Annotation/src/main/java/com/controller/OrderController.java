package com.controller;

import com.model.OrderCommend;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
하나의 요청 주소로
GET : 화면
POST : 처리
*/
@Controller
@RequestMapping("/order/order.do")
public class OrderController {
  @GetMapping
  public String form() {
    return "order/OrderForm";
  }

  @PostMapping
  public String submit(OrderCommend command) {
    return "order/OrderCommitted";
  }
}
