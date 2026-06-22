package annie.async_practice.service;

import annie.async_practice.dto.NotificationEvent;
import java.time.Instant;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class NotificationLogService {

  private static final int MAX_EVENT_SIZE = 500;

  private final AtomicLong sequence = new AtomicLong();
  private final Deque<NotificationEvent> events = new ConcurrentLinkedDeque<>();

  public void record(String type, String message) {
    NotificationEvent event = new NotificationEvent(
        sequence.incrementAndGet(),
        type,
        message,
        Thread.currentThread().getName(),
        Instant.now()
    );

    events.addLast(event);
    while (events.size() > MAX_EVENT_SIZE) {
      events.pollFirst();
    }

    System.out.println(message);
  }

  public List<NotificationEvent> findAll() {
    return List.copyOf(events);
  }

  public void clear() {
    events.clear();
  }
}
