package com.ben.portforlio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author bkariuki
 */
@Entity
@Table(name = "USER_GROUP")
public class UserGroup implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "GROUP_ID",referencedColumnName = "ID",insertable = false,updatable = false)
    private WorkGroups workGroups;
    @Column(name = "GROUP_ID")
    private BigDecimal groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkGroups getWorkGroups() {
        return workGroups;
    }

    public void setWorkGroups(WorkGroups workGroups) {
        this.workGroups = workGroups;
    }

    public BigDecimal getGroupId() {
        return groupId;
    }

    public void setGroupId(BigDecimal groupId) {
        this.groupId = groupId;
    }
}
