package com.Product.product.Exception;

import com.Product.product.Response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
@ExceptionHandler(ProductCustomeException.class)
    public ResponseEntity<ErrorResponse>handleProductException(ProductCustomeException exception){
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(exception.getMessage())
                .errorcode(exception.getErrorcode())
                .build(), HttpStatus.NOT_FOUND);
    }
}
