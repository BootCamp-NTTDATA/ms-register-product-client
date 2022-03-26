package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.ClientCredit;
import com.bootcamp.msregisterproductclient.repository.IClientCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientCreditServiceImpl implements IClientCreditService {

    @Autowired
    IClientCreditRepository iClientCreditRepository;

    @Override
    public Mono<ClientCredit> save(ClientCredit clientCredit) {
        return iClientCreditRepository.save(clientCredit);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return iClientCreditRepository.deleteById(id);
    }

    @Override
    public Mono<ClientCredit> findById(String id) {
        return iClientCreditRepository.findById(id);
    }

    @Override
    public Flux<ClientCredit> findAll() {
        return iClientCreditRepository.findAll();
    }

    @Override
    public Mono<ClientCredit> findByClientNumberDocument(String numberDocument) {
        return iClientCreditRepository.findByClientNumberDocument(numberDocument);
    }
}
