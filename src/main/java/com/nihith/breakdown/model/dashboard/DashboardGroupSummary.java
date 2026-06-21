package com.nihith.breakdown.model.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DashboardGroupSummary {
    private String id;
    private String name;
    private int memberCount;
    private int expenseCount;
    private BigDecimal net;
    private boolean isFamily;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getExpenseCount() {
        return expenseCount;
    }

    public void setExpenseCount(int expenseCount) {
        this.expenseCount = expenseCount;
    }

    public BigDecimal getNet() {
        return net;
    }

    public void setNet(BigDecimal net) {
        this.net = net;
    }

    @JsonProperty("isFamily")
    public boolean isFamily() {
        return isFamily;
    }

    public void setFamily(boolean family) {
        isFamily = family;
    }

    @Override
    public String toString() {
        return "DashboardGroupSummary{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", memberCount=" + memberCount +
                ", expenseCount=" + expenseCount +
                ", net=" + net +
                ", isFamily=" + isFamily +
                '}';
    }
}
