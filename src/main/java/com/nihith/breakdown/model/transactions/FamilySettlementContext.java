package com.nihith.breakdown.model.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Context object for tracking settlement information at the family level.
 * 
 * Combines family identifiers with settlement balance information to support
 * family-grouped settlement computations instead of individual-level settlements.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FamilySettlementContext {

    private String familyId;
    
    private String familyName;
    
    private BigDecimal familyBalance;
    
    /**
     * Optional: Maps individual member IDs to their contribution within the family.
     * Useful for tracking transparency about who paid/owes what within a family
     * while still settling at the family level.
     */
    private Map<String, BigDecimal> memberBalances;

    public FamilySettlementContext() {
    }

    public FamilySettlementContext(String familyId, String familyName, BigDecimal familyBalance) {
        this.familyId = familyId;
        this.familyName = familyName;
        this.familyBalance = familyBalance;
    }

    public FamilySettlementContext(String familyId, String familyName, BigDecimal familyBalance, 
                                  Map<String, BigDecimal> memberBalances) {
        this.familyId = familyId;
        this.familyName = familyName;
        this.familyBalance = familyBalance;
        this.memberBalances = memberBalances;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public BigDecimal getFamilyBalance() {
        return familyBalance;
    }

    public void setFamilyBalance(BigDecimal familyBalance) {
        this.familyBalance = familyBalance;
    }

    public Map<String, BigDecimal> getMemberBalances() {
        return memberBalances;
    }

    public void setMemberBalances(Map<String, BigDecimal> memberBalances) {
        this.memberBalances = memberBalances;
    }

    @Override
    public String toString() {
        return "FamilySettlementContext{" +
                "familyId='" + familyId + '\'' +
                ", familyName='" + familyName + '\'' +
                ", familyBalance=" + familyBalance +
                ", memberBalances=" + memberBalances +
                '}';
    }
}
