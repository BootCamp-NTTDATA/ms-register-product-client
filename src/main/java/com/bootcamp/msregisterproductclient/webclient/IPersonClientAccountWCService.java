package com.bootcamp.msregisterproductclient.webclient;

import com.bootcamp.msregisterproductclient.webclient.dto.AccountDto;
import com.bootcamp.msregisterproductclient.webclient.dto.PersonClientDto;
import reactor.core.publisher.Mono;

public interface IPersonClientAccountWCService {
    Mono<PersonClientDto> findPersonClientById(String id);
    Mono<AccountDto> findAccountTypeById(String id);
}
