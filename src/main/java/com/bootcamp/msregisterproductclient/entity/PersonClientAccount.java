package com.bootcamp.msregisterproductclient.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class PersonClientAccount extends BaseEntity {
    private String code;
    private String accountNumber;
    private LocalDateTime openingDate;
    private TypeAccount typeAccount;
    private Client person;
    private boolean state;
}
