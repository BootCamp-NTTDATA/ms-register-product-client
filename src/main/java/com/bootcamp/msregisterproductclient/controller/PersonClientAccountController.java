package com.bootcamp.msregisterproductclient.controller;

import com.bootcamp.msregisterproductclient.dto.PersonClientAccountDto;
import com.bootcamp.msregisterproductclient.request.PersonClientAccountRequest;
import com.bootcamp.msregisterproductclient.resource.PersonClientAccountResource;
import com.bootcamp.msregisterproductclient.webclient.dto.PersonClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/register/account/person")
public class PersonClientAccountController {

    @Autowired
    private PersonClientAccountResource personClientAccountResource;

    @PostMapping
    public Mono<PersonClientAccountDto> create(@RequestBody PersonClientAccountRequest personClientAccountRequest){
        return personClientAccountResource.create(personClientAccountRequest);
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

    @GetMapping("/numberDocument/{numberDocument}")
    public Mono<PersonClientAccountDto> findByPersonNumberDocument(@PathVariable String numberDocument){
        return personClientAccountResource.findByPersonNumberDocument(numberDocument);
    }

    @GetMapping("/find/client/{id}")
    public Mono<PersonClientDto> findPersonClientById(@PathVariable String id){
        return personClientAccountResource.findPersonClientById(id);
    }
}
