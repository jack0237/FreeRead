package com.sklfgroup.auxillium.services;

import com.sklfgroup.auxillium.rest.dto.responses.account.ConnexionAccountResponse;
import com.sklfgroup.auxillium.rest.dto.responses.account.CreateAccountDataResponse;
import com.sklfgroup.auxillium.rest.dto.responses.account.CreateAccountResponse;
import com.sklfgroup.auxillium.dao.entities.account.AccountEntity;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotActivatedException;
import org.springframework.data.domain.Pageable;

import java.security.NoSuchAlgorithmException;

public interface AccountService {
    CreateAccountDataResponse save(AccountEntity entity) throws AlreadyExistsException, NotActivatedException, NoSuchAlgorithmException;
    CreateAccountDataResponse find(String uuid);
    CreateAccountResponse findAll(Pageable pageable);
    ConnexionAccountResponse authentificateAccount (AccountEntity entity) throws AlreadyExistsException, NotActivatedException, NoSuchAlgorithmException;
//TODO: TRANSFORMER LA REQUEST EN ENTITIE GUIDE SAVE
}
