package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.PersonClientWallet;
import com.bootcamp.msregisterproductclient.util.ICrud;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonClientWalletService extends ICrud<PersonClientWallet, String> {
}
