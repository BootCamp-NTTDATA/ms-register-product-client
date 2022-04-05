package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.ClientDebitCard;
import com.bootcamp.msregisterproductclient.util.ICrud;
import reactor.core.publisher.Mono;

public interface IClientDebitCardService  extends ICrud<ClientDebitCard, String> {
    Mono<ClientDebitCard> findByDebitCardNumber(String debitCardNumber);
    Mono<ClientDebitCard> findByClientDocument(String documentNumber);
}
