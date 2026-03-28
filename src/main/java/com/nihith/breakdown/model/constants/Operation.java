package com.nihith.breakdown.model.constants;

public enum Operation {

    CREATE("Create"),
    MODIFY("Modify"),
    DELETE("Delete");

    private final String displayName;

    Operation(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
