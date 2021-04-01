package com.ben.portforlio.repositories;

import com.ben.portforlio.entities.AppLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface AppLogsRepository extends JpaRepository<AppLogs,Long> {
}
