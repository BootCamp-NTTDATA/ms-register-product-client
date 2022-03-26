package com.bootcamp.msregisterproductclient.webclient;

import com.bootcamp.msregisterproductclient.webclient.dto.PersonClientDto;
import reactor.core.publisher.Mono;

public interface ClientServiceInterface {
    Mono<PersonClientDto> findPersonClientbyId(String id);
}
