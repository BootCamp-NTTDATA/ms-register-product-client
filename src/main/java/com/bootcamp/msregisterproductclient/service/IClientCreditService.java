package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.ClientCredit;
import com.bootcamp.msregisterproductclient.util.ICrud;
import reactor.core.publisher.Mono;

public interface IClientCreditService extends ICrud<ClientCredit, String> {
    Mono<ClientCredit> findByClientNumberDocument(String numberDocument);
}
