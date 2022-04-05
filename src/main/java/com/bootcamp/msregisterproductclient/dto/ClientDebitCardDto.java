package com.bootcamp.msregisterproductclient.dto;

import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.PersonClientAccount;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientDebitCardDto {
    private String id;
    private String code;
    private String debitCardNumber;
    private Client client;
    private List<PersonClientAccount> accounts;
    private boolean state;
}
