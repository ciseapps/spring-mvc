package com.cise.cms.core.modules.menu.models;

import com.cise.cms.core.applications.base.BaseModelSignature;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "app_menu")
public class Menu extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuId;

    private String menuName;

    private String menuIcon;

    private String moduleName;

    private String typeMenu;

    private long seqNumber;

    private long parentId;

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getMenuId(){
        return menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName(){
        return menuName;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuIcon(){
        return menuIcon;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName(){
        return moduleName;
    }

    public void setTypeMenu(String typeMenu) {
        this.typeMenu = typeMenu;
    }

    public String getTypeMenu(){
        return typeMenu;
    }

    public void setSeqNumber(long seqNumber) {
        this.seqNumber = seqNumber;
    }

    public long getSeqNumber(){
        return seqNumber;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getParentId(){
        return parentId;
    }

}
