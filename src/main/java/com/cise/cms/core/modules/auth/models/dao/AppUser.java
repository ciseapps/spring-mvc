package com.cise.cms.core.modules.auth.models.dao;

import com.cise.cms.core.applications.base.BaseModelSignature;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "app_user")
public class AppUser extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private int roleId;

    private String externalId;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be null")
    private String password;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
