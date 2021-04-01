package com.ben.portforlio.repositories;

import com.ben.portforlio.entities.AppRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface AppRecordsRepository extends JpaRepository<AppRecords,Long> {
}
