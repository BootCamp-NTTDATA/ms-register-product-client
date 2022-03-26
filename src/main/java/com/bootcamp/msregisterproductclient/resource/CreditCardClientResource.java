package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.CreditCardClientDto;
import com.bootcamp.msregisterproductclient.entity.ClientCreditCard;
import com.bootcamp.msregisterproductclient.service.ICreditCardClientService;
import com.bootcamp.msregisterproductclient.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
@Slf4j
@Service
public class CreditCardClientResource extends MapperUtil {

    @Autowired
    private ICreditCardClientService iCreditCardClientService;

    public Mono<CreditCardClientDto> create(CreditCardClientDto creditCardClientDto) {

        ClientCreditCard clientCreditCard = map(creditCardClientDto, ClientCreditCard.class);

        String typeDocumentClient = creditCardClientDto.getClient().getDocumentType();

        log.info(typeDocumentClient);

        if (typeDocumentClient.equals(TypeDocument.DNI.name()) || typeDocumentClient.equals(TypeDocument.PASSPORT.name())){
            if (clientCreditCard.getTypeCreditCard().getAllowPerson()){
                clientCreditCard.setId(new ObjectId().toString());
                clientCreditCard.setCreatedAt(LocalDateTime.now());
                Mono<ClientCreditCard> entity = iCreditCardClientService.save(clientCreditCard);
                return  entity.map(x -> map(x, CreditCardClientDto.class));
            }
        }
        if (typeDocumentClient.equals(TypeDocument.RUC.name())) {
            if (clientCreditCard.getTypeCreditCard().getAllowCompany()) {
                clientCreditCard.setId(new ObjectId().toString());
                clientCreditCard.setCreatedAt(LocalDateTime.now());
                Mono<ClientCreditCard> entity = iCreditCardClientService.save(clientCreditCard);
                return  entity.map(x -> map(x, CreditCardClientDto.class));
            }
        }
        return null;
    }

    public Flux<CreditCardClientDto> findAll() {
        return iCreditCardClientService.findAll().map(x->map(x,CreditCardClientDto.class));
    }

    public Mono<CreditCardClientDto> update(CreditCardClientDto creditCardClientDto) {
        return iCreditCardClientService.findById(creditCardClientDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(p->{
                    ClientCreditCard clientCreditCard =  map(creditCardClientDto, ClientCreditCard.class);
                    clientCreditCard.setUpdatedAt(LocalDateTime.now());
                    return iCreditCardClientService.save(clientCreditCard).map(y->map(y,CreditCardClientDto.class));
                });
    }
    public Mono<CreditCardClientDto> findById(String id) {
        return iCreditCardClientService.findById(id)
                .switchIfEmpty(Mono.error(new Exception()))
                .map(x-> map(x,CreditCardClientDto.class));
    }

    public Mono<Void> delete(CreditCardClientDto creditCardClientDto) {
        return iCreditCardClientService.findById(creditCardClientDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x-> iCreditCardClientService.deleteById(creditCardClientDto.getId()));
    }

    public Mono<CreditCardClientDto> findByClientNumberDocument(String numberDocument){
        return iCreditCardClientService.findByClientNumberDocument(numberDocument)
                .map(x -> map(x, CreditCardClientDto.class));
    }
}
