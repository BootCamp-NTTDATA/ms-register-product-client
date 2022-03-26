package com.bootcamp.msregisterproductclient.repository;

import com.bootcamp.msregisterproductclient.entity.ClientCreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ICreditCardClientRepository extends ReactiveMongoRepository<ClientCreditCard, String> {
    Mono<ClientCreditCard> findByClientNumberDocument(String numberDocument);
}
