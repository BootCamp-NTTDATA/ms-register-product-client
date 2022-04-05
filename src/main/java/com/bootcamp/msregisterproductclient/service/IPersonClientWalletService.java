package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.PersonClientWallet;
import com.bootcamp.msregisterproductclient.util.ICrud;
import reactor.core.publisher.Mono;

public interface IPersonClientWalletService extends ICrud<PersonClientWallet, String> {
    Mono<PersonClientWallet> findByPersonPhone(String phone);
}
