package annie.async_practice.dto;

import lombok.Data;

@Data
public class OrderRequest {

  private Long memberId;
  private String productName;
  private int quantity;
  private int price;
}
