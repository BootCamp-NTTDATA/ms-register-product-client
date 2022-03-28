package com.bootcamp.msregisterproductclient.repository;

import com.bootcamp.msregisterproductclient.entity.PersonClientAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IPersonClientAccountRepository extends ReactiveMongoRepository<PersonClientAccount, String> {
    Mono<PersonClientAccount> findByAccountNumberAndClientNumberDocumentAndClientDocumentType(String accountNumber, String documentType,String documentNumber);

}
