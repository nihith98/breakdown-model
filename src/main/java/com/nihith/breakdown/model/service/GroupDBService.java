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
     * Adds new members to an existing group.
     *
     * @param groupId       the unique identifier of the group to update
     * @param request list of new member IDs to add to the group
     * @return {@code true} if the update was successful, {@code false} otherwise
     * @throws SystemException if a database error prevents the update
     */
    public boolean addMembers(String groupId, List<String> newPersonList) throws SystemException;

    /**
     * Fetches the list of families present in the group identified by the given ID.
     *
     * @param groupId the unique identifier of the group
     * @return the list of {@link Family} objects belonging to the group, or an empty list if none exist
     * @throws SystemException if the group is not found or a database error occurs
     */
    public List<Family> fetchFamilyList(String groupId) throws SystemException;

    /**
     * Replaces an existing family in the group with the provided family object, matched by {@code familyId}.
     * If no matching family is found, the incoming family is appended to the existing family list.
     * The updated group is then persisted via a delete-and-insert.
     *
     * @param groupId       the unique identifier of the group to update
     * @param updatedFamily the {@link Family} object to upsert; matched against existing families by {@code familyId}
     * @return {@code true} if the group was updated successfully, {@code false} otherwise
     * @throws SystemException if the group is not found or a database error occurs
     */
    public boolean upsertFamily(String groupId, Family updatedFamily) throws SystemException;

    /**
     * Persists the family list of an already-hydrated group object by replacing the group
     * document in the data store via a delete-and-insert.
     *
     * @param updatedGroup the {@link Group} object with the new {@code familyList} already set
     * @return {@code true} if the group was updated successfully, {@code false} otherwise
     * @throws SystemException if a database error prevents the update
     */
    public boolean updateFamilyList(Group updatedGroup) throws SystemException;

    /**
     * Fetches the full details of the group identified by the given ID.
     *
     * @param groupId the unique identifier of the group to retrieve
     * @return the {@link Group} object containing members and family definitions
     * @throws SystemException if the group is not found or a database error occurs
     */
    public Group fetchGroupDetails(String groupId) throws SystemException;

    /**
     * Adds a user to a group by joining code.
     * The user ID is identified by the X-User-Id header and added to the group's personList.
     *
     * @param joiningCode the unique joining code of the group to join
     * @param userId      the unique identifier of the user joining the group
     * @return {@code true} if the user was successfully added to the group, {@code false} otherwise
     * @throws SystemException if the group is not found, user is already a member, or a database error occurs
     */
    public boolean joinGroupByCode(String joiningCode, String userId) throws SystemException;

    /**
     * Removes a user from a group's member list. The user is also removed from any family's
     * {@code personIds} list it belongs to.
     *
     * @param groupId the unique identifier of the group to update
     * @param userId  the unique identifier of the user to remove
     * @return {@code true} if the user was successfully removed, {@code false} otherwise
     * @throws SystemException if the group is not found or a database error occurs
     */
    public boolean removeMember(String groupId, String userId) throws SystemException;

    /**
     * Updates the {@code lastUpdatedTimestamp} field of a group to mark it as recently modified,
     * without touching any other field.
     *
     * @param groupId   the unique identifier of the group to update
     * @param timestamp the new {@code lastUpdatedTimestamp} value (epoch milliseconds)
     * @return {@code true} if the update was successful, {@code false} otherwise
     * @throws SystemException if the group is not found or a database error occurs
     */
    public boolean touchLastUpdatedTimestamp(String groupId, Long timestamp) throws SystemException;

}
