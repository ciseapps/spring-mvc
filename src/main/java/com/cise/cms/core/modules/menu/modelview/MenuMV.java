package com.cise.cms.core.modules.menu.modelview;

import com.cise.cms.core.applications.base.BaseModelSignature;
import com.cise.cms.core.modules.menu.models.Menu;

import javax.persistence.*;

public class MenuMV extends BaseModelSignature {

    private long menuId;

    private String menuName;

    private String menuIcon;

    private String moduleName;

    private String typeMenu;

    private long seqNumber;

    private String parentId;

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setTypeMenu(String typeMenu) {
        this.typeMenu = typeMenu;
    }

    public String getTypeMenu() {
        return typeMenu;
    }

    public void setSeqNumber(long seqNumber) {
        this.seqNumber = seqNumber;
    }

    public long getSeqNumber() {
        return seqNumber;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void initFrom(Menu menu) {
        if (menu != null) {
            this.parentId = String.valueOf(menu.getParentId());
            this.menuId = menu.getMenuId();
            this.menuName = menu.getMenuName();
            this.moduleName = menu.getModuleName();
            this.menuIcon = menu.getMenuIcon();
            this.seqNumber = menu.getSeqNumber();
            this.typeMenu = menu.getTypeMenu();
        }
    }

    public void castToMenu(Menu menu) {
        if (menu != null) {
            menu.setParentId(Long.valueOf(parentId));
            menu.setMenuId(menuId);
            menu.setMenuName(menuName);
            menu.setModuleName(moduleName);
            menu.setSeqNumber(seqNumber);
            menu.setTypeMenu(typeMenu);
        }
    }

    public void castToMenuForUpdate(Menu menu) {
        if (menu != null) {
            menu.setParentId(Long.valueOf(parentId));
            menu.setMenuName(menuName);
            menu.setModuleName(moduleName);
            menu.setSeqNumber(seqNumber);
            menu.setTypeMenu(typeMenu);
        }
    }
}
