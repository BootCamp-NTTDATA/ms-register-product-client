package com.bootcamp.msregisterproductclient.webclient;

import com.bootcamp.msregisterproductclient.exception.ClientNotFoundException;
import com.bootcamp.msregisterproductclient.exception.ProductNotFoundException;
import com.bootcamp.msregisterproductclient.webclient.dto.AccountDto;
import com.bootcamp.msregisterproductclient.webclient.dto.PersonClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class IPersonClientAccountWCServiceImpl implements IPersonClientAccountWCService {

    private static final String URL_CLIENT = "http://localhost:8083";
    private static final String URL_PRODUCT = "http://localhost:8081";

    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Mono<PersonClientDto> findPersonClientById(String id) {
        return webClient.baseUrl(URL_CLIENT).build().get().uri("/api/client/person/".concat(id))
                .retrieve()
                .bodyToMono(PersonClientDto.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ClientNotFoundException());
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<AccountDto> findAccountTypeById(String id) {
        return webClient.baseUrl(URL_PRODUCT).build().get().uri("/api/product/account/".concat(id))
                .retrieve()
                .bodyToMono(AccountDto.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ProductNotFoundException());
                    }
                    return  Mono.error(error);
                });
    }
}
