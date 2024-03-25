package com.sklfgroup.auxillium.rest.api;

import com.sklfgroup.auxillium.rest.dto.requests.CreateAccountRequest;
import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
import com.sklfgroup.auxillium.exceptions.NotActivatedException;
import com.sklfgroup.auxillium.rest.dto.requests.ConnexionAccountRequest;
import com.sklfgroup.auxillium.rest.dto.responses.account.ConnexionAccountResponse;
import com.sklfgroup.auxillium.rest.dto.responses.account.CreateAccountResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;

@RequestMapping(value = "/lightenergy/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AccountApi {
    @PostMapping
    ResponseEntity<CreateAccountResponse> createAccount(CreateAccountRequest requestDto) throws AlreadyExistsException, NotActivatedException, NoSuchAlgorithmException;

    @GetMapping(value = "{id}")
    ResponseEntity<CreateAccountResponse> findToDo(String id);

    @GetMapping
    ResponseEntity<CreateAccountResponse> findToDos(Integer page, Integer size);
    @PostMapping("/login")
    ResponseEntity<ConnexionAccountResponse> authentificateAccount (ConnexionAccountRequest requestDto) throws AlreadyExistsException, NotActivatedException, NoSuchAlgorithmException;

}
