package com.example.order.exception;

public class ProductNotFoundException extends CustomException {
    public ProductNotFoundException(Integer productId) {

        super("Product no found with " + productId);
    }
}

