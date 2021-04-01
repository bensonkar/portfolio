package com.ben.portforlio.entities;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author bkariuki
 */
@Entity
@Table(name = "AUTHENTICATION")
@AllArgsConstructor
public class Authentication implements UserDetails,Serializable {

    private static final long serialVersionUID = 9203159848644017221L;
    @Id
    @GeneratedValue
    @Column(name = "AUTH_ID")
    private Long authId;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "PAWSSWORD")
    private String password;
    @Column(name = "LAST_LOGIN")
    private Date lastLogin;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "USER_ID")
    private Long userId;
    @OneToOne
    @JoinColumn(name = "USER_ID",referencedColumnName = "USER_ID",insertable = false,updatable = false)
    private SystemUser user;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean accountEnabled;
    @Transient
    private List<BigDecimal> workGroupIds;
//    @OneToMany(targetEntity = UserGroup.class)
//    private List<UserGroup> userGroups;


    public Authentication(){
        accountNonExpired = true;
        accountNonLocked = true;
        credentialsNonExpired = true;
        accountEnabled = true;
        creationDate = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return accountEnabled;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<BigDecimal> getWorkGroupIds() {
        return workGroupIds;
    }

    public void setWorkGroupIds(List<BigDecimal> workGroupIds) {
        this.workGroupIds = workGroupIds;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

//    public List<UserGroup> getUserGroups() {
//        return userGroups;
//    }
//
//    public void setUserGroups(List<UserGroup> userGroups) {
//        this.userGroups = userGroups;
//    }
}
