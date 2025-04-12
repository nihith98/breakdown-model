package com.nihith.breakdown.model.groups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Family {

    private String familyName;
    private String familyId;
    private List<String> personIds;

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public List<String> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<String> personIds) {
        this.personIds = personIds;
    }

    @Override
    public String toString() {
        return "Family{" +
                "familyName='" + familyName + '\'' +
                ", familyId='" + familyId + '\'' +
                ", personIds=" + personIds +
                '}';
    }
}
