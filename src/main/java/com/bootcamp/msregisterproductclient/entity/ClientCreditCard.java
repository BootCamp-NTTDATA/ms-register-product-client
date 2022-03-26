package com.bootcamp.msregisterproductclient.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class ClientCreditCard extends BaseEntity {
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
