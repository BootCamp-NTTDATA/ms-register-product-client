package com.bootcamp.msregisterproductclient.repository;

import com.bootcamp.msregisterproductclient.entity.PersonClientWallet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonClientWalletRepository extends ReactiveMongoRepository<PersonClientWallet, String> {
}
