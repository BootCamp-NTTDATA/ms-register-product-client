package com.bootcamp.msregisterproductclient.request;

import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.TypeCredit;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ClientCreditRequest {
    private String code;
    private BigDecimal amountGiven;
    private BigDecimal amountPaid;
    private int fees;
    private int feesPaid;
    private String idClient;
    private String idTypeCredit;
    private boolean state;
}
