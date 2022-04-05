package com.bootcamp.msregisterproductclient.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientDebitCard  extends BaseEntity{
    private String code;
    private String debitCardNumber;
    private Client client;
    private List<PersonClientAccount> accounts;
    private boolean state;
}
