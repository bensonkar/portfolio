package com.ben.portforlio.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author bkariuki
 */
@Entity
@Data
public class Career implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Boolean working;
    private String occupation;
}
