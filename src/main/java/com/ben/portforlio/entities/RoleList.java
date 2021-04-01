package com.ben.portforlio.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bkariuki
 */
@Entity
@Table(name = "ROLE_LIST")
public class RoleList implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "PERMISSION_ID",referencedColumnName = "ID",insertable = false,updatable = false)
    private EntityPermissions permissions;
    @Column(name = "PERMISSION_ID")
    private Long permissionId;

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


    public EntityPermissions getPermissions() {
        return permissions;
    }

    public void setPermissions(EntityPermissions permissions) {
        this.permissions = permissions;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
