package com.nihith.breakdown.model.individuals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaidFor {

    @NotEmpty
    private String paidForId;
    private double paidForValue;

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

    @Override
    public String toString() {
        return "PaidFor{" +
                "paidForId='" + paidForId + '\'' +
                ", paidForValue=" + paidForValue +
                '}';
    }
}
