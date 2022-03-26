package com.bootcamp.msregisterproductclient.dto;

import com.bootcamp.msregisterproductclient.entity.BankAccount;
import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.TypeCredit;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditClientDto {
    private String id;
    private String code;
    private BigDecimal amountGiven;
    private BigDecimal amountPaid;
    private int fees;
    private int feesPaid;
    private Client client;
    private TypeCredit typeCredit;
    private boolean state;
}
