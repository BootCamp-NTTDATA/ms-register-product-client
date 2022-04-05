package com.bootcamp.msregisterproductclient.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client{
    private String idClient;
    private String numberDocument;
    private String documentType;
    private String name;
    private String phone;
    private String email;
}
