package com.sklfgroup.auxillium.rest.dto.requests;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sklfgroup.auxillium.dao.entities.account.AccountEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ConnexionAccountRequest(

        @Email
        @NotBlank(message = "email is required")
        @JsonProperty(value = "email", required = true)
        String email,

        @NotBlank(message = "password is required")
        @JsonProperty(value = "password", required = true)
        String password) {

    public AccountEntity toEntity(){
        AccountEntity entity = new AccountEntity();
        entity.setPassword(password);
        entity.setEmail(email.toLowerCase());
        return entity;
    }
}