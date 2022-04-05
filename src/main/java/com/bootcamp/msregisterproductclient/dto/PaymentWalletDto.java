package com.bootcamp.msregisterproductclient.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentWalletDto {
    private String idTransmitter;
    private String phoneReceiver;
    private BigDecimal amount;
}
