package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.PersonClientWalletDto;
import com.bootcamp.msregisterproductclient.entity.PersonClientWallet;
import com.bootcamp.msregisterproductclient.service.PersonClientWalletServiceImpl;
import com.bootcamp.msregisterproductclient.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonClientWalletResource extends MapperUtil {

    @Autowired
    private PersonClientWalletServiceImpl personClientWalletService;

    public Mono<PersonClientWalletDto> create(PersonClientWalletDto personClientWalletDto){
        PersonClientWallet personClientWallet = map(personClientWalletDto, PersonClientWallet.class);
        return  personClientWalletService.save(personClientWallet)
                .map(x -> map(x, PersonClientWalletDto.class));
    }

    public Mono<PersonClientWalletDto> update(PersonClientWalletDto personClientWalletDto) {
        PersonClientWallet personClientWallet = map(personClientWalletDto, PersonClientWallet.class);
        return personClientWalletService.findById(personClientWalletDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> personClientWalletService.save(personClientWallet).map(y -> map(y, PersonClientWalletDto.class)));
    }

    public Mono<Void> delete(PersonClientWalletDto personClientWalletDto) {
        return personClientWalletService.findById(personClientWalletDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> personClientWalletService.deleteById(personClientWalletDto.getId()));
    }

    public Flux<PersonClientWalletDto> findAll() {
        return personClientWalletService.findAll()
                .map(x -> map(x, PersonClientWalletDto.class));
    }

    public Mono<PersonClientWalletDto> findById(String id) {
        return personClientWalletService.findById(id).map(x -> map(x, PersonClientWalletDto.class));
    }
}
