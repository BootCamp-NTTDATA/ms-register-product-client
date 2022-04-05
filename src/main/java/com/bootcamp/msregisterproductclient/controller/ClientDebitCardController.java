package com.bootcamp.msregisterproductclient.controller;

import com.bootcamp.msregisterproductclient.dto.ClientDebitCardDto;
import com.bootcamp.msregisterproductclient.resource.ClientDebitCardResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/register/debit-card/client")
public class ClientDebitCardController {
    @Autowired
    private ClientDebitCardResource clientDebitCardResource;

    @PostMapping
    public Mono<ClientDebitCardDto> create(@RequestBody ClientDebitCardDto clientCreditDto){
        return clientDebitCardResource.create(clientCreditDto);
    }
    @PutMapping
    public Mono<ClientDebitCardDto> update(@RequestBody ClientDebitCardDto clientCreditDto){
        return clientDebitCardResource.update(clientCreditDto);
    }
    @GetMapping
    public Flux<ClientDebitCardDto> findAll(){
        return clientDebitCardResource.findAll();
    }
    @DeleteMapping
    public Mono<Void> delete(@RequestBody ClientDebitCardDto clientCreditDto){
        return clientDebitCardResource.delete(clientCreditDto);
    }
    @GetMapping("/number-card/{debitCardNumber}/number-account/{accountNumber}")
    public Mono<ClientDebitCardDto> addAccountDebitCard(@PathVariable String debitCardNumber, @PathVariable String accountNumber){
        return clientDebitCardResource.addAccountDebitCard(debitCardNumber,accountNumber);
    }
}
