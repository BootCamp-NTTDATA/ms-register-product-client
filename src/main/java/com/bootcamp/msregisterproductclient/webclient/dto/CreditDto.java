package com.bootcamp.msregisterproductclient.webclient.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditDto {
    private String id;
    private String type;
    private BigDecimal interestRateMonth;
    private Boolean allowCompany;
    private Boolean allowPerson;
    private Boolean needCreditCard;
}
