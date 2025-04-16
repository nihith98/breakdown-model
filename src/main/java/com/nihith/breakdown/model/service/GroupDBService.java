package com.nihith.breakdown.model.service;

import com.nihith.breakdown.model.exceptions.SystemException;
import com.nihith.breakdown.model.groups.Family;
import com.nihith.breakdown.model.groups.Group;

import java.util.List;

public interface GroupDBService {

    public boolean createGroup(Group groupDetails) throws SystemException;

    public boolean updateGroup(String groupId, List<String> newPersonList, List<Family> newFamilyList) throws SystemException;

    public Group fetchGroupDetails(String groupId) throws SystemException;

}
