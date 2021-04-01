package com.ben.portforlio.repositories;

import com.ben.portforlio.entities.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bkariuki
 */
@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser,Long> {
    SystemUser findByEmail(String email);
}
