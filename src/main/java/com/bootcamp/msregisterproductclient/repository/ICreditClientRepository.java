package com.bootcamp.msregisterproductclient.repository;

import com.bootcamp.msregisterproductclient.entity.ClientCredit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ICreditClientRepository extends ReactiveMongoRepository<ClientCredit, String> {
    Mono<ClientCredit> findByClientNumberDocument(String numberDocument);
}
