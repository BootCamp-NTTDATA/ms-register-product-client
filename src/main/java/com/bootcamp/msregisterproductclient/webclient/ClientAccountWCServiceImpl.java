package com.bootcamp.msregisterproductclient.webclient;

import com.bootcamp.msregisterproductclient.exception.ClientNotFoundException;
import com.bootcamp.msregisterproductclient.exception.ProductNotFoundException;
import com.bootcamp.msregisterproductclient.webclient.dto.AccountDto;
import com.bootcamp.msregisterproductclient.webclient.dto.CompanyClientDto;
import com.bootcamp.msregisterproductclient.webclient.dto.CreditCardDto;
import com.bootcamp.msregisterproductclient.webclient.dto.PersonClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class ClientAccountWCServiceImpl implements IClientAccountWCService {

    private static final String URL_GATEWAY = "http://localhost:8080";

    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Mono<PersonClientDto> findPersonClientById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/client/person/".concat(id))
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
    public Mono<CompanyClientDto> findCompanyClientById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/client/company/".concat(id))
                .retrieve()
                .bodyToMono(CompanyClientDto.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ClientNotFoundException());
                    }
                    return Mono.error(new ClientNotFoundException());
                });
    }

    @Override
    public Mono<AccountDto> findAccountTypeById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/product/account/".concat(id))
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

    @Override
    public Mono<CreditCardDto> findCreditCardTypeById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/product/credit-card/".concat(id))
                .retrieve()
                .bodyToMono(CreditCardDto.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ProductNotFoundException());
                    }
                    return Mono.error(error);
                });
    }
}
