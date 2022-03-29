package com.bootcamp.msregisterproductclient.controller;

import com.bootcamp.msregisterproductclient.dto.PersonClientAccountDto;
import com.bootcamp.msregisterproductclient.resource.PersonClientAccountResource;
import com.bootcamp.msregisterproductclient.webclient.dto.PersonClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/account/person")
public class PersonClientAccountController {

    @Autowired
    private PersonClientAccountResource personClientAccountResource;

    @PostMapping
    public Mono<PersonClientAccountDto> create(@RequestBody PersonClientAccountDto personClientAccountDto){
        return personClientAccountResource.create(personClientAccountDto);
    }

    @PutMapping
    public Mono<PersonClientAccountDto> update(@RequestBody PersonClientAccountDto personClientAccountDto){
        return personClientAccountResource.update(personClientAccountDto);
    }

    @GetMapping
    public Flux<PersonClientAccountDto> findAll(){
        return personClientAccountResource.findAll();
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestBody PersonClientAccountDto personClientAccountDto){
        return personClientAccountResource.delete(personClientAccountDto);
    }

    /*@GetMapping("/account/{account}/document/{document}/type/{type}")
    public Mono<PersonClientAccountDto> findByAccountNumberAndDocument(String account,String document,String type) {
        return personClientAccountResource.findByAccountNumberAndDocument(account, document, type);

    }*/
    @GetMapping("/numberDocument/{numberDocument}")
    public Mono<PersonClientAccountDto> findByPersonNumberDocument(@PathVariable String numberDocument){
        return personClientAccountResource.findByPersonNumberDocument(numberDocument);
    }
}
