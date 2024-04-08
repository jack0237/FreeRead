//package com.sklfgroup.auxillium.rest.controllers;
//
//import com.sklfgroup.auxillium.rest.api.AccountApi;
//import com.sklfgroup.auxillium.rest.dto.requests.ConnexionAccountRequest;
//import com.sklfgroup.auxillium.rest.dto.requests.CreateAccountRequest;
//import com.sklfgroup.auxillium.rest.dto.responses.account.ConnexionAccountResponse;
//import com.sklfgroup.auxillium.rest.dto.responses.account.CreateAccountResponse;
//import com.sklfgroup.auxillium.exceptions.AlreadyExistsException;
//import com.sklfgroup.auxillium.exceptions.NotActivatedException;
//import com.sklfgroup.auxillium.helpers.Pager;
//import com.sklfgroup.auxillium.services.AccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import java.security.NoSuchAlgorithmException;
//
//@CrossOrigin
//@RestController
//public class AccountController implements AccountApi {
//
//    private final AccountService accountService;
//
//    @Autowired
//    public AccountController(AccountService accountService) {
//        this.accountService = accountService;
//    }
//
//    @Override
//    public ResponseEntity<CreateAccountResponse> createAccount(@NotNull @Valid @RequestBody CreateAccountRequest requestDto) throws AlreadyExistsException, NotActivatedException, NoSuchAlgorithmException {
//        return new ResponseEntity<>(new CreateAccountResponse(accountService.save(requestDto.toEntity())), HttpStatus.CREATED);
//    }
//
//    @Override
//    public ResponseEntity<CreateAccountResponse> findToDo(@NotBlank @PathVariable String id) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<CreateAccountResponse> findToDos(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
//        Pageable pageable = Pager.of(page, size);
//        return new ResponseEntity<>(accountService.findAll(pageable), HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<ConnexionAccountResponse> authentificateAccount(@NotNull @Valid @RequestBody ConnexionAccountRequest requestDto) throws AlreadyExistsException, NotActivatedException, NoSuchAlgorithmException {
//        return ResponseEntity.ok(accountService.authentificateAccount(requestDto.toEntity()));
//
//    }
//
//
//}
