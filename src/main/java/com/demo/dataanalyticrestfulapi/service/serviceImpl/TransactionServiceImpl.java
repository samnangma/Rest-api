package com.demo.dataanalyticrestfulapi.service.serviceImpl;

import com.demo.dataanalyticrestfulapi.Reposity.TransactionRepository;
import com.demo.dataanalyticrestfulapi.model.Transaction;
import com.demo.dataanalyticrestfulapi.service.TransactionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionRepository transactionMapper;
    @Override
    public PageInfo<Transaction> getAllTransaction(int page, int size, int filter) {
        PageHelper.startPage(page, size);
        return new PageInfo<Transaction>(transactionRepository.getAllTransaction(filter));
    }

    @Override
    public int createTransaction(Transaction transaction) {
        return transactionMapper.insertTransaction(transaction);
    }

    @Override
    public int deleteTransaction(int id) {
        return transactionMapper.deleteTransaction(id);
    }

    @Override
    public int updateTransaction(Transaction transaction) {
        return transactionMapper.updateTransaction(transaction);
    }
}
