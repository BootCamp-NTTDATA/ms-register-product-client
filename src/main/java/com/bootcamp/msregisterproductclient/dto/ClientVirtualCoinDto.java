package com.bootcamp.msregisterproductclient.dto;

import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.TypeVirtualCoin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientVirtualCoinDto {
    private String id;
    private String code;
    private Client client;
    private TypeVirtualCoin typeVirtualCoin;
}
