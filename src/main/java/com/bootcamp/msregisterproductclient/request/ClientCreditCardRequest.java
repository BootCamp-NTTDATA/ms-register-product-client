package com.bootcamp.msregisterproductclient.request;

import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.TypeCreditCard;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ClientCreditCardRequest {
    private String code;
    private String creditCardNumber;
    private BigDecimal limitAmount;
    private BigDecimal usedAmount;
    private int fees;
    private int feesPaid;
    private int cutoffDate;
    private int limitDate;
    private LocalDateTime openingDate;
    private LocalDateTime deliveryDate;
    private String idClient;
    private String idTypeCreditCard;
    private boolean state;
}
