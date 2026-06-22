package annie.async_practice.service;

import annie.async_practice.dto.OrderRequest;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final NotificationService notificationService;
  private final NotificationLogService notificationLogService;
  private final AtomicLong orderSequence = new AtomicLong();

  public OrderService(
      NotificationService notificationService,
      NotificationLogService notificationLogService
  ) {
    this.notificationService = notificationService;
    this.notificationLogService = notificationLogService;
  }

  public void order(OrderRequest request) {
    long orderNo = orderSequence.incrementAndGet();

    notificationLogService.record("order", "[주문 #" + orderNo + "] 주문 저장 시작");
    notificationLogService.record("order", "[주문 #" + orderNo + "] 회원 ID: " + request.getMemberId());
    notificationLogService.record("order", "[주문 #" + orderNo + "] 상품명: " + request.getProductName());
    notificationLogService.record("order", "[주문 #" + orderNo + "] 수량: " + request.getQuantity());
    notificationLogService.record("order", "[주문 #" + orderNo + "] 가격: " + request.getPrice());
    notificationLogService.record("order", "[주문 #" + orderNo + "] 주문 저장 완료");

    notificationService.sendOrderCompleteNotification(
        orderNo,
        request.getMemberId(),
        request.getProductName()
    );

    notificationLogService.record("order", "[주문 #" + orderNo + "] OrderService 종료");
  }
}
