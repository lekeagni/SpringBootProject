package com.example.product.exception;

public class ProductAlreadytExistException extends CustomException {
    public ProductAlreadytExistException(String name) {

        super("Product already exist with " + name);
    }
}
