package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.PersonClientWalletDto;
import com.bootcamp.msregisterproductclient.dto.WalletTransactionDto;
import com.bootcamp.msregisterproductclient.entity.PersonClientWallet;
import com.bootcamp.msregisterproductclient.service.IPersonClientWalletService;
import com.bootcamp.msregisterproductclient.service.PersonClientWalletServiceImpl;
import com.bootcamp.msregisterproductclient.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class PersonClientWalletResource extends MapperUtil {
    @Autowired
    private IPersonClientWalletService personClientWalletService;

    @Autowired
    private PersonClientAccountResource personClientAccountResource;

    @Autowired
    MessageResource messageResource;

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
    public Mono<PersonClientWalletDto> findByClientPhone(String phone){
        return personClientWalletService.findByPersonPhone(phone).map(x->map(x, PersonClientWalletDto.class));
    }
    public Mono<WalletTransactionDto>  walletTransaction(String idTransmitter, String phoneReceiver, BigDecimal amount) {
        WalletTransactionDto temp = new WalletTransactionDto();
        return findByClientPhone(phoneReceiver)
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(receiver-> findById(idTransmitter).map(transmitter->{
                    temp.setIdReceiver(receiver.getId());
                    temp.setAmount(amount);
                    temp.setIdTransmitter(idTransmitter);
                    temp.setClientTransmitter(transmitter.getPerson());
                    temp.setClientReceiver(receiver.getPerson());
                    messageResource.sendMessage(temp,"topic_ms_wallet");
                    return temp;
                }));
    }

    public Mono<WalletTransactionDto> rechargeWalletAccount(String idTransmitter, String accountNumber, BigDecimal amount){
        WalletTransactionDto temp = new WalletTransactionDto();
        return personClientAccountResource.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(account -> findById(idTransmitter).map(transmitter ->{
                    temp.setIdReceiver(transmitter.getId());
                    temp.setIdTransmitter(account.getAccountNumber());
                    temp.setAmount(amount);
                    temp.setClientTransmitter(account.getPerson());
                    temp.setClientReceiver(transmitter.getPerson());
                    messageResource.sendMessage(temp,"ms_wallet_recharge");
                    return temp;
                }));
    }
}
