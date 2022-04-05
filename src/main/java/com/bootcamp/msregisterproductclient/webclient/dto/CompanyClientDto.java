package com.bootcamp.msregisterproductclient.webclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyClientDto {
    private String id;
    private String numberDocument;
    private String documentType;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String tradeName;
}
