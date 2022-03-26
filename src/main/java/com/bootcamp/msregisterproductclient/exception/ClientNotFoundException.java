package com.bootcamp.msregisterproductclient.exception;

public class ClientNotFoundException extends RuntimeException{

    private static final String MESSAGE = "CLIENT NOT FOUND";

    public ClientNotFoundException(){
        super(MESSAGE);
    }
}
