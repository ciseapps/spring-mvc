package com.cise.cms.core.modules.rolemenu.models.view;

import com.cise.cms.core.modules.rolemenu.models.dao.Rolemenu;

public class RolemenuMV {

    private long roleMenuId;

    private String roleId;

    private String menuId;

    public long getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(long roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }



    public void initFrom(Rolemenu rolemenu) {
        if (rolemenu != null) {
            menuId = String.valueOf(rolemenu.getMenuId());
            roleId = String.valueOf(rolemenu.getRoleId());
            roleMenuId = rolemenu.getRoleMenuId();

        }
    }

    public void castTo(Rolemenu rolemenu) {
        if (rolemenu != null) {
            rolemenu.setRoleMenuId(roleMenuId);
            rolemenu.setMenuId(Long.valueOf(menuId));
            rolemenu.setRoleId(Long.valueOf(roleId));
        }
    }

    public void castToForUpdate(Rolemenu rolemenu) {
        if (rolemenu != null) {
            rolemenu.setMenuId(Long.valueOf(menuId));
            rolemenu.setRoleId(Long.valueOf(roleId));
        }
    }
}
