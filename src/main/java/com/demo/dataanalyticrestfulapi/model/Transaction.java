package com.demo.dataanalyticrestfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Transaction {
    private int id;
    private int sender_account_id;
    private int receiver_account;
    private double amount;
    private String remark;
    private String transfer_at;
}
