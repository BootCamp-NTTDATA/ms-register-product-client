package com.bootcamp.msregisterproductclient.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TypeWallet {
    private String idWalletType;
    private BigDecimal maxAmountMovement;
    private String type;
}
