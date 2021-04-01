package com.ben.portforlio.repositories;

import com.ben.portforlio.entities.RelationShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface RelationshipRepository extends JpaRepository<RelationShip, Long> {
    RelationShip findByName(String name);
}
