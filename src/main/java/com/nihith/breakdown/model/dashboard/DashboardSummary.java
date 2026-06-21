package com.nihith.breakdown.model.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DashboardSummary {
    private String displayName;
    private BigDecimal youOwe;
    private BigDecimal owedToYou;
    private BigDecimal net;
    private List<DashboardGroupSummary> recentGroups;
    private List<Object> recentFamilies;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public BigDecimal getYouOwe() {
        return youOwe;
    }

    public void setYouOwe(BigDecimal youOwe) {
        this.youOwe = youOwe;
    }

    public BigDecimal getOwedToYou() {
        return owedToYou;
    }

    public void setOwedToYou(BigDecimal owedToYou) {
        this.owedToYou = owedToYou;
    }

    public BigDecimal getNet() {
        return net;
    }

    public void setNet(BigDecimal net) {
        this.net = net;
    }

    public List<DashboardGroupSummary> getRecentGroups() {
        return recentGroups;
    }

    public void setRecentGroups(List<DashboardGroupSummary> recentGroups) {
        this.recentGroups = recentGroups;
    }

    public List<Object> getRecentFamilies() {
        return recentFamilies;
    }

    public void setRecentFamilies(List<Object> recentFamilies) {
        this.recentFamilies = recentFamilies;
    }

    @Override
    public String toString() {
        return "DashboardSummary{" +
                "displayName='" + displayName + '\'' +
                ", youOwe=" + youOwe +
                ", owedToYou=" + owedToYou +
                ", net=" + net +
                ", recentGroups=" + recentGroups +
                ", recentFamilies=" + recentFamilies +
                '}';
    }
}
