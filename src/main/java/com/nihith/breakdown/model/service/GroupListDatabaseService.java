package com.nihith.breakdown.model.service;

import com.nihith.breakdown.model.groups.Group;
import com.nihith.breakdown.model.exceptions.SystemException;
import java.util.List;

/**
 * Database service interface for listing and retrieving group information.
 */
public interface GroupListDatabaseService {

    /**
     * Fetches all groups from the database.
     *
     * @return list of all groups, or empty list if none found
     * @throws SystemException for infrastructure failures
     */
    List<Group> fetchAllGroups() throws SystemException;
}
