package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.PersonClientWallet;
import com.bootcamp.msregisterproductclient.repository.IPersonClientWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonClientWalletServiceImpl implements IPersonClientWalletService{

    @Autowired
    IPersonClientWalletRepository iPersonClientWalletRepository;

    @Override
    public Mono<PersonClientWallet> save(PersonClientWallet personClientWallet) {
        return iPersonClientWalletRepository.save(personClientWallet);
    }

    @Override
    public Mono<Void> deleteById(String s) {
        return iPersonClientWalletRepository.deleteById(s);
    }

    @Override
    public Mono<PersonClientWallet> findById(String s) {
        return iPersonClientWalletRepository.findById(s);
    }

    @Override
    public Flux<PersonClientWallet> findAll() {
        return iPersonClientWalletRepository.findAll();
    }
}
