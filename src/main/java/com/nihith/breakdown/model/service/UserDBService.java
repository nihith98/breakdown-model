package com.nihith.breakdown.model.service;

import com.nihith.breakdown.model.exceptions.SystemException;
import com.nihith.breakdown.model.individuals.UserProfile;

import java.util.List;

/**
 * Database service interface for retrieving user profile information.
 */
public interface UserDBService {

    /**
     * Fetches user profiles for the given list of user IDs.
     *
     * @param userIds the list of user IDs to look up
     * @return list of matching {@link UserProfile} objects; empty list if none found
     * @throws SystemException for infrastructure failures
     */
    List<UserProfile> fetchUsersByIds(List<String> userIds) throws SystemException;
}
