package com.demo.dataanalyticrestfulapi.mapper;

import com.demo.dataanalyticrestfulapi.model.Account;
import com.demo.dataanalyticrestfulapi.model.response.AccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutoAccountMapper {
    // account -> account response
    List<AccountResponse> mapToAccountResponse(List<Account> accounts);
    // account - > account

    List<Account>mapToAccount(List<AccountResponse> responses);
}
