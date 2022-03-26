package com.bootcamp.msregisterproductclient.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document
public class CreditClient extends BaseEntity{
    private String code;
    private BigDecimal amountGiven;
    private BigDecimal amountPaid;
    private int fees;
    private int feesPaid;
    private Client client;
    private TypeCredit typeCredit;
    private boolean state;
}
