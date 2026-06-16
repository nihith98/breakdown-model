package com.nihith.breakdown.model.groups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Domain model representing the full group information response payload —
 * group metadata plus the resolved member list (with display names) and family definitions.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupInformation {

    private String groupId;
    private String joiningCode;
    private String groupName;
    private String groupDescription;
    private String createdById;
    private List<GroupMember> personList;
    private List<Family> familyList;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getJoiningCode() {
        return joiningCode;
    }

    public void setJoiningCode(String joiningCode) {
        this.joiningCode = joiningCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public List<GroupMember> getPersonList() {
        return personList;
    }

    public void setPersonList(List<GroupMember> personList) {
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
        return "GroupInformation{" +
                "groupId='" + groupId + '\'' +
                ", joiningCode='" + joiningCode + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                ", personList=" + personList +
                ", familyList=" + familyList +
                '}';
    }
}
