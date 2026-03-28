package com.nihith.breakdown.model.service;

import com.nihith.breakdown.model.exceptions.SystemException;
import com.nihith.breakdown.model.groups.Family;
import com.nihith.breakdown.model.groups.Group;

import java.util.List;

/**
 * Service interface that defines the database operations for managing groups and families.
 * Implementations provide the persistence layer for a specific database technology.
 */
public interface GroupDBService {

    /**
     * Creates a new group record in the data store.
     *
     * @param groupDetails the group information to persist
     * @return {@code true} if the group was created successfully, {@code false} otherwise
     * @throws SystemException if a database error prevents the creation
     */
    public boolean createGroup(Group groupDetails) throws SystemException;

    /**
     * Adds new members and family sub-groups to an existing group.
     *
     * @param groupId       the unique identifier of the group to update
     * @param newPersonList list of new member IDs to add to the group
     * @param newFamilyList list of new family definitions to add to the group
     * @return {@code true} if the update was successful, {@code false} otherwise
     * @throws SystemException if a database error prevents the update
     */
    public boolean updateGroup(String groupId, List<String> newPersonList, List<Family> newFamilyList) throws SystemException;

    /**
     * Fetches the full details of the group identified by the given ID.
     *
     * @param groupId the unique identifier of the group to retrieve
     * @return the {@link Group} object containing members and family definitions
     * @throws SystemException if the group is not found or a database error occurs
     */
    public Group fetchGroupDetails(String groupId) throws SystemException;

}
