package com.ecom.ecommerce.handler;

import com.ecom.ecommerce.exception.CustomerNotFoundException;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException exp) {
        return ResponseEntity
                .status(HttpStatus.SC_NOT_FOUND)
                .body(exp.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fileName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(errorMessage, errorMessage);
                });

        return ResponseEntity
                .status(HttpStatus.SC_BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }


}
