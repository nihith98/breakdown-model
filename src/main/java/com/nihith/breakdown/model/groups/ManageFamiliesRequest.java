package com.nihith.breakdown.model.groups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Top-level request body for the manage-families endpoint.
 * Contains a list of {@link ManageFamilyEntry} objects, each describing a create, update, or delete
 * operation on a single family within a group.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageFamiliesRequest {

    private List<ManageFamilyEntry> familyList;

    public List<ManageFamilyEntry> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<ManageFamilyEntry> familyList) {
        this.familyList = familyList;
    }

    @Override
    public String toString() {
        return "ManageFamiliesRequest{" +
                "familyList=" + familyList +
                '}';
    }
}
