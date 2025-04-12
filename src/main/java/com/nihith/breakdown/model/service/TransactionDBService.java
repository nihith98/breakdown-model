package com.nihith.breakdown.model.service;

import com.nihith.breakdown.model.transactions.Transaction;

import java.util.List;

public interface TransactionDBService {

    public boolean insertTransaction(Transaction transaction);

    public boolean deleteTransaction(String transactionId);

    public List<Transaction> fetchTransaction(String groupId);

}
