package com.sklfgroup.auxillium;

import com.sklfgroup.auxillium.rest.dto.responses.account.ConnexionAccountResponse;
import com.sklfgroup.auxillium.rest.dto.responses.account.CreateAccountDataResponse;
import com.sklfgroup.auxillium.dao.entities.account.AccountEntity;

import java.util.UUID;

public class AccountFixtures {

    public static final String EMAIL = "samantha.edima@yopmail.com";

    public static AccountEntity generateAccountEntity(){
        AccountEntity entity = new AccountEntity();
        entity.setFirstname("Samantha");
        entity.setLastname("EDIMA");
        entity.setEmail(EMAIL);
        return entity;
    }

    public static CreateAccountDataResponse generateCreateAccountDataResponse(){
        CreateAccountDataResponse response = new CreateAccountDataResponse();
        response.setFirstname("Samantha");
        response.setLastname("EDIMA");
        response.setEmail(EMAIL);
        response.setDeleted(false);
        response.setUuid(UUID.randomUUID().toString());
        return response;
    }

    public static ConnexionAccountResponse generateConnexionAccountResponse(){
        ConnexionAccountResponse response = new ConnexionAccountResponse();
        response.setFirstname("Samantha");
        response.setLastname("EDIMA");
        response.setEmail(EMAIL);
        response.setDeleted(false);
        response.setUuid(UUID.randomUUID().toString());
        return response;
    }
}
