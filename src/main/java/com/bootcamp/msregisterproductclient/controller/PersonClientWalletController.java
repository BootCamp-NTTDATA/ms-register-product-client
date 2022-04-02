package com.bootcamp.msregisterproductclient.controller;

import com.bootcamp.msregisterproductclient.dto.PersonClientWalletDto;
import com.bootcamp.msregisterproductclient.resource.PersonClientWalletResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/register/wallet/person")
public class PersonClientWalletController {

    @Autowired
    private PersonClientWalletResource personClientWalletResource;

    @PostMapping
    public Mono<PersonClientWalletDto> create(@RequestBody PersonClientWalletDto personClientWalletDto){
        return personClientWalletResource.create(personClientWalletDto);
    }

    @PutMapping
    public Mono<PersonClientWalletDto> update(@RequestBody PersonClientWalletDto personClientWalletDto){
        return personClientWalletResource.update(personClientWalletDto);
    }

    @GetMapping("/{id}")
    public Mono<PersonClientWalletDto> findById(@PathVariable String id){
        return personClientWalletResource.findById(id);
    }

    @GetMapping
    public Flux<PersonClientWalletDto> findAll(){
        return personClientWalletResource.findAll();
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestBody PersonClientWalletDto personClientWalletDto){
        return personClientWalletResource.delete(personClientWalletDto);
    }
}
