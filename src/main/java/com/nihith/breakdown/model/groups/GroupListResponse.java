package com.nihith.breakdown.model.groups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupListResponse {
    private List<GroupSummary> groups;
    private int totalExpenses;
    private int requireSettling;
    private BigDecimal effectiveAmount;

    public List<GroupSummary> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupSummary> groups) {
        this.groups = groups;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public int getRequireSettling() {
        return requireSettling;
    }

    public void setRequireSettling(int requireSettling) {
        this.requireSettling = requireSettling;
    }

    public BigDecimal getEffectiveAmount() {
        return effectiveAmount;
    }

    public void setEffectiveAmount(BigDecimal effectiveAmount) {
        this.effectiveAmount = effectiveAmount;
    }

    @Override
    public String toString() {
        return "GroupListResponse{" +
                "groups=" + groups +
                ", totalExpenses=" + totalExpenses +
                ", requireSettling=" + requireSettling +
                ", effectiveAmount=" + effectiveAmount +
                '}';
    }
}
