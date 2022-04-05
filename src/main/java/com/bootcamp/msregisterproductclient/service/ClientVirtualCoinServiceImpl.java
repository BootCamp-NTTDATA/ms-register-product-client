package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.ClientVirtualCoin;
import com.bootcamp.msregisterproductclient.repository.IClientVirtualCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientVirtualCoinServiceImpl implements IClientVirtualCoinService {
    @Autowired
    IClientVirtualCoinRepository clientVirtualCoinRepository;
    @Override
    public Mono<ClientVirtualCoin> save(ClientVirtualCoin clientVirtualCoin) {
        return clientVirtualCoinRepository.save(clientVirtualCoin);
    }
    @Override
    public Mono<Void> deleteById(String s) {
        return clientVirtualCoinRepository.deleteById(s);
    }
    @Override
    public Mono<ClientVirtualCoin> findById(String s) {
        return clientVirtualCoinRepository.findById(s);
    }
    @Override
    public Flux<ClientVirtualCoin> findAll() {
        return clientVirtualCoinRepository.findAll();
    }
}
