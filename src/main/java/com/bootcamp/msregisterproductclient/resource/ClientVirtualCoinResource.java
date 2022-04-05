package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.ClientVirtualCoinDto;
import com.bootcamp.msregisterproductclient.entity.ClientVirtualCoin;
import com.bootcamp.msregisterproductclient.service.IClientVirtualCoinService;
import com.bootcamp.msregisterproductclient.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientVirtualCoinResource extends MapperUtil {
    @Autowired
    private IClientVirtualCoinService clientVirtualCoinService;

    public Mono<ClientVirtualCoinDto> create(ClientVirtualCoinDto clientVirtualCoinDto){
        ClientVirtualCoin clientVirtualCoin = map(clientVirtualCoinDto, ClientVirtualCoin.class);
        return  clientVirtualCoinService.save(clientVirtualCoin)
                .map(x -> map(x, ClientVirtualCoinDto.class));
    }
    public Mono<ClientVirtualCoinDto> update(ClientVirtualCoinDto clientVirtualCoinDto) {
        ClientVirtualCoin clientVirtualCoin = map(clientVirtualCoinDto, ClientVirtualCoin.class);
        return clientVirtualCoinService.findById(clientVirtualCoinDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> clientVirtualCoinService.save(clientVirtualCoin).map(y -> map(y, ClientVirtualCoinDto.class)));
    }
    public Mono<Void> delete(ClientVirtualCoinDto clientVirtualCoin) {
        return clientVirtualCoinService.findById(clientVirtualCoin.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> clientVirtualCoinService.deleteById(clientVirtualCoin.getId()));
    }

    public Flux<ClientVirtualCoinDto> findAll() {
        return clientVirtualCoinService.findAll()
                .map(x -> map(x, ClientVirtualCoinDto.class));
    }

    public Mono<ClientVirtualCoinDto> findById(String id) {
        return clientVirtualCoinService.findById(id).map(x -> map(x, ClientVirtualCoinDto.class));
    }

}
