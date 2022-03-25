package com.bootcamp.msregisterproductclient.repository;

import com.bootcamp.msregisterproductclient.entity.CreditClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface ICreditClientRepository  extends ReactiveMongoRepository<CreditClient, String> {

    Mono<CreditClient>findByCodeAndClientNumberDocumentAndClientDocumentType(String code,String numberDocument, String documentType);

}
