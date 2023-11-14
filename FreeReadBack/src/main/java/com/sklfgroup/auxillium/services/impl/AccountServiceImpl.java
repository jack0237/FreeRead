package com.sklfgroup.auxillium.services.impl;

import com.sklfgroup.auxillium.helpers.ObjectHelper;
import com.sklfgroup.auxillium.helpers.StringHelper;
import com.sklfgroup.auxillium.rest.dto.responses.account.ConnexionAccountResponse;
import com.sklfgroup.auxillium.rest.dto.responses.account.CreateAccountDataResponse;
import com.sklfgroup.auxillium.rest.dto.responses.account.CreateAccountResponse;
import com.sklfgroup.auxillium.services.AccountService;
import com.sklfgroup.auxillium.dao.entities.account.AccountEntity;
import com.sklfgroup.auxillium.dao.repositories.AccountRepository;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotActivatedException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.exceptions.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;
    @Autowired
    private EmailServiceImp emailService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public CreateAccountDataResponse save(AccountEntity entity) throws AlreadyExistsException, NotActivatedException, NoSuchAlgorithmException {
        LOGGER.info("saving account: {}", ObjectHelper.toString(entity));
        Optional<AccountEntity> existingAccount = accountRepository.findByEmail(entity.getEmail());
        if (existingAccount.isPresent()) {
            if (existingAccount.get().isActive()) {
                LOGGER.error("Account already exists");
                throw new AlreadyExistsException("Account already exists");
            } else {
                LOGGER.error("Account not activated");
                throw new NotActivatedException("Account not activated");
            }
        }
        entity.setPassword(StringHelper.generateSecurePassword(entity.getPassword(), entity.getEmail()));
        // TODO: send email to confirm account in order for him to active the account
        CreateAccountDataResponse createAccountDataResponse = new CreateAccountDataResponse(accountRepository.save(entity));
        String confirmationLink = emailService.generateConfirmationLink(); // Générez votre lien de confirmation ici
        emailService.sendConfirmationEmail(entity.getEmail(), confirmationLink); // Envoyez l'e-mail de confirmation

        return createAccountDataResponse;
    }

    @Override
    public CreateAccountDataResponse find(String id) {
        return new CreateAccountDataResponse(accountRepository.findByUuid(id).orElseThrow(() -> new NotFoundException("ToDo not found")));
    }

    @Override
    public CreateAccountResponse findAll(Pageable pageable) {
        return new CreateAccountResponse(accountRepository.findAll(pageable));
    }

    @Override
    public ConnexionAccountResponse authentificateAccount(AccountEntity entity) throws AlreadyExistsException, NotActivatedException, NoSuchAlgorithmException {



        Optional<AccountEntity> accountEntity = accountRepository.findByActiveTrueAndDeletedFalseAndEmail(entity.getEmail());

        if (accountEntity.isPresent()) {


            String password = StringHelper.generateSecurePassword(entity.getPassword(), entity.getEmail());

            log.info(accountEntity.get().getPassword() + " from Entity");
            if (accountEntity.get().getPassword().equals(password)) {
                return new ConnexionAccountResponse(accountEntity.get());
            } else {
                // throw new  Unauth
                throw new UnauthorizedException("password is not correct");

            }
        } else {
            throw new UnauthorizedException("Email is not correct");

            //bien gerer les exceptions
        }

    }


}
