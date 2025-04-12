package com.nihith.breakdown.model.transactions;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nihith.breakdown.model.individuals.PaidFor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Model for storing information about single transaction
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private static final long serialVersionUID = 1L;
    private String uuid;
    @NotEmpty
    private String transactionName;
    private String transactionDescription;
    private TransactionType transactionType;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;
    @NotEmpty
    private String paidById;
    @NotNull
    private List<PaidFor> paidForList;
    private SplitType splitType;
    private Date timestamp;
    @NotEmpty
    private String groupId;

    Transaction() {
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "uuid='" + uuid + '\'' +
                ", transactionName='" + transactionName + '\'' +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", paidById='" + paidById + '\'' +
                ", paidForList=" + paidForList +
                ", splitType=" + splitType +
                ", timestamp=" + timestamp +
                ", groupId='" + groupId + '\'' +
                '}';
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


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<PaidFor> getPaidForList() {
        return paidForList;
    }

    public void setPaidForList(List<PaidFor> paidForList) {
        this.paidForList = paidForList;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    public String getPaidById() {
        return paidById;
    }

    public void setPaidById(String paidById) {
        this.paidById = paidById;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
