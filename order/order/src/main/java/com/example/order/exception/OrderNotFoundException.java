package com.example.order.exception;

public class OrderNotFoundException extends CustomException {
  public OrderNotFoundException(Integer orderId) {

    super("Order not found with " +orderId);
  }
}
