package com.bootcamp.msregisterproductclient.service;

import com.bootcamp.msregisterproductclient.entity.CompanyClientAccount;
import com.bootcamp.msregisterproductclient.entity.PersonClientAccount;
import com.bootcamp.msregisterproductclient.util.ICrud;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface ICompanyClientAccountService extends ICrud<CompanyClientAccount, String> {
    Mono<CompanyClientAccount> findByCompanyNumberDocument(String numberDocument);
}
