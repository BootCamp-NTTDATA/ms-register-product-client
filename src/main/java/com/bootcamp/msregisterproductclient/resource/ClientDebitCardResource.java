package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.ClientDebitCardDto;
import com.bootcamp.msregisterproductclient.entity.ClientDebitCard;
import com.bootcamp.msregisterproductclient.service.IClientDebitCardService;
import com.bootcamp.msregisterproductclient.service.IPersonClientAccountService;
import com.bootcamp.msregisterproductclient.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientDebitCardResource extends MapperUtil {

    @Autowired
    private IClientDebitCardService iClientDebitCardService;

    @Autowired
    IPersonClientAccountService personClientAccountService;

    public Mono<ClientDebitCardDto> create(ClientDebitCardDto clientDebitCardDto){
        ClientDebitCard clientDebitCard = map(clientDebitCardDto, ClientDebitCard.class);
        return personClientAccountService.findByPersonPhone(clientDebitCard.getClient().getNumberDocument())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(account->{
                    clientDebitCard.getAccounts().add(account);
                    return iClientDebitCardService.save(clientDebitCard).map(x->map(x, ClientDebitCardDto.class));
                });
    }
    public Mono<ClientDebitCardDto> addAccountDebitCard(String debitCardNumber, String accountNumber){
        return iClientDebitCardService.findByDebitCardNumber(debitCardNumber)
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(clientDebitCard -> personClientAccountService.findByPersonAccountNumber(accountNumber)
                        .flatMap(account->{
                            clientDebitCard.getAccounts().add(account);
                            return  update(map(clientDebitCard,ClientDebitCardDto.class));
                        }));
    }

    public Mono<ClientDebitCardDto > update(ClientDebitCardDto  clientDebitCardDto) {
        ClientDebitCard clientDebitCard = map(clientDebitCardDto, ClientDebitCard.class);
        return iClientDebitCardService.findById(clientDebitCardDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> iClientDebitCardService.save(clientDebitCard).map(y -> map(y, ClientDebitCardDto .class)));
    }

    public Mono<Void> delete(ClientDebitCardDto clientDebitCardDto) {
        return iClientDebitCardService.findById(clientDebitCardDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> iClientDebitCardService.deleteById(clientDebitCardDto.getId()));
    }

    public Flux<ClientDebitCardDto> findAll() {
        return iClientDebitCardService.findAll()
                .map(x -> map(x, ClientDebitCardDto.class));
    }

    public Mono<ClientDebitCardDto> findById(String id) {
        return iClientDebitCardService.findById(id).map(x -> map(x, ClientDebitCardDto.class));
    }
}
