package com.bootcamp.msregisterproductclient.webclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private String id;
    private BigDecimal maintenanceCommission;
    private BigDecimal transactionCommission;
    private BigDecimal minimumOpeningAmount;
    private int numLimitMovements;
    private String type;
    private int dayMovement;
    private Boolean allowCompany;
    private Boolean allowPerson;
    private Boolean needCreditCard;
}
