package com.model;
import lombok.Data;

//DB 테이블
//Order 과 OrderItem
//주문과 주문상세

@Data
public class OrderItem {
  private int itemid;
  private int number;
  private String remark;
}
