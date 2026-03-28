package com.nihith.breakdown.model.groups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nihith.breakdown.model.constants.Operation;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {
    String groupId;
    String groupName;
    String createdById;
    List<String> personList;
    List<Family> familyList;
    Operation operation;

    public Operation getOperation() {
        return operation;
    }
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getCreatedById() {
        return createdById;
    }
    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getPersonList() {
        return personList;
    }

    public void setPersonList(List<String> personList) {
        this.personList = personList;
    }

    public List<Family> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<Family> familyList) {
        this.familyList = familyList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", personList=" + personList +
                ", familyList=" + familyList +
                '}';
    }
}
