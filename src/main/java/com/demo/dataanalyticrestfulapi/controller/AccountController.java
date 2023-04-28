package com.demo.dataanalyticrestfulapi.controller;

import com.demo.dataanalyticrestfulapi.mapper.AutoAccountMapper;
import com.demo.dataanalyticrestfulapi.model.Account;
import com.demo.dataanalyticrestfulapi.model.response.AccountResponse;
import com.demo.dataanalyticrestfulapi.service.AccountService;
import com.demo.dataanalyticrestfulapi.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountController {
    // inject Account Service

    final private AccountService accountService;
    final private AutoAccountMapper autoAccountMapper;

    AccountController(AccountService accountService , AutoAccountMapper autoAccountMapper){
        this.accountService = accountService;
        this.autoAccountMapper = autoAccountMapper;
    }

    @GetMapping("/all-accounts")
    public Response<List<AccountResponse>> getAllAccounts(){
        try{
            List<Account> allAccount = accountService.getAllAccounts();
            List<AccountResponse> accountResponses = autoAccountMapper.mapToAccountResponse(allAccount);

            return Response.<List<AccountResponse>>ok().setPayload(accountResponses).setMessage("Successfully retrieved all account information");

        } catch (Exception exception){
            System.out.println("Something Wrong: "+exception.getMessage());
            return Response.<List<AccountResponse>>exception().setMessage("Failed to retrevied the accounts");
        }
    }
}
