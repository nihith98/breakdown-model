package com.nihith.breakdown.model.service;

import com.nihith.breakdown.model.transactions.Transaction;

import java.util.List;

public interface TransactionDBService {

    public boolean insertTransaction(Transaction transaction);

    public boolean deleteTransaction(String transactionId);

    public List<Transaction> fetchTransaction(String groupId);

    public List<Transaction> fetchExpenseList(String groupId);

    public List<Transaction> fetchSettlementList(String groupId);

    public boolean updateSettlementList(String groupId, List<Transaction> settlementList);

}
