package com.demo.dataanalyticrestfulapi.Reposity;

import com.demo.dataanalyticrestfulapi.Reposity.provider.TransactionProvider;
import com.demo.dataanalyticrestfulapi.model.Transaction;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TransactionRepository {
    @SelectProvider(type = TransactionProvider.class, method = "getAllTransaction")
    List<Transaction> getAllTransaction(int filter);

    @InsertProvider(type = TransactionProvider.class, method = "insertTransaction")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertTransaction(Transaction transaction);

    @DeleteProvider(type = TransactionProvider.class, method = "deleteTransaction")
    int deleteTransaction(@Param("id") int id);

    @UpdateProvider(type = TransactionProvider.class, method = "updateTransaction")
    int updateTransaction(Transaction transaction);
}
