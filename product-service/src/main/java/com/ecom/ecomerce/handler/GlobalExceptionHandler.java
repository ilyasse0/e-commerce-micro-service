package com.ecom.ecomerce.handler;

import com.ecom.ecomerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handle(ProductPurchaseException exp) {
        return ResponseEntity
                .status(HttpStatus.SC_BAD_REQUEST)
                .body(exp.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException exp) {
        return ResponseEntity
                .status(HttpStatus.SC_BAD_REQUEST)
                .body(exp.getMessage());
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<com.ecom.ecomerce.handler.ErrorResponse> handle(MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fileName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(errorMessage, errorMessage);
                });

        return ResponseEntity
                .status(HttpStatus.SC_BAD_REQUEST)
                .body(new com.ecom.ecomerce.handler.ErrorResponse(errors));
    }


}
