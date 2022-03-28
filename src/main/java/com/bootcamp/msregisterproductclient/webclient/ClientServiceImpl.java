package com.bootcamp.msregisterproductclient.webclient;

import com.bootcamp.msregisterproductclient.exception.ClientNotFoundException;
import com.bootcamp.msregisterproductclient.webclient.dto.PersonClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientServiceInterface{

    private static final String BASE_URL = "lb://ms-clients";

    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Mono<PersonClientDto> findPersonClientbyId(String id) {
        return webClient.baseUrl(BASE_URL).build().get().uri("/api/client/person/".concat(id))
                .retrieve()
                .bodyToMono(PersonClientDto.class)
                .onErrorResume(x -> Mono.error(new Exception()));
    }
}
