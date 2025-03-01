package com.nihith.breakdown.model.transactions;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class TransactionList {

    private String groupId;
    private List<Transaction> TransactionList;
    private TransactionType type;
    private BigDecimal totalExpenditure;

    public TransactionList(String groupId, List<Transaction> transactionList, TransactionType type, BigDecimal totalExpenditure) {
        this.groupId = groupId;
        TransactionList = transactionList;
        this.type = type;
        this.totalExpenditure = totalExpenditure;
    }

    public TransactionList(String groupId) {
        this.groupId = groupId;
    }

    public TransactionList(String groupId, TransactionType type) {
        this.groupId = groupId;
        this.type = type;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Transaction> getTransactionList() {
        return TransactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        TransactionList = transactionList;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(BigDecimal totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    @Override
    public String toString() {
        return "TransactionList{" +
                "groupId='" + groupId + '\'' +
                ", TransactionList=" + TransactionList +
                ", type=" + type +
                ", totalExpenditure=" + totalExpenditure +
                '}';
    }
}
