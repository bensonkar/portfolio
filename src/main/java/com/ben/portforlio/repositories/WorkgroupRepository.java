package com.ben.portforlio.repositories;

import com.ben.portforlio.entities.WorkGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bkariuki
 */
@Repository
public interface WorkgroupRepository extends JpaRepository<WorkGroups,Long> {
    List<WorkGroups> findAllById(Long id);
    WorkGroups findByName(String name);
}
