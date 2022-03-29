package com.bootcamp.msregisterproductclient.exception;

public class ProductNotFoundException extends RuntimeException{

    private static final String MESSAGE = "PRODUCT NOT FOUND";

    public ProductNotFoundException(){
        super(MESSAGE);
    }
}
