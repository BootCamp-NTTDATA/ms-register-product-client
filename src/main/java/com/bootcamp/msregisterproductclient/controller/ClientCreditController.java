package com.bootcamp.msregisterproductclient.controller;

import com.bootcamp.msregisterproductclient.dto.ClientCreditDto;
import com.bootcamp.msregisterproductclient.resource.ClientCreditResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit/client")
public class ClientCreditController {

    @Autowired
    private ClientCreditResource clientCreditResource;

    @PostMapping
    public Mono<ClientCreditDto> create(@RequestBody ClientCreditDto clientCreditDto){
        return clientCreditResource.create(clientCreditDto);
    }

    @PutMapping
    public Mono<ClientCreditDto> update(@RequestBody ClientCreditDto clientCreditDto){
        return clientCreditResource.update(clientCreditDto);
    }

    @GetMapping
    public Flux<ClientCreditDto> findAll(){
        return clientCreditResource.findAll();
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestBody ClientCreditDto clientCreditDto){
        return clientCreditResource.delete(clientCreditDto);
    }

    @GetMapping("/numberDocument/{numberDocument}")
    public Mono<ClientCreditDto> findByClientNumberDocument(@PathVariable String numberDocument){
        return clientCreditResource.findByClientNumberDocument(numberDocument);
    }
}