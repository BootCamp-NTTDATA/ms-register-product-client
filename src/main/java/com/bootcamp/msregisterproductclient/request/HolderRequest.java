package com.bootcamp.msregisterproductclient.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolderRequest {
    private String idRegister;
    private String numberDocument;
    private String documentType;
    private String name;
    private String phone;
    private String email;
}
