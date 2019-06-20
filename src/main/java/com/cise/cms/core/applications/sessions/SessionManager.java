package com.cise.cms.core.applications.sessions;

import com.cise.cms.core.applications.helper.SessionHelper;
import com.cise.cms.core.applications.models.MenuNav;
import com.cise.cms.core.modules.auth.models.dao.AppUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionManager {

    private Map<Integer, List<MenuNav>> nav = new HashMap<>();

    private AppUser appUser;

    public SessionManager() {

    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public void setMenuNav(List<MenuNav> menuNavList) {
        if (appUser != null) {
            nav.put(appUser.getRoleId(), menuNavList);
        }
    }

    public List<MenuNav> getMenuNav() {
        if (appUser != null) {
            return nav.get(appUser.getRoleId());
        } else {
            return new ArrayList<>();
        }
    }

    @Deprecated
    private void refreshMenu() {
        AppUser appUser = SessionHelper.getAppUser();
        if (appUser != null) {
            List<MenuNav> menuNavs = new ArrayList<>();
            menuNavs.add(new MenuNav(0, 100, "Dashboard", "home"));


            menuNavs.add(new MenuNav(0, 8, "Approval", "#approve"));

            menuNavs.add(new MenuNav(8, 80, "Approve SVP", "approval/spv"));
            menuNavs.add(new MenuNav(8, 81, "Approve DEPT HEAD", "approval/head"));
            menuNavs.add(new MenuNav(8, 82, "Approve Receive", "approval/receive"));
            menuNavs.add(new MenuNav(8, 83, "Approve Pickup", "approval/pickup"));

            menuNavs.add(new MenuNav(0, 1, "Control Panel", "#cpanel"));

            menuNavs.add(new MenuNav(1, 12, "Configuration", "config"));
            menuNavs.add(new MenuNav(1, 13, "Menu", "menu"));
            menuNavs.add(new MenuNav(1, 13, "Role", "role"));
            menuNavs.add(new MenuNav(1, 13, "Role Menu", "rolemenu"));

            menuNavs.add(new MenuNav(0, 2, "Master", "#master"));

            menuNavs.add(new MenuNav(2, 27, "Department", "department"));
            menuNavs.add(new MenuNav(2, 28, "Jabatan", "jabatan"));
            menuNavs.add(new MenuNav(2, 29, "Employee", "employee"));
            menuNavs.add(new MenuNav(2, 20, "Barang", "product"));

            menuNavs.add(new MenuNav(2, 21, "Vendor", "vendor"));

            menuNavs.add(new MenuNav(0, 7, "Vendor Product", "venpro"));
            menuNavs.add(new MenuNav(0, 7, "Procurement", "procurement"));

            menuNavs.add(new MenuNav(0, 6, "CRUD", "sample_crud"));
            menuNavs.clear();

            nav.put(appUser.getRoleId(), menuNavs);
        }
    }

}
