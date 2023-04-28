package com.demo.dataanalyticrestfulapi.service;

import com.demo.dataanalyticrestfulapi.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    int createAccount(Account account);
    int updateAccount(Account account, int id);

    Account findAccountByID(int id);
}
