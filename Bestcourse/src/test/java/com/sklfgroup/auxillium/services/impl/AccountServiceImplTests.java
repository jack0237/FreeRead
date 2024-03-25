package com.sklfgroup.auxillium.services.impl;

import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotActivatedException;
import com.sklfgroup.auxillium.exceptions.NotFoundException;
import com.sklfgroup.auxillium.helpers.StringHelper;
import com.sklfgroup.auxillium.rest.dto.responses.account.ConnexionAccountResponse;
import com.sklfgroup.auxillium.rest.dto.responses.account.CreateAccountDataResponse;
import com.sklfgroup.auxillium.services.AccountService;
import com.sklfgroup.auxillium.dao.entities.account.AccountEntity;
import com.sklfgroup.auxillium.dao.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static com.sklfgroup.auxillium.AccountFixtures.EMAIL;
import static com.sklfgroup.auxillium.AccountFixtures.generateAccountEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
@Slf4j
public class AccountServiceImplTests {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    void should_throw_already_exists_exception_when_account_already_exists_in_db() {
        AccountEntity entity = generateAccountEntity();
        entity.setActive(true);
        when(accountRepository.findByEmail(EMAIL)).thenReturn(Optional.of(entity));

        assertThatThrownBy(() -> accountService.save(entity))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessage("Account already exists");
        verify(accountRepository, timeout(1)).findByEmail(entity.getEmail());
    }
    @Test
    void should_throw_not_activated_exception_when_account_not_activated_in_db() {
        AccountEntity entity = generateAccountEntity();
        entity.setActive(false);
        when(accountRepository.findByEmail(EMAIL)).thenReturn(Optional.of(entity));

        assertThatThrownBy(() -> accountService.save(entity))
                .isInstanceOf(NotActivatedException.class)
                .hasMessage("Account not activated");
        verify(accountRepository, timeout(1)).findByEmail(entity.getEmail());
    }

    @Test
    void should_save_account_when_input_is_correct() throws AlreadyExistsException, NotActivatedException {
        AccountEntity entity = generateAccountEntity();
        when(accountRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());
        when(accountRepository.save(any(AccountEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        CreateAccountDataResponse data = accountService.save(entity);

        assertThat(data).isNotNull();
        assertThat(data.getFirstname()).isEqualTo(entity.getFirstname());
        assertThat(data.getLastname()).isEqualTo(entity.getLastname());
        assertThat(data.getEmail()).isEqualTo(entity.getEmail());
        assertThat(data.getUuid()).isNotBlank();
        assertThat(data.isDeleted()).isFalse();
    }
    @Test
    public void should_throw_already_exists_exception_when_email_is_not_correct()  {

        AccountEntity entity = generateAccountEntity();

        when(accountRepository.findByActiveTrueAndDeletedFalseAndEmail(EMAIL)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> accountService.authentificateAccount(entity))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Email is not correct");

        verify(accountRepository).findByActiveTrueAndDeletedFalseAndEmail(EMAIL);

    }

    @Test
    public void should_throw_already_exists_exception_when_password_is_not_correct() {

        AccountEntity entity = generateAccountEntity();
        entity.setActive(true);

        entity.setPassword("password");

        when(accountRepository.findByActiveTrueAndDeletedFalseAndEmail(EMAIL)).thenReturn(Optional.of(entity));



        assertThatThrownBy(() -> accountService.authentificateAccount(entity))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("password is not correct");
        verify(accountRepository).findByActiveTrueAndDeletedFalseAndEmail(EMAIL);
    }

    @Test
    public void should_connect_account_when_input_is_correct() throws NoSuchAlgorithmException, AlreadyExistsException, NotActivatedException {

        AccountEntity entity = generateAccountEntity();

        //entityRequest est l'entité que je passe n paramètre lors de l'appel du service
        AccountEntity entityRequest = generateAccountEntity();
        entityRequest.setActive(true);
        entity.setActive(true);

        entityRequest.setPassword("password");
        entity.setPassword(StringHelper.generateSecurePassword("password", EMAIL));
        when(accountRepository.findByActiveTrueAndDeletedFalseAndEmail(EMAIL)).thenReturn(Optional.of(entity));

        entityRequest.setPassword("password");


        ConnexionAccountResponse data = accountService.authentificateAccount(entityRequest);

        assertThat(data).isNotNull();
        assertThat(data.getFirstname()).isEqualTo(entity.getFirstname());
        assertThat(data.getLastname()).isEqualTo(entity.getLastname());
        assertThat(data.getEmail()).isEqualTo(entity.getEmail());
        Assertions.assertThat(data.getUuid()).isNotBlank();
        Assertions.assertThat(data.isDeleted()).isFalse();

        verify(accountRepository).findByActiveTrueAndDeletedFalseAndEmail(EMAIL);
    }
    @AfterEach
    public void verification (){
        Mockito.verifyNoMoreInteractions(accountRepository);

    }
}
