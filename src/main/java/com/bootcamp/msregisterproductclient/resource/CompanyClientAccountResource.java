package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.CompanyClientAccountDto;
import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.CompanyClientAccount;
import com.bootcamp.msregisterproductclient.entity.TypeAccount;
import com.bootcamp.msregisterproductclient.request.CompanyClientAccountRequest;
import com.bootcamp.msregisterproductclient.request.HolderRequest;
import com.bootcamp.msregisterproductclient.request.SignerRequest;
import com.bootcamp.msregisterproductclient.util.MapperUtil;
import com.bootcamp.msregisterproductclient.service.ICompanyClientAccountService;
import com.bootcamp.msregisterproductclient.webclient.WCServiceImpl;
import com.bootcamp.msregisterproductclient.webclient.dto.CompanyClientDto;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Service
public class CompanyClientAccountResource extends MapperUtil {

    @Autowired
    private ICompanyClientAccountService iCompanyClientAccountService;

    @Autowired
    private WCServiceImpl clientWCService;

    public Mono<CompanyClientDto> findCompanyClientById(String id){
        Mono<CompanyClientDto> companyClientDtoMono = clientWCService.findCompanyClientById(id);
        return companyClientDtoMono;
    }
    public Mono<CompanyClientAccountDto> create(CompanyClientAccountRequest companyClientAccountRequest) {
        return clientWCService.findCompanyClientById(companyClientAccountRequest.getIdCompany())
            .switchIfEmpty(Mono.error(new Exception()))
            .flatMap( companyClientDto ->
                    clientWCService.findAccountTypeById(companyClientAccountRequest.getIdTypeAccount())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap( accountDto -> {
                        CompanyClientAccount companyClientAccount = new CompanyClientAccount();
                        companyClientAccount.setId(new ObjectId().toString());
                        companyClientAccount.setCreatedAt(LocalDateTime.now());
                        companyClientAccount.setCode(companyClientAccountRequest.getCode());
                        companyClientAccount.setAmount(companyClientAccountRequest.getAmount());
                        companyClientAccount.setAccountNumber(companyClientAccountRequest.getAccountNumber());
                        companyClientAccount.setOpeningDate(companyClientAccountRequest.getOpeningDate());
                        companyClientAccount.setState(companyClientAccountRequest.isState());
                        companyClientAccount.setCompany(map(companyClientDto, Client.class));
                        companyClientAccount.setTypeAccount(map(accountDto, TypeAccount.class));

                        String typDocumentClient = companyClientAccount.getCompany().getDocumentType();
                        if (typDocumentClient.equals(TypeDocument.RUC.name())){
                            if (companyClientAccount.getTypeAccount().getAllowCompany()){
                                Mono<CompanyClientAccount> entity = iCompanyClientAccountService.save(companyClientAccount);
                                return entity.map(x -> map(x, CompanyClientAccountDto.class));
                            }
                        }
                        return null;
                    })
            );
    }

    public Flux<CompanyClientAccountDto> findAll() {
        return iCompanyClientAccountService.findAll().map(x->map(x,CompanyClientAccountDto.class));
    }

    public Mono<CompanyClientAccountDto> update(CompanyClientAccountDto companyClientAccountDto) {
        return iCompanyClientAccountService.findById(companyClientAccountDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(p->{
                    CompanyClientAccount companyClientAccount =  map(companyClientAccountDto,CompanyClientAccount.class);
                    companyClientAccount.setUpdatedAt(LocalDateTime.now());
                    return iCompanyClientAccountService
                            .save(companyClientAccount)
                            .map( y -> map(y, CompanyClientAccountDto.class));
                });
    }

    public Mono<CompanyClientAccountDto> findById(String id) {
        return iCompanyClientAccountService.findById(id)
                .switchIfEmpty(Mono.error(new Exception()))
                .map(x-> map(x,CompanyClientAccountDto.class));
    }

    public Mono<Void> delete(CompanyClientAccountDto companyClientAccountDto) {
        return iCompanyClientAccountService.findById(companyClientAccountDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x-> iCompanyClientAccountService.deleteById(companyClientAccountDto.getId()));
    }

    public Mono<CompanyClientAccountDto> addSigner(SignerRequest signerRequest){
        return iCompanyClientAccountService.findById(signerRequest.getIdRegister())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> {
                    Client signer = new Client();
                    signer.setNumberDocument(signerRequest.getNumberDocument());
                    signer.setDocumentType(signerRequest.getDocumentType());
                    signer.setName(signerRequest.getName());
                    signer.setEmail(signerRequest.getEmail());
                    signer.setPhone(signerRequest.getPhone());
                    if (x.getSigners() != null) {
                        x.getSigners().add(signer);
                    } else {
                        ArrayList<Client> clientArrayList = new ArrayList<>();
                        clientArrayList.add(signer);
                        x.setSigners(clientArrayList);
                    }
                    return iCompanyClientAccountService
                            .save(x)
                            .map(y -> map(y, CompanyClientAccountDto.class));
                });
    }

    public Mono<CompanyClientAccountDto> addHolder(HolderRequest holderRequest){
        return iCompanyClientAccountService.findById(holderRequest.getIdRegister())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap( x -> {
                    Client holder = new Client();
                    holder.setNumberDocument(holderRequest.getNumberDocument());
                    holder.setDocumentType(holderRequest.getDocumentType());
                    holder.setName(holderRequest.getName());
                    holder.setEmail(holderRequest.getEmail());
                    holder.setPhone(holderRequest.getPhone());
                    if (x.getHolders() != null){
                        x.getHolders().add(holder);
                    } else {
                        ArrayList<Client> clientArrayList = new ArrayList<>();
                        clientArrayList.add(holder);
                        x.setHolders(clientArrayList);
                    }
                    return iCompanyClientAccountService
                            .save(x)
                            .map(y -> map(y, CompanyClientAccountDto.class));
                });
    }

    public Flux<CompanyClientAccountDto> findByCompanyNumberDocument(String numberDocument){
        return iCompanyClientAccountService.findByCompanyNumberDocument(numberDocument)
                .map(x -> map(x, CompanyClientAccountDto.class));
    }
}
