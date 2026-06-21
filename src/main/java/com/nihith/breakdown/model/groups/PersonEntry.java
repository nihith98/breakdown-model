package com.nihith.breakdown.model.groups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a single person entry in a manage-families request, pairing the person's
 * unique identifier with their display name.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonEntry {

    private String personId;
    private String displayName;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "PersonEntry{" +
                "personId='" + personId + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
