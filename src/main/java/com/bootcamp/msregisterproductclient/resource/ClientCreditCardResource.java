package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.ClientCreditCardDto;
import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.ClientCreditCard;
import com.bootcamp.msregisterproductclient.entity.TypeCreditCard;
import com.bootcamp.msregisterproductclient.request.ClientCreditCardRequest;
import com.bootcamp.msregisterproductclient.service.IClientCreditCardService;
import com.bootcamp.msregisterproductclient.util.MapperUtil;
import com.bootcamp.msregisterproductclient.webclient.WCServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
@Slf4j
@Service
public class ClientCreditCardResource extends MapperUtil {

    @Autowired
    private IClientCreditCardService iClientCreditCardService;

    @Autowired
    private WCServiceImpl clientWCService;

    public Mono<ClientCreditCardDto> create(ClientCreditCardRequest creditCardRequest) {
        return clientWCService.findPersonClientById(creditCardRequest.getIdClient())
            .switchIfEmpty(Mono.error(new Exception()))
            .flatMap( clientDtoMono ->
                    clientWCService.findCreditCardTypeById(creditCardRequest.getIdTypeCreditCard())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap( creditCardDto -> {
                        ClientCreditCard clientCreditCard = new ClientCreditCard();
                        clientCreditCard.setId(new ObjectId().toString());
                        clientCreditCard.setCreatedAt(LocalDateTime.now());
                        clientCreditCard.setCode(creditCardRequest.getCode());
                        clientCreditCard.setCreditCardNumber(creditCardRequest.getCreditCardNumber());
                        clientCreditCard.setLimitAmount(creditCardRequest.getLimitAmount());
                        clientCreditCard.setUsedAmount(creditCardRequest.getUsedAmount());
                        clientCreditCard.setFees(creditCardRequest.getFees());
                        clientCreditCard.setFeesPaid(creditCardRequest.getFeesPaid());
                        clientCreditCard.setCutoffDate(creditCardRequest.getCutoffDate());
                        clientCreditCard.setLimitDate(creditCardRequest.getLimitDate());
                        clientCreditCard.setOpeningDate(LocalDateTime.now());
                        clientCreditCard.setDeliveryDate(creditCardRequest.getDeliveryDate());
                        clientCreditCard.setClient(map(clientDtoMono, Client.class));
                        clientCreditCard.setTypeCreditCard(map(creditCardDto, TypeCreditCard.class));
                        clientCreditCard.setState(creditCardRequest.isState());

                        String typeDocumentClient = clientCreditCard.getClient().getDocumentType();

                        if (typeDocumentClient.equals(TypeDocument.DNI.name()) || typeDocumentClient.equals(TypeDocument.PASSPORT.name())){
                            if (clientCreditCard.getTypeCreditCard().getAllowPerson()){
                                clientCreditCard.setId(new ObjectId().toString());
                                clientCreditCard.setCreatedAt(LocalDateTime.now());
                                Mono<ClientCreditCard> entity = iClientCreditCardService.save(clientCreditCard);
                                return  entity.map(x -> map(x, ClientCreditCardDto.class));
                            }
                        }
                        if (typeDocumentClient.equals(TypeDocument.RUC.name())) {
                            if (clientCreditCard.getTypeCreditCard().getAllowCompany()) {
                                clientCreditCard.setId(new ObjectId().toString());
                                clientCreditCard.setCreatedAt(LocalDateTime.now());
                                Mono<ClientCreditCard> entity = iClientCreditCardService.save(clientCreditCard);
                                return  entity.map(x -> map(x, ClientCreditCardDto.class));
                            }
                        }
                        return null;
                    })
            );
    }

    public Flux<ClientCreditCardDto> findAll() {
        return iClientCreditCardService.findAll().map(x->map(x, ClientCreditCardDto.class));
    }

    public Mono<ClientCreditCardDto> update(ClientCreditCardDto clientCreditCardDto) {
        return iClientCreditCardService.findById(clientCreditCardDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(p->{
                    ClientCreditCard clientCreditCard =  map(clientCreditCardDto, ClientCreditCard.class);
                    clientCreditCard.setUpdatedAt(LocalDateTime.now());
                    return iClientCreditCardService.save(clientCreditCard).map(y->map(y, ClientCreditCardDto.class));
                });
    }
    public Mono<ClientCreditCardDto> findById(String id) {
        return iClientCreditCardService.findById(id)
                .switchIfEmpty(Mono.error(new Exception()))
                .map(x-> map(x, ClientCreditCardDto.class));
    }

    public Mono<Void> delete(ClientCreditCardDto clientCreditCardDto) {
        return iClientCreditCardService.findById(clientCreditCardDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x-> iClientCreditCardService.deleteById(clientCreditCardDto.getId()));
    }

    public Mono<ClientCreditCardDto> findByClientNumberDocument(String numberDocument){
        return iClientCreditCardService.findByClientNumberDocument(numberDocument)
                .map(x -> map(x, ClientCreditCardDto.class));
    }
}
