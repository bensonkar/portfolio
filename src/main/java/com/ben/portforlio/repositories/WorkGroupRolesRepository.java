package com.ben.portforlio.repositories;

import com.ben.portforlio.entities.WorkGgroupRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface WorkGroupRolesRepository extends JpaRepository<WorkGgroupRoles,Long> {
}
