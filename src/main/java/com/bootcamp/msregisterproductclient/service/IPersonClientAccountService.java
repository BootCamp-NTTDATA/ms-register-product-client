package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.PersonClientAccount;
import com.bootcamp.msregisterproductclient.util.ICrud;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IPersonClientAccountService extends ICrud<PersonClientAccount, String> {
     Flux<PersonClientAccount> findByPersonNumberDocument(String numberDocument);
}
