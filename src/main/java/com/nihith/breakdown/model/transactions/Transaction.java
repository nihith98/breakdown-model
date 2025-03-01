package com.nihith.breakdown.model.transactions;


import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Transaction {


    private String uuid;
    private String transactionName;
    private String transactionDescription;
    private BigDecimal amount;
    private String debitorId;
    private List<String> creditorList;
    private Date timestamp;
    private String groupId;

    Transaction() {
        this.uuid = UUID.randomUUID().toString();
    }

    Transaction(String transactionName, String transactionDescription, String debitorId, List<String> creditorList, BigDecimal amount, Date timestamp, String groupId) {

        this.uuid = UUID.randomUUID().toString();
        this.transactionName = transactionName;
        this.transactionDescription = transactionDescription;
        this.debitorId = debitorId;
        this.creditorList = creditorList;
        this.amount = amount;
        this.timestamp = timestamp;
        this.groupId = groupId;

    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDebitorId() {
        return debitorId;
    }

    public void setDebitorId(String debitorId) {
        this.debitorId = debitorId;
    }

    public List<String> getCreditorList() {
        return creditorList;
    }

    public void setCreditorList(List<String> creditorList) {
        this.creditorList = creditorList;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "uuid='" + uuid + '\'' +
                ", transactionName='" + transactionName + '\'' +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", amount=" + amount +
                ", debitorId='" + debitorId + '\'' +
                ", creditorList=" + creditorList +
                ", timestamp=" + timestamp +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
