package com.bootcamp.msregisterproductclient.resource;

import com.bootcamp.msregisterproductclient.dto.CompanyClientAccountDto;
import com.bootcamp.msregisterproductclient.dto.PersonClientAccountDto;
import com.bootcamp.msregisterproductclient.entity.CompanyClientAccount;
import com.bootcamp.msregisterproductclient.entity.PersonClientAccount;
import com.bootcamp.msregisterproductclient.util.MapperUtil;
import com.bootcamp.msregisterproductclient.service.ICompanyClientAccountService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
@Slf4j
@Service
public class CompanyClientAccountResource extends MapperUtil {

    @Autowired
    private ICompanyClientAccountService iCompanyClientAccountService;

    public Mono<CompanyClientAccountDto> create(CompanyClientAccountDto companyClientAccountDto) {

        CompanyClientAccount companyClientAccount = map(companyClientAccountDto, CompanyClientAccount.class);
        companyClientAccount.setId(new ObjectId().toString());
        companyClientAccount.setCreatedAt(LocalDateTime.now());

        String typDocumentClient = companyClientAccountDto.getCompany().getDocumentType();
        log.info(companyClientAccount.getCompany().getName());
        if (typDocumentClient.equals(TypeDocument.RUC.name())){
            if (companyClientAccount.getTypeAccount().getAllowCompany()){
                Mono<CompanyClientAccount> entity = iCompanyClientAccountService.save(companyClientAccount);
                return entity.map(x -> map(x, CompanyClientAccountDto.class));
            }
        }
        return null;

        /*
        String typeAccount = companyClientAccount.getTypeAccount().getName();
        String typeClient = companyClientAccount.getClient().getClientType();
        if(typeAccount.equals(TypeAccount.CURRENTACCOUNT.name()) && typeClient.equals(TypeClient.Company.name())){
            companyClientAccount.setId(new ObjectId().toString());
            companyClientAccount.setCreatedAt(LocalDateTime.now());
            Mono<CompanyClientAccount> entity = iCompanyClientAccountService.save(companyClientAccount);
            return entity.map(x->map(x,CompanyClientAccountDto.class));
        }else{
            return Mono.error(new Exception());
        }*/
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
                    return iCompanyClientAccountService.save(companyClientAccount).map(y->map(y,CompanyClientAccountDto.class));
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

    public Mono<CompanyClientAccountDto> findByCompanyNumberDocument(String numberDocument){
        return iCompanyClientAccountService.findByCompanyNumberDocument(numberDocument)
                .map(x -> map(x, CompanyClientAccountDto.class));
    }
}
