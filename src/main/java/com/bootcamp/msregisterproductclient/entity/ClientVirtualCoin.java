package com.bootcamp.msregisterproductclient.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientVirtualCoin extends BaseEntity{
    private String code;
    private Client client;
    private TypeVirtualCoin typeVirtualCoin;
}
