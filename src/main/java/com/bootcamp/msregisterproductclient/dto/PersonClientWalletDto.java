package com.bootcamp.msregisterproductclient.dto;

import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.TypeWallet;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonClientWalletDto {
    private String id;
    private String code;
    private BigDecimal amount;
    private LocalDateTime openingDate;
    private Client person;
    private TypeWallet typeWallet;
    private boolean state;
}
