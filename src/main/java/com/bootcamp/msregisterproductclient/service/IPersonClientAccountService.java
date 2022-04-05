package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.PersonClientAccount;
import com.bootcamp.msregisterproductclient.util.ICrud;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonClientAccountService extends ICrud<PersonClientAccount, String> {
     Flux<PersonClientAccount> findByPersonNumberDocument(String numberDocument);
     Mono<PersonClientAccount>  findByPersonAccountNumber(String accountNumber);
     Mono<PersonClientAccount> findByPersonPhone(String numberDocument);
}
