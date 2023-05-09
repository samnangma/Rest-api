package com.demo.dataanalyticrestfulapi.mapper;

import com.demo.dataanalyticrestfulapi.model.Transaction;
import com.demo.dataanalyticrestfulapi.model.response.TransactionResponse;

import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface TransactionMapper {
    List<Transaction> saveTransactionToTransaction(List<TransactionResponse> response);
    List<TransactionResponse> transactionOriginal(List<Transaction> transaction);

}
