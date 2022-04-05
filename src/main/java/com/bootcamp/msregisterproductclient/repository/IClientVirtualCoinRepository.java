package com.bootcamp.msregisterproductclient.repository;

import com.bootcamp.msregisterproductclient.entity.ClientVirtualCoin;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IClientVirtualCoinRepository extends ReactiveMongoRepository<ClientVirtualCoin, String> {
}
