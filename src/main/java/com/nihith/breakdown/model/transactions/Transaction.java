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
 * Model for storing information about a single transaction.
 *
 * <p>Supports two transaction types:
 * <ul>
 *   <li><b>EXPENSE:</b> A transaction where one member pays for others. The {@code paidById} is the payer,
 *       and {@code paidForList} contains the beneficiaries and their share amounts.</li>
 *   <li><b>SETTLEMENT:</b> A transaction representing a payment from one party (individual or family)
 *       to another to clear outstanding balances. Automatically computed by the settlement engine.</li>
 * </ul>
 *
 * <p><b>Family-Level Settlements:</b> When a group contains families, settlement transactions are
 * computed at the family level. The {@code familyId} field identifies the settling family:
 * <ul>
 *   <li><b>SETTLEMENT with familyId:</b> Represents a family paying another party. The family's
 *       individually tracked member balances are aggregated, and the settlement represents the
 *       family's collective debt or credit.</li>
 *   <li><b>SETTLEMENT without familyId (null):</b> Represents an individual member settling a debt,
 *       used only in groups without families or for members not part of any family.</li>
 * </ul>
 *
 * <p><b>Amount Precision:</b> All amount values are scaled to 2 decimal places using
 * {@link java.math.RoundingMode#HALF_UP} rounding.
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
    /**
     * The family ID associated with a settlement transaction, identifying which family
     * owns the debt. Only populated for SETTLEMENT-type transactions computed at family level.
     * {@code null} for EXPENSE transactions and SETTLEMENT transactions involving individuals
     * not part of any family.
     *
     * <p><b>Usage:</b> When a group contains families, the settlement engine aggregates individual
     * member balances to the family level and computes settlements between families. Each settlement
     * transaction includes the payer family's ID in this field to distinguish family-level
     * settlements from individual settlements.</p>
     */
    private String familyId;
    private TransactionStatus transactionStatus;

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Transaction() {
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
                ", familyId='" + familyId + '\'' +
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
        this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
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

    /**
     * Gets the family ID associated with this settlement transaction.
     *
     * @return the family ID if this is a SETTLEMENT-type transaction computed at family level,
     *         {@code null} otherwise
     */
    public String getFamilyId() {
        return familyId;
    }

    /**
     * Sets the family ID to identify which family owns the debt in a settlement transaction.
     * This field should only be populated for SETTLEMENT-type transactions computed at the
     * family level. Individual settlements should leave this as {@code null}.
     *
     * @param familyId the family ID (typically the UUID of the family), or {@code null} for
     *                 individual settlements
     */
    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }
}
