package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.ClientCreditCard;
import com.bootcamp.msregisterproductclient.util.ICrud;
import reactor.core.publisher.Mono;

public interface IClientCreditCardService extends ICrud<ClientCreditCard, String> {
    Mono<ClientCreditCard> findByClientNumberDocument(String numberDocument);
}
