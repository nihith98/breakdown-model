package com.nihith.breakdown.model.service;

import com.nihith.breakdown.model.transactions.Transaction;

import java.util.List;

/**
 * Service interface that defines the database operations for managing transactions and
 * settlements. Implementations provide the persistence layer for a specific database
 * technology (e.g. MongoDB).
 */
public interface TransactionDBService {

    /**
     * Persists a new transaction record in the data store.
     *
     * @param transaction the transaction to insert
     * @return {@code true} if the insertion was successful, {@code false} otherwise
     */
    public boolean insertTransaction(Transaction transaction);

    /**
     * Removes the transaction identified by the given ID from the data store.
     *
     * @param transactionId the unique identifier of the transaction to delete
     * @return {@code true} if the deletion was successful, {@code false} otherwise
     */
    public boolean deleteTransaction(String transactionId);

    /**
     * Retrieves all transactions (expense and settlement) belonging to the specified group.
     *
     * @param groupId the unique identifier of the group
     * @return a list of transactions for the group; empty list if none found
     */
    public List<Transaction> fetchTransaction(String groupId);

    /**
     * Retrieves only the {@link com.nihith.breakdown.model.transactions.TransactionType#EXPENSE}
     * transactions for the specified group.
     *
     * @param groupId the unique identifier of the group
     * @return a list of expense transactions; empty list if none found
     */
    public List<Transaction> fetchExpenseList(String groupId);

    /**
     * Retrieves only the {@link com.nihith.breakdown.model.transactions.TransactionType#SETTLEMENT}
     * transactions for the specified group.
     *
     * @param groupId the unique identifier of the group
     * @return a list of settlement transactions; empty list if none found
     */
    public List<Transaction> fetchSettlementList(String groupId);

    /**
     * Replaces the current settlement list for the specified group with the provided list.
     * Implementations are expected to clear the existing settlements before inserting the new ones.
     *
     * @param groupId        the unique identifier of the group
     * @param settlementList the new settlement transactions to persist
     * @return {@code true} if the update was successful, {@code false} otherwise
     */
    public boolean updateSettlementList(String groupId, List<Transaction> settlementList);

}
