package com.nihith.breakdown.model.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionList {

    private String id;
    private String groupId;
    private List<Transaction> TransactionList;
    private BigDecimal totalExpenditure;

    public TransactionList(String groupId, List<Transaction> transactionList, TransactionType type, BigDecimal totalExpenditure) {
        this.groupId = groupId;
        TransactionList = transactionList;
        this.totalExpenditure = totalExpenditure;
    }

    public TransactionList(String groupId) {
        this.groupId = groupId;
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
                ", totalExpenditure=" + totalExpenditure +
                '}';
    }

}
