package com.nihith.breakdown.model.transactions;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
public class Transaction {


    private String uuid;
    private String transactionName;
    private String transactionDescription;
    private BigDecimal amount;
    private String debitorId;
    private String creditorId;
    private Date timestamp;

    Transaction() {
        this.uuid = UUID.randomUUID().toString();
    }

    Transaction(String transactionName, String transactionDescription, String debitorId, String creditorId, BigDecimal amount, Date timestamp) {

        this.uuid = UUID.randomUUID().toString();
        this.transactionName = transactionName;
        this.transactionDescription = transactionDescription;
        this.debitorId = debitorId;
        this.creditorId = creditorId;
        this.amount = amount;
        this.timestamp = timestamp;

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

    public String getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(String creditorId) {
        this.creditorId = creditorId;
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
                ", creditorId='" + creditorId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
