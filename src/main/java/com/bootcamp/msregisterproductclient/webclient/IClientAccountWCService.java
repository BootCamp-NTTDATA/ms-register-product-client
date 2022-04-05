package com.bootcamp.msregisterproductclient.webclient;

import com.bootcamp.msregisterproductclient.webclient.dto.AccountDto;
import com.bootcamp.msregisterproductclient.webclient.dto.CompanyClientDto;
import com.bootcamp.msregisterproductclient.webclient.dto.PersonClientDto;
import reactor.core.publisher.Mono;

public interface IClientAccountWCService {
    Mono<PersonClientDto> findPersonClientById(String id);
    Mono<CompanyClientDto> findCompanyClientById(String id);
    Mono<AccountDto> findAccountTypeById(String id);
}
