package com.ben.portforlio.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author bkariuki
 */
@Data
@Entity
public class RelationShip implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date creationDate;

    public RelationShip() {
        this.creationDate = new Date();
    }

    public RelationShip(String name) {
        this.name = name;
        this.creationDate = new Date();
    }
}
