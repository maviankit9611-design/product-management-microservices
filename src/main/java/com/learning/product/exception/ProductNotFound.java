package com.learning.product.exception;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String msg) {
        super(msg);
    }
}
