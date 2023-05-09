package com.demo.dataanalyticrestfulapi.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    int sender_account_id;
    int receiver_account;
    double amount;
    String remark;
    String transfer_at;
}
