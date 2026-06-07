package com.nihith.breakdown.model.groups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupSummary {
    private String groupId;
    private String groupName;
    private int memberCount;
    private Long lastUpdatedTimestamp;
    private BigDecimal settlementAmount;
    private int transactionCount;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public Long getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    public void setLastUpdatedTimestamp(Long lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    @Override
    public String toString() {
        return "GroupSummary{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", memberCount=" + memberCount +
                ", lastUpdatedTimestamp=" + lastUpdatedTimestamp +
                ", settlementAmount=" + settlementAmount +
                ", transactionCount=" + transactionCount +
                '}';
    }
}
