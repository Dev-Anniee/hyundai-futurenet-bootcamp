package annie.async_practice.dto;

import java.time.Instant;
import lombok.Data;

@Data
public class NotificationEvent {

  private final long sequence;
  private final String type;
  private final String message;
  private final String threadName;
  private final Instant createdAt;
}
