package com.ben.portforlio.repositories;

import com.ben.portforlio.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bkariuki
 */
@Repository
public interface FamilyRepository extends JpaRepository<Family,Long> {
    Family findByEmail(String email);
    Family findByPhone(String phone);
    List<Family> findAllByEmail(String email);
}
