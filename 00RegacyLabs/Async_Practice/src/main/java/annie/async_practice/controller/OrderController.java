package annie.async_practice.controller;

import annie.async_practice.dto.OrderRequest;
import annie.async_practice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<String> order(@RequestBody OrderRequest request) {
    orderService.order(request);
    return ResponseEntity.ok("주문 요청이 접수되었습니다.");
  }
}
