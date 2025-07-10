package com.nihith.breakdown.model.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionList {

    private String id;
    private String groupId;
    private List<Transaction> transactionList;
    private List<Transaction> settlementList;
    private BigDecimal totalExpenditure;

    public TransactionList(String groupId, List<Transaction> transactionList, TransactionType type, BigDecimal totalExpenditure) {
        this.groupId = groupId;
        this.transactionList = transactionList;
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
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        transactionList = transactionList;
    }

    public BigDecimal getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(BigDecimal totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public List<Transaction> getSettlementList() {
        return settlementList;
    }

    public void setSettlementList(List<Transaction> settlementList) {
        this.settlementList = settlementList;
    }

    @Override
    public String toString() {
        return "TransactionList{" +
                "id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", transactionList=" + transactionList +
                ", settlementList=" + settlementList +
                ", totalExpenditure=" + totalExpenditure +
                '}';
    }
}
