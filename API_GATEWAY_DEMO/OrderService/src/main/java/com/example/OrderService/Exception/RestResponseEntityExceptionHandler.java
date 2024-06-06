package com.example.OrderService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderServiceCustomException.class)
    public ResponseEntity<ErrorResponse>handleCustomeException(OrderServiceCustomException exception){
        return new ResponseEntity<>(ErrorResponse.builder(exception,HttpStatus.NOT_FOUND, "orderservice")
                .detailMessageCode(exception.getMessage())
                .build(), HttpStatus.valueOf(exception.getStatus()));
    }
}
