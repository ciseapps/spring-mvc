package com.cise.cms.core.modules.role.models;

import com.cise.cms.core.applications.base.BaseModelSignature;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "app_role")
public class Role extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleId;

    @NotNull(message = "Role cannot be null")
    @NotBlank(message = "Role cannot be null")
    @Size(min = 4, max = 30, message = "Role cannot be null min char 4 and max 30")
    private String roleName;

    public Role() {
    }

    public Role(long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
