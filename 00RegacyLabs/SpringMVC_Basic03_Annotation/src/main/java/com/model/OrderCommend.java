package com.model;
/*
주문서 클래스
하나의 주문은 여러개의 상세 (상품) OrderItem을 가질 수 있다

Board/ Reply
하나의 게시글은 여러개의 댓글을 가질 수 있다

class Board {
  List<Reply> replyList;
}

하나의 은행은 여러개의 계좌를 가질 수 있다
*/


import java.util.ArrayList;
import java.util.List;

public class OrderCommend {
  private List<OrderItem> orderItem;

  public List<OrderItem> getOrderItemList() {
    return orderItem;
  }
  public void setOrderItemList(List<OrderItem> orderItemList) {
    this.orderItem = orderItemList;
  }
}

/*
주문 발생 2개의 상품
1, 10 파손 주의
2, 4 리모컨 별도 주문

OrderCommand command = new OrderCommend();

List<OrderItem> orderItemList = new ArrayList<>();
orderItemList.add(new OrderItem(1,10,"파손주의"));
orderItemList.add(new OrderItem(2,4,"리모컨 별도 주문"));

command.setOrderItem(itemList);
*/
