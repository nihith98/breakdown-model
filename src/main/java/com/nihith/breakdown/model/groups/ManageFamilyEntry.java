package com.nihith.breakdown.model.groups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Represents a single family operation within a {@link ManageFamiliesRequest}.
 * <ul>
 *   <li>Omit {@code familyId} to <b>create</b> a new family.</li>
 *   <li>Include {@code familyId} with a non-empty {@code personIds} list to <b>update</b> the family.</li>
 *   <li>Include {@code familyId} with a null or empty {@code personIds} list to <b>delete</b> the family.</li>
 * </ul>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageFamilyEntry {

    private String familyId;
    private String familyName;
    private String familyHex;
    private List<PersonEntry> personIds;

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

    public String getFamilyHex() {
        return familyHex;
    }

    public void setFamilyHex(String familyHex) {
        this.familyHex = familyHex;
    }

    public List<PersonEntry> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<PersonEntry> personIds) {
        this.personIds = personIds;
    }

    @Override
    public String toString() {
        return "ManageFamilyEntry{" +
                "familyId='" + familyId + '\'' +
                ", familyName='" + familyName + '\'' +
                ", familyHex='" + familyHex + '\'' +
                ", personIds=" + personIds +
                '}';
    }
}
