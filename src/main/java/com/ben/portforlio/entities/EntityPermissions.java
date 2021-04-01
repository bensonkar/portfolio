package com.ben.portforlio.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bkariuki
 */
@Entity
@Table(name = "ENTITY_PERMISSIONS")
public class EntityPermissions implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "ENTITY_ID",referencedColumnName = "ID",updatable = false,insertable = false)
    private AppEntity entity;
    @Column(name = "ENTITY_ID")
    private Long entityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public AppEntity getEntity() {
        return entity;
    }

    public void setEntity(AppEntity entity) {
        this.entity = entity;
    }
}
