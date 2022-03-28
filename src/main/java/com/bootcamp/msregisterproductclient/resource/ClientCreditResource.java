package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.ClientCreditDto;
import com.bootcamp.msregisterproductclient.entity.ClientCredit;
import com.bootcamp.msregisterproductclient.service.IClientCreditService;
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
public class ClientCreditResource extends MapperUtil {

    @Autowired
    public IClientCreditService iClientCreditService;

    public Mono<ClientCreditDto> create(ClientCreditDto clientCreditDto) {

        ClientCredit clientCredit = map(clientCreditDto, ClientCredit.class);

        String typDocumentClient = clientCreditDto.getClient().getDocumentType();

        log.info(typDocumentClient);

        if (typDocumentClient.equals(TypeDocument.DNI.name()) || typDocumentClient.equals(TypeDocument.PASSPORT.name())){
            if (clientCredit.getTypeCredit().getAllowPerson()){
                clientCredit.setId(new ObjectId().toString());
                clientCredit.setCreatedAt(LocalDateTime.now());
                Mono<ClientCredit> entity = iClientCreditService.save(clientCredit);
                return  entity.map(x -> map(x, ClientCreditDto.class));
            }
        }
        if (typDocumentClient.equals(TypeDocument.RUC.name())) {
            if (clientCredit.getTypeCredit().getAllowCompany()) {
                clientCredit.setId(new ObjectId().toString());
                clientCredit.setCreatedAt(LocalDateTime.now());
                Mono<ClientCredit> entity = iClientCreditService.save(clientCredit);
                return entity.map(x -> map(x, ClientCreditDto.class));
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

    public Flux<ClientCreditDto> findAll() {
        return iClientCreditService.findAll().map(x->map(x, ClientCreditDto.class));
    }

    public Mono<ClientCreditDto> update(ClientCreditDto clientCreditDto) {
        return iClientCreditService.findById(clientCreditDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(p->{
                    ClientCredit clientCredit =  map(clientCreditDto, ClientCredit.class);
                    clientCredit.setUpdatedAt(LocalDateTime.now());
                    return iClientCreditService.save(clientCredit).map(y->map(y, ClientCreditDto.class));
                });
    }
    public Mono<ClientCreditDto> findById(String id) {
        return iClientCreditService.findById(id)
                .switchIfEmpty(Mono.error(new Exception()))
                .map(x-> map(x, ClientCreditDto.class));
    }

    public Mono<Void> delete(ClientCreditDto clientCreditDto) {
        return iClientCreditService.findById(clientCreditDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x-> iClientCreditService.deleteById(clientCreditDto.getId()));
    }

    public Mono<ClientCreditDto> findByClientNumberDocument(String numberDocument) {
        return iClientCreditService.findByClientNumberDocument(numberDocument)
                .map(x -> map(x, ClientCreditDto.class));
    }
}
