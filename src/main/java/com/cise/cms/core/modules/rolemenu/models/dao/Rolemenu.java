package com.cise.cms.core.modules.rolemenu.models.dao;

import com.cise.cms.core.applications.base.BaseModelSignature;
import com.cise.cms.core.modules.menu.models.Menu;
import com.cise.cms.core.modules.role.models.Role;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "app_role_menu")
public class Rolemenu extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleMenuId;

    private long roleId;

    private long menuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", referencedColumnName = "roleId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuId", referencedColumnName = "menuId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Menu menu;

    public void setRoleMenuId(long roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public long getRoleMenuId(){
        return roleMenuId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId(){
        return roleId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getMenuId(){
        return menuId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
