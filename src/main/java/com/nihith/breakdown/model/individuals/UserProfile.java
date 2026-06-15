package com.nihith.breakdown.model.individuals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Domain model representing a user's profile as stored in the {@code users} collection.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile {

    private String userId;
    private String username;
    private String displayName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
