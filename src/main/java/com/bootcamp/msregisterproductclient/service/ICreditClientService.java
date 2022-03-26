package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.CreditClient;
import com.bootcamp.msregisterproductclient.util.ICrud;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface ICreditClientService  extends ICrud<CreditClient, String> {
    Mono<CreditClient> findByClientNumberDocument(String numberDocument);
}
