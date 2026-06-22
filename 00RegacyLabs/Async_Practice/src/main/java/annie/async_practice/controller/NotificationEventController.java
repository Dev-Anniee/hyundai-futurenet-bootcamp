package annie.async_practice.controller;

import annie.async_practice.dto.NotificationEvent;
import annie.async_practice.service.NotificationLogService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification-events")
public class NotificationEventController {

  private final NotificationLogService notificationLogService;

  public NotificationEventController(NotificationLogService notificationLogService) {
    this.notificationLogService = notificationLogService;
  }

  @GetMapping
  public List<NotificationEvent> events() {
    return notificationLogService.findAll();
  }

  @DeleteMapping
  public ResponseEntity<Void> clear() {
    notificationLogService.clear();
    return ResponseEntity.noContent().build();
  }
}
