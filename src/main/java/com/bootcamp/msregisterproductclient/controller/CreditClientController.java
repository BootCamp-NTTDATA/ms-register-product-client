package com.bootcamp.msregisterproductclient.controller;

import com.bootcamp.msregisterproductclient.dto.CreditClientDto;
import com.bootcamp.msregisterproductclient.resource.CreditClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/register/credit/client")
public class CreditClientController {

    @Autowired
    private CreditClientResource creditClientResource;

    @PostMapping
    public Mono<CreditClientDto> create(@RequestBody CreditClientDto creditClientDto){
        return creditClientResource.create(creditClientDto);
    }
    @PutMapping
    public Mono<CreditClientDto> update(@RequestBody CreditClientDto creditClientDto){
        return creditClientResource.update(creditClientDto);
    }

    @GetMapping
    public Flux<CreditClientDto> findAll(){
        return creditClientResource.findAll();
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestBody CreditClientDto creditClientDto){
        return creditClientResource.delete(creditClientDto);
    }

    @GetMapping("/code/{code}/number/{number}/document/{typeDocument}")
    public Mono<CreditClientDto> findCreditByClient(@PathVariable  String code, @PathVariable String number, @PathVariable String typeDocument) {
        return creditClientResource.findCreditByClient(code, number, typeDocument);
    }
}
