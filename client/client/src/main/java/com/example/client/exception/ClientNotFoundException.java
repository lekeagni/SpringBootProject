package com.example.client.exception;

public class ClientNotFoundException extends CustomException {
    public ClientNotFoundException(Integer clientId) {

      super("client nt found with "+ clientId);
    }
}
