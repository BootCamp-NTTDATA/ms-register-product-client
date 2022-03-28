package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.ClientCreditCard;
import com.bootcamp.msregisterproductclient.repository.IClientCreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientCreditCardServiceImpl implements IClientCreditCardService {

    @Autowired
    IClientCreditCardRepository iClientCreditCardRepository;

    @Override
    public Mono<ClientCreditCard> save(ClientCreditCard clientCreditCard) {
        return iClientCreditCardRepository.save(clientCreditCard);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return iClientCreditCardRepository.deleteById(id);
    }

    @Override
    public Mono<ClientCreditCard> findById(String id) {
        return iClientCreditCardRepository.findById(id);
    }

    @Override
    public Flux<ClientCreditCard> findAll() {
        return iClientCreditCardRepository.findAll();
    }

    @Override
    public Mono<ClientCreditCard> findByClientNumberDocument(String numberDocument) {
        return iClientCreditCardRepository.findByClientNumberDocument(numberDocument);
    }
}
