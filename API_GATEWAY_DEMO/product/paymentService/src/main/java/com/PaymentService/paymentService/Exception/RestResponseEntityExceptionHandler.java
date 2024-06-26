package com.PaymentService.paymentService.Exception;

import com.PaymentService.paymentService.Exception.PaymentServiceCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
@ExceptionHandler(PaymentServiceCustomException.class)
    public ResponseEntity<ErrorResponse>handleProductException(PaymentServiceCustomException exception){
    return new ResponseEntity<>(ErrorResponse.builder(exception,HttpStatus.NOT_FOUND,"exception")
           /* .errorMessage(exception.getMessage())
            .errorCode(exception.getErrorCode())*/
            .build(), HttpStatus.NOT_FOUND);
}
}
