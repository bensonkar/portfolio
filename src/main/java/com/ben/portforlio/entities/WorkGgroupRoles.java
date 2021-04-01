package com.ben.portforlio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author bkariuki
 */
@Entity
@Table(name = "WORKGROUP_ROLES")
public class WorkGgroupRoles implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ROLE_ID",referencedColumnName = "ID",insertable = false,updatable = false)
    private Roles roles;
    @Column(name = "ROLE_ID")
    private BigDecimal roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public BigDecimal getRoleId() {
        return roleId;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
}
