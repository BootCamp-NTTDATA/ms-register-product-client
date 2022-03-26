package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.CreditClientDto;
import com.bootcamp.msregisterproductclient.entity.ClientCredit;
import com.bootcamp.msregisterproductclient.service.ICreditClientService;
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
public class CreditClientResource extends MapperUtil {

    @Autowired
    public ICreditClientService iCreditClientService;

    public Mono<CreditClientDto> create(CreditClientDto creditClientDto) {

        ClientCredit clientCredit = map(creditClientDto, ClientCredit.class);

        String typDocumentClient = creditClientDto.getClient().getDocumentType();

        log.info(typDocumentClient);

        if (typDocumentClient.equals(TypeDocument.DNI.name()) || typDocumentClient.equals(TypeDocument.PASSPORT.name())){
            if (clientCredit.getTypeCredit().getAllowPerson()){
                clientCredit.setId(new ObjectId().toString());
                clientCredit.setCreatedAt(LocalDateTime.now());
                Mono<ClientCredit> entity = iCreditClientService.save(clientCredit);
                return  entity.map(x -> map(x, CreditClientDto.class));
            }
        }
        if (typDocumentClient.equals(TypeDocument.RUC.name())) {
            if (clientCredit.getTypeCredit().getAllowCompany()) {
                clientCredit.setId(new ObjectId().toString());
                clientCredit.setCreatedAt(LocalDateTime.now());
                Mono<ClientCredit> entity = iCreditClientService.save(clientCredit);
                return entity.map(x -> map(x, CreditClientDto.class));
            }
        }
        return null;
        /*
        else{
            creditClient.setId(new ObjectId().toString());
            creditClient.setCreatedAt(LocalDateTime.now());
            Flux<CreditClientDto> temp = iCreditClientService.findAll()
                    .filter(p->p.isState())
                    .filter(t->t.getClient().getNumberDocument().equals(creditClientDto.getClient().getNumberDocument()))
                    .switchIfEmpty(iCreditClientService.save(creditClient))
                    .map(x->creditClientDto);
            return Mono.from(temp);
        }
        */
    }

    public Flux<CreditClientDto> findAll() {
        return iCreditClientService.findAll().map(x->map(x,CreditClientDto.class));
    }

    public Mono<CreditClientDto> update(CreditClientDto creditClientDto) {
        return iCreditClientService.findById(creditClientDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(p->{
                    ClientCredit clientCredit =  map(creditClientDto, ClientCredit.class);
                    clientCredit.setUpdatedAt(LocalDateTime.now());
                    return iCreditClientService.save(clientCredit).map(y->map(y,CreditClientDto.class));
                });
    }
    public Mono<CreditClientDto> findById(String id) {
        return iCreditClientService.findById(id)
                .switchIfEmpty(Mono.error(new Exception()))
                .map(x-> map(x,CreditClientDto.class));
    }

    public Mono<Void> delete(CreditClientDto creditClientDto) {
        return iCreditClientService.findById(creditClientDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x-> iCreditClientService.deleteById(creditClientDto.getId()));
    }

    public Mono<CreditClientDto> findByClientNumberDocument(String numberDocument) {
        return iCreditClientService.findByClientNumberDocument(numberDocument)
                .map(x -> map(x, CreditClientDto.class));
    }
}
