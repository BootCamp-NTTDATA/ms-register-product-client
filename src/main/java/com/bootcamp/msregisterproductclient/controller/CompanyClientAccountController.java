package com.bootcamp.msregisterproductclient.controller;

import com.bootcamp.msregisterproductclient.dto.CompanyClientAccountDto;
import com.bootcamp.msregisterproductclient.request.CompanyClientAccountRequest;
import com.bootcamp.msregisterproductclient.resource.CompanyClientAccountResource;
import com.bootcamp.msregisterproductclient.webclient.dto.CompanyClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/register/account/company")
public class CompanyClientAccountController {

    @Autowired
    private CompanyClientAccountResource companyClientAccountResource;

    @PostMapping
    public Mono<CompanyClientAccountDto> create(@RequestBody CompanyClientAccountRequest companyClientAccountRequest){
        return companyClientAccountResource.create(companyClientAccountRequest);
    }

    @PutMapping
    public Mono<CompanyClientAccountDto> update(@RequestBody CompanyClientAccountDto companyClientAccountDto){
        return companyClientAccountResource.update(companyClientAccountDto);
    }

    @GetMapping
    public Flux<CompanyClientAccountDto> findAll(){
        return companyClientAccountResource.findAll();
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestBody CompanyClientAccountDto companyClientAccountDto){
        return companyClientAccountResource.delete(companyClientAccountDto);
    }

    @GetMapping("/numberDocument/{numberDocument}")
    public Flux<CompanyClientAccountDto> findByCompanyNumberDocument(@PathVariable String numberDocument){
        return companyClientAccountResource.findByCompanyNumberDocument(numberDocument);
    }

    @GetMapping("/find/company/{id}")
    public Mono<CompanyClientDto> findCompanyClientById(@PathVariable String id){
        return companyClientAccountResource.findCompanyClientById(id);
    }
}
