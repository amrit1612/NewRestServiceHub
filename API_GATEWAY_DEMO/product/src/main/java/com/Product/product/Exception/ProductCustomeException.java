package com.Product.product.Exception;

import lombok.Data;

@Data
public class ProductCustomeException extends RuntimeException {
    private String errorcode;

    public ProductCustomeException(String message,String errorcode){
        super(message);
        this.errorcode=errorcode;
    }
}
