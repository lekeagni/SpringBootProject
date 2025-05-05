package com.example.order.exception;

public class ClientNotFoundException extends CustomException {
    public ClientNotFoundException(Integer clientId) {

        super("client not found with "+ clientId);
    }
}
