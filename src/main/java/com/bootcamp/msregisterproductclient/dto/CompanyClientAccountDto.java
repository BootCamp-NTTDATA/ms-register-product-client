package com.bootcamp.msregisterproductclient.dto;

import com.bootcamp.msregisterproductclient.entity.Client;
import com.bootcamp.msregisterproductclient.entity.TypeAccount;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyClientAccountDto {
    private String id;
    private String code;
    private BigDecimal amount;
    private String accountNumber;
    private LocalDateTime openingDate;
    private Client company;
    private List<Client> holders;
    private List<Client> signers;
    private TypeAccount typeAccount;
    private boolean state;
}
