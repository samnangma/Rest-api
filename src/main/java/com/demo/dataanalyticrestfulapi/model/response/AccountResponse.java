package com.demo.dataanalyticrestfulapi.model.response;

import com.demo.dataanalyticrestfulapi.model.Account;
import com.demo.dataanalyticrestfulapi.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Integer id;
    private String accountName;
    private String accountNumber;
    private String profile;
    private String phoneNumber;
    private Integer transferLimit;
    private AccountType accountType;
}
