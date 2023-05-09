package com.demo.dataanalyticrestfulapi.service;

import com.demo.dataanalyticrestfulapi.model.Transaction;
import com.github.pagehelper.PageInfo;


public interface TransactionService {
    PageInfo<Transaction> getAllTransaction(int page, int size, int filter);
    int createTransaction(Transaction transaction);
    int deleteTransaction(int id);
    int updateTransaction(Transaction transaction);
}
