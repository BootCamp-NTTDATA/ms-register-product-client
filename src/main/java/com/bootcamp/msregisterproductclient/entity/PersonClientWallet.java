package com.bootcamp.msregisterproductclient.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class PersonClientWallet extends BaseEntity{
    private String code;
    private BigDecimal amount;
    private LocalDateTime openingDate;
    private Client person;
    private TypeWallet typeWallet;
    private boolean state;
}
