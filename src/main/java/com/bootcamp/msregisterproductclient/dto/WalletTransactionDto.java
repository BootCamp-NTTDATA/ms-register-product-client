package com.bootcamp.msregisterproductclient.dto;

import com.bootcamp.msregisterproductclient.entity.Client;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WalletTransactionDto {
    private String idReceiver;
    private BigDecimal amount;
    private String idTransmitter;
    private Client clientTransmitter;
    private Client clientReceiver;
}
