package com.bootcamp.msregisterproductclient.controller;

import com.bootcamp.msregisterproductclient.dto.ClientCreditCardDto;
import com.bootcamp.msregisterproductclient.request.ClientCreditCardRequest;
import com.bootcamp.msregisterproductclient.resource.ClientCreditCardResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/register/credit-card/client")
public class ClientCreditCardController {
    @Autowired
    private ClientCreditCardResource clientCreditCardResource;

   @PostMapping
    public Mono<ClientCreditCardDto> create(@RequestBody ClientCreditCardRequest clientCreditCardRequest){
        return clientCreditCardResource.create(clientCreditCardRequest);
    }
    @PutMapping
    public Mono<ClientCreditCardDto> update(@RequestBody ClientCreditCardDto clientCreditCardDto){
        return clientCreditCardResource.update(clientCreditCardDto);
    }

    @GetMapping
    public Flux<ClientCreditCardDto> findAll(){
        return clientCreditCardResource.findAll();
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestBody ClientCreditCardDto clientCreditCardDto){
        return clientCreditCardResource.delete(clientCreditCardDto);
    }

    @GetMapping("/numberDocument/{numberDocument}")
    public Mono<ClientCreditCardDto> findByClientNumberDocument(@PathVariable String numberDocument){
        return clientCreditCardResource.findByClientNumberDocument(numberDocument);
    }
}
