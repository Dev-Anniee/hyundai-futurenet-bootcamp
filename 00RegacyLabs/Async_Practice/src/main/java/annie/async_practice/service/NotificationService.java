package annie.async_practice.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private final NotificationLogService notificationLogService;

  public NotificationService(NotificationLogService notificationLogService) {
    this.notificationLogService = notificationLogService;
  }

  @Async("taskExecutor")
  public void sendOrderCompleteNotification(long orderNo, Long memberId, String productName) {
    notificationLogService.record("notification", "[주문 #" + orderNo + "] 알림 발송 시작");
    notificationLogService.record(
        "thread",
        "[주문 #" + orderNo + "] 현재 스레드: " + Thread.currentThread().getName()
    );

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      notificationLogService.record("error", "[주문 #" + orderNo + "] 알림 발송이 중단되었습니다.");
      return;
    }

    notificationLogService.record(
        "notification",
        "[주문 #" + orderNo + "] [알림] 회원 " + memberId + "님, " + productName + " 주문이 완료되었습니다."
    );
    notificationLogService.record("notification", "[주문 #" + orderNo + "] 알림 발송 완료");
  }
}
