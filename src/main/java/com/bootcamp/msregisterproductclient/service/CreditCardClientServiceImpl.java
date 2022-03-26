package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.ClientCreditCard;
import com.bootcamp.msregisterproductclient.repository.ICreditCardClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardClientServiceImpl implements ICreditCardClientService{

    @Autowired
    ICreditCardClientRepository iCreditCardClientRepository;

    @Override
    public Mono<ClientCreditCard> save(ClientCreditCard clientCreditCard) {
        return iCreditCardClientRepository.save(clientCreditCard);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return iCreditCardClientRepository.deleteById(id);
    }

    @Override
    public Mono<ClientCreditCard> findById(String id) {
        return iCreditCardClientRepository.findById(id);
    }

    @Override
    public Flux<ClientCreditCard> findAll() {
        return iCreditCardClientRepository.findAll();
    }

    @Override
    public Mono<ClientCreditCard> findByClientNumberDocument(String numberDocument) {
        return iCreditCardClientRepository.findByClientNumberDocument(numberDocument);
    }
}
