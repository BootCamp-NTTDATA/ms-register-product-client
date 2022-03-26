package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.ClientCredit;
import com.bootcamp.msregisterproductclient.repository.ICreditClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditClientServiceImpl implements ICreditClientService{

    @Autowired
    ICreditClientRepository iCreditClientRepository;

    @Override
    public Mono<ClientCredit> save(ClientCredit clientCredit) {
        return iCreditClientRepository.save(clientCredit);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return iCreditClientRepository.deleteById(id);
    }

    @Override
    public Mono<ClientCredit> findById(String id) {
        return iCreditClientRepository.findById(id);
    }

    @Override
    public Flux<ClientCredit> findAll() {
        return iCreditClientRepository.findAll();
    }

    @Override
    public Mono<ClientCredit> findByClientNumberDocument(String numberDocument) {
        return iCreditClientRepository.findByClientNumberDocument(numberDocument);
    }
}
