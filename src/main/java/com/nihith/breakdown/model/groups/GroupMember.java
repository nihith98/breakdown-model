package com.nihith.breakdown.model.groups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Domain model representing a single resolved group member — a person ID paired with
 * their display name — for use in the group information response payload.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupMember {

    private String userId;
    private String displayName;

    public GroupMember() {
    }

    public GroupMember(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "userId='" + userId + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
