package com.bootcamp.msregisterproductclient.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document
public class CompanyClientAccount extends BaseEntity{
    private String code;
    private String accountNumber;
    private LocalDateTime openingDate;
    private Client company;
    private List<Client> holders;
    private List<Client> signers;
    private TypeAccount typeAccount;
    private boolean state;
}
