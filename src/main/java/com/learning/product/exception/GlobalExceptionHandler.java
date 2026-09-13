package com.learning.product.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler (ProductNotFound.class)
    public ResponseEntity<?> handleProductNotFound(ProductNotFound e) {
        logger.info("handleProductNotFound");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());

    }

}
