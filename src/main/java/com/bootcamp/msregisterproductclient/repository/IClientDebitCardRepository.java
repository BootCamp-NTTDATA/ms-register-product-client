package com.bootcamp.msregisterproductclient.repository;

import com.bootcamp.msregisterproductclient.entity.ClientDebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IClientDebitCardRepository extends ReactiveMongoRepository<ClientDebitCard,String> {
    Mono<ClientDebitCard> findByDebitCardNumber(String debitCardNumber);
    Mono<ClientDebitCard> findByClientNumberDocument(String numberDocument);
}
