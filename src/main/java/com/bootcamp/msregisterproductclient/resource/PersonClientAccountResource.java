package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.PersonClientAccountDto;
import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.PersonClientAccount;
import com.bootcamp.msregisterproductclient.entity.TypeAccount;
import com.bootcamp.msregisterproductclient.request.PersonClientAccountRequest;
import com.bootcamp.msregisterproductclient.service.IPersonClientAccountService;
import com.bootcamp.msregisterproductclient.util.MapperUtil;
import com.bootcamp.msregisterproductclient.webclient.ClientAccountWCServiceImpl;
import com.bootcamp.msregisterproductclient.webclient.dto.PersonClientDto;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
@Slf4j
@Service
public class PersonClientAccountResource extends MapperUtil {

    @Autowired
    private IPersonClientAccountService iPersonClientAccountService;

    @Autowired
    private ClientAccountWCServiceImpl clientWCService;

    public Mono<PersonClientDto> findPersonClientById(String id){
        Mono<PersonClientDto> personClientDtoMono = clientWCService.findPersonClientById(id);
        log.info(Mono.just(personClientDtoMono).toString());
        return personClientDtoMono;
    }

    public Mono<PersonClientAccountDto> create(PersonClientAccountRequest personClientAccountRequest){
        return clientWCService.findPersonClientById(personClientAccountRequest.getIdPerson())
            .switchIfEmpty(Mono.error(new Exception()))
            .flatMap( personClientDtoMono ->
                    clientWCService.findAccountTypeById(personClientAccountRequest.getIdTypeAccount())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap( accountDtoMono -> {
                        PersonClientAccount personClientAccount = new PersonClientAccount();
                        personClientAccount.setId(new ObjectId().toString());
                        personClientAccount.setCreatedAt(LocalDateTime.now());
                        personClientAccount.setCode(personClientAccountRequest.getCode());
                        personClientAccount.setAmount(personClientAccountRequest.getAmount());
                        personClientAccount.setAccountNumber(personClientAccountRequest.getAccountNumber());
                        personClientAccount.setOpeningDate(personClientAccountRequest.getOpeningDate());
                        personClientAccount.setState(personClientAccountRequest.isState());
                        personClientAccount.setPerson(map(personClientDtoMono, Client.class));
                        personClientAccount.setTypeAccount(map(accountDtoMono, TypeAccount.class));

                        String typDocumentClient = personClientAccount.getPerson().getDocumentType();
                        if (typDocumentClient.equals(TypeDocument.DNI.name()) || typDocumentClient.equals(TypeDocument.PASSPORT.name())){
                            if (personClientAccount.getTypeAccount().getAllowPerson()){
                                Mono<PersonClientAccount> entity = iPersonClientAccountService.save(personClientAccount);
                                return entity.map(x -> map(x, PersonClientAccountDto.class));
                            }
                        }
                        return null;
                    })
            );
    }

    public Flux<PersonClientAccountDto> findAll(){
        return iPersonClientAccountService.findAll().map(x->map(x,PersonClientAccountDto.class));
    }

    public Mono<PersonClientAccountDto> update(PersonClientAccountDto personClientAccountDto){
        return iPersonClientAccountService.findById(personClientAccountDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(p->{
                    PersonClientAccount personClientAccount =  map(personClientAccountDto,PersonClientAccount.class);
                    personClientAccount.setUpdatedAt(LocalDateTime.now());
                    return iPersonClientAccountService.save(personClientAccount).map(y->map(y,PersonClientAccountDto.class));
                });
    }

    public Mono<PersonClientAccountDto> findById(String id){
        return iPersonClientAccountService.findById(id)
                .switchIfEmpty(Mono.error(new Exception()))
                .map(x-> map(x,PersonClientAccountDto.class));
    }

    public Mono<Void> delete(PersonClientAccountDto personClientAccountDto){
        return iPersonClientAccountService.findById(personClientAccountDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x-> iPersonClientAccountService.deleteById(personClientAccountDto.getId()));
    }

    public Mono<PersonClientAccountDto> findByPersonNumberDocument(String numberDocument){
        return iPersonClientAccountService.findByPersonNumberDocument(numberDocument)
                .map(x -> map(x, PersonClientAccountDto.class));
    }
}
