package com.example.product.exception;

import com.example.product.model.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class GlobalHandlerException {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorException> HandlerProductNotFoundException(ProductNotFoundException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .HttpStatus(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorException);
    }

    @ExceptionHandler(ProductAlreadytExistException.class)
    public ResponseEntity<ErrorException> HandlerProductAlreadytExistException(ProductAlreadytExistException exception){
        ErrorException errorException = ErrorException.builder()
                .localDateTime(LocalDateTime.now())
                .HttpStatus(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorException);
    }
}
