package com.bootcamp.msregisterproductclient.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RechargeWalletDto {
    private String idTransmitter;
    private String accountNumber;
    private BigDecimal amount;
}
