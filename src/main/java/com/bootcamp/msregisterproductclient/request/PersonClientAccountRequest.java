package com.bootcamp.msregisterproductclient.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PersonClientAccountRequest {
    private String id;
    private String code;
    private BigDecimal amount;
    private String accountNumber;
    private LocalDateTime openingDate;
    private String idPerson;
    private String idTypeAccount;
    private boolean state;
}
