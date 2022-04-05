package com.bootcamp.msregisterproductclient.controller;

import com.bootcamp.msregisterproductclient.dto.ClientVirtualCoinDto;
import com.bootcamp.msregisterproductclient.resource.ClientVirtualCoinResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/register/virtual-coin/client")
public class ClientVirtualCoinController {
    @Autowired
    private ClientVirtualCoinResource clientVirtualCoinResource;

    @PostMapping
    public Mono<ClientVirtualCoinDto> create(@RequestBody ClientVirtualCoinDto clientVirtualCoinDto){
        return clientVirtualCoinResource.create(clientVirtualCoinDto);
    }
    @PutMapping
    public Mono<ClientVirtualCoinDto> update(@RequestBody ClientVirtualCoinDto clientVirtualCoinDto){
        return clientVirtualCoinResource.update(clientVirtualCoinDto);
    }
    @GetMapping
    public Flux<ClientVirtualCoinDto> findAll(){
        return clientVirtualCoinResource.findAll();
    }
    @DeleteMapping
    public Mono<Void> delete(@RequestBody ClientVirtualCoinDto clientVirtualCoinDto){
        return clientVirtualCoinResource.delete(clientVirtualCoinDto);
    }
}
