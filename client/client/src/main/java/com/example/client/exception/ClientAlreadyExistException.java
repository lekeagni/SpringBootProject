package com.example.client.exception;

public class ClientAlreadyExistException extends CustomException {
    public ClientAlreadyExistException(String email) {

        super("client already exist with " + email);
    }
}
