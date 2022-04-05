package com.bootcamp.msregisterproductclient.webclient;

import com.bootcamp.msregisterproductclient.webclient.dto.*;
import reactor.core.publisher.Mono;


public interface WCService {
    Mono<PersonClientDto> findPersonClientById(String id);
    Mono<CompanyClientDto> findCompanyClientById(String id);
    Mono<AccountDto> findAccountTypeById(String id);
    Mono<CreditCardDto> findCreditCardTypeById(String id);
    Mono<CreditDto> findCreditTypeById(String id);

}
