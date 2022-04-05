package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.ClientDebitCard;
import com.bootcamp.msregisterproductclient.repository.IClientDebitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientDebitCardServiceImpl implements IClientDebitCardService{
    @Autowired
    private IClientDebitCardRepository repository;
    @Override
    public Mono<ClientDebitCard> save(ClientDebitCard clientDebitCard) {
        return repository.save(clientDebitCard);
    }
    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
    @Override
    public Mono<ClientDebitCard> findById(String id) {
        return repository.findById(id);
    }
    @Override
    public Flux<ClientDebitCard> findAll() {
        return repository.findAll();
    }
    @Override
    public Mono<ClientDebitCard> findByDebitCardNumber(String debitCardNumber) {
        return repository.findByDebitCardNumber(debitCardNumber);
    }
    @Override
    public Mono<ClientDebitCard> findByClientDocument(String documentNumber) {
        return repository.findByClientNumberDocument(documentNumber);
    }
}

