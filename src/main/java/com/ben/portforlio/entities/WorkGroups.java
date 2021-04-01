package com.ben.portforlio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author bkariuki
 */
@Entity
@Table(name = "WORK_GROUPS")
public class WorkGroups implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Transient
    private List<BigDecimal> roleIds;
    @Column(name = "NAME")
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST,targetEntity = WorkGgroupRoles.class,fetch = FetchType.LAZY)
    private List<WorkGgroupRoles> workGgroupRoles;

    public WorkGroups(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BigDecimal> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<BigDecimal> roleIds) {
        this.roleIds = roleIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WorkGgroupRoles> getWorkGgroupRoles() {
        return workGgroupRoles;
    }

    public void setWorkGgroupRoles(List<WorkGgroupRoles> workGgroupRoles) {
        this.workGgroupRoles = workGgroupRoles;
    }
}
