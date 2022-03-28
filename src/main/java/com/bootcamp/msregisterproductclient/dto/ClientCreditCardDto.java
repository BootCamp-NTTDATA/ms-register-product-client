package com.bootcamp.msregisterproductclient.dto;

import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.TypeCreditCard;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientCreditCardDto {
    private String id;
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
    private Client client;
    private TypeCreditCard typeCreditCard;
    private String state;
}
