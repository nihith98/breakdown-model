package com.nihith.breakdown.model.groups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JoinGroupRequest {
    @NotEmpty(message = "Joining code is required")
    private String joiningCode;

    public String getJoiningCode() {
        return joiningCode;
    }

    public void setJoiningCode(String joiningCode) {
        this.joiningCode = joiningCode;
    }

    @Override
    public String toString() {
        return "JoinGroupRequest{" +
                "joiningCode='" + joiningCode + '\'' +
                '}';
    }
}
