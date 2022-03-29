package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.PersonClientAccountDto;
import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.PersonClientAccount;
import com.bootcamp.msregisterproductclient.entity.TypeAccount;
import com.bootcamp.msregisterproductclient.request.PersonClientAccountRequest;
import com.bootcamp.msregisterproductclient.service.IPersonClientAccountService;
import com.bootcamp.msregisterproductclient.util.MapperUtil;
import com.bootcamp.msregisterproductclient.webclient.IPersonClientAccountWCServiceImpl;
import com.bootcamp.msregisterproductclient.webclient.dto.AccountDto;
import com.bootcamp.msregisterproductclient.webclient.dto.PersonClientDto;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
@Slf4j
@Service
public class PersonClientAccountResource extends MapperUtil {

    @Autowired
    private IPersonClientAccountService iPersonClientAccountService;

    @Autowired
    private IPersonClientAccountWCServiceImpl iClientService;

    public Mono<PersonClientAccountDto> create(PersonClientAccountRequest personClientAccountRequest){
        Mono<PersonClientDto> personClientDtoMono = iClientService.findPersonClientById(personClientAccountRequest.getIdPerson());
        Mono<AccountDto> accountDtoMono = iClientService.findAccountTypeById(personClientAccountRequest.getIdTypeAccount());
        Mono<PersonClientAccountDto> personClientAccountDto = new Mono<PersonClientAccountDto>() {
            @Override
            public void subscribe(CoreSubscriber<? super PersonClientAccountDto> coreSubscriber) {

            }
        };
        personClientAccountDto.map( personClientAccount -> {
            personClientAccount.setCode(personClientAccountRequest.getCode());
            personClientAccount.setAmount(personClientAccountRequest.getAmount());
            personClientAccount.setAccountNumber(personClientAccountRequest.getAccountNumber());
            personClientAccount.setOpeningDate(personClientAccountRequest.getOpeningDate());
            personClientAccount.setState(personClientAccountRequest.isState());
            personClientAccount.setPerson(map(personClientDtoMono, Client.class));
            personClientDtoMono.map(personClientDto -> {
               personClientAccount.getPerson().setIdClient(personClientDto.getId());
               personClientAccount.getPerson().setName(personClientDto.getName().concat(" ").concat(personClientDto.getLastName()));
               personClientAccount.getPerson().setNumberDocument(personClientDto.getNumberDocument());
               personClientAccount.getPerson().setDocumentType(personClientDto.getDocumentType());
               return personClientDto;
            });
            personClientAccount.setTypeAccount(map(accountDtoMono, TypeAccount.class));
            return personClientAccount;
        });

        PersonClientAccount personClientAccount = map(personClientAccountDto, PersonClientAccount.class);
        personClientAccount.setId(new ObjectId().toString());
        personClientAccount.setCreatedAt(LocalDateTime.now());

        String typDocumentClient = personClientAccount.getPerson().getDocumentType();

        if (typDocumentClient.equals(TypeDocument.DNI.name()) || typDocumentClient.equals(TypeDocument.PASSPORT.name())){
            if (personClientAccount.getTypeAccount().getAllowPerson()){
                Mono<PersonClientAccount> entity = iPersonClientAccountService.save(personClientAccount);
                return entity.map(x -> map(x, PersonClientAccountDto.class));
            }
        }
        return null;
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

   /* public Mono<PersonClientAccountDto> findByAccountNumberAndDocument(String accountNumber, String numberDocument, String documentType) {
        return iPersonClientAccountService.findByAccountNumberAndDocument(accountNumber, numberDocument, documentType)
                .switchIfEmpty(Mono.error(new Exception()))
                .map(x -> map(x, PersonClientAccountDto.class));
    }*/

    public Mono<PersonClientAccountDto> findByPersonNumberDocument(String numberDocument){
        return iPersonClientAccountService.findByPersonNumberDocument(numberDocument)
                .map(x -> map(x, PersonClientAccountDto.class));
    }

    public Mono<PersonClientDto> findPersonClientbyId(String id){
        Mono<PersonClientDto> personClientDtoMono = iClientService.findPersonClientById(id);
        log.info(Mono.just(personClientDtoMono).toString());
        return personClientDtoMono;
    }
}
