package com.bootcamp.msregisterproductclient.repository;

import com.bootcamp.msregisterproductclient.entity.PersonClientWallet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IPersonClientWalletRepository extends ReactiveMongoRepository<PersonClientWallet, String> {
    Mono<PersonClientWallet> findByPersonPhone(String phone);
}
