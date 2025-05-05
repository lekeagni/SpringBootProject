package com.example.order.exception;

public class OrderAlreadyExistException extends CustomException {
  public OrderAlreadyExistException(String message) {
    super(message);
  }
}
