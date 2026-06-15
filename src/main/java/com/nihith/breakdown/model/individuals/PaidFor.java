package com.nihith.breakdown.model.individuals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaidFor {

    @NotEmpty
    private String paidForId;
    private double paidForValue;
    private String paidForName;

    public String getPaidForId() {
        return paidForId;
    }

    public void setPaidForId(String paidForId) {
        this.paidForId = paidForId;
    }

    public double getPaidForValue() {
        return paidForValue;
    }

    public void setPaidForValue(double paidForValue) {
        this.paidForValue = paidForValue;
    }

    public String getPaidForName() {
        return paidForName;
    }

    public void setPaidForName(String paidForName) {
        this.paidForName = paidForName;
    }

    @Override
    public String toString() {
        return "PaidFor{" +
                "paidForId='" + paidForId + '\'' +
                ", paidForValue=" + paidForValue +
                ", paidForName='" + paidForName + '\'' +
                '}';
    }
}
