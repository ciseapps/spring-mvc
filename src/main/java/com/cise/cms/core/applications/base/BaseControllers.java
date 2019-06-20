package com.cise.cms.core.applications.base;

import com.cise.cms.core.applications.helper.SessionHelper;
import com.cise.cms.core.modules.auth.models.dao.AppUser;
import com.cise.cms.core.applications.models.MenuNav;
import com.cise.cms.core.applications.sessions.SessionManager;
import com.cise.cms.core.modules.menu.models.Menu;
import com.cise.cms.core.modules.menu.service.MenuService;
import com.cise.cms.core.modules.rolemenu.models.dao.Rolemenu;
import com.cise.cms.core.modules.rolemenu.service.RolemenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class BaseControllers {

    protected final Logger log = LogManager.getLogger(getClass());

    @Autowired
    protected SessionManager sessionManager;

    @Autowired
    protected MenuService menuService;

    @Autowired
    protected RolemenuService rolemenuService;

    public static HttpSession getHttpSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            return attr.getRequest().getSession(true);
        } else {
            return null;
        }
    }

    protected void setDummyUser() {
        AppUser appUser = new AppUser();
        appUser.setExternalId("NIK0001-001");
        appUser.setUsername("zuliadin@gmail.com");
        appUser.setRoleId(1);
        Objects.requireNonNull(getHttpSession()).setAttribute("userLogin", appUser);
        sessionManager.setAppUser(appUser);
    }

    protected void setDummyMenu() {
        if (getUser() == null) return;
        log.debug("user login with role id : " + getUser().getRoleId());
        List<Rolemenu> rolemenus = rolemenuService.findMenuByRoleId(getUser().getRoleId());
        List<Long> ids = new ArrayList<>();
        for (Rolemenu r : rolemenus) {
            ids.add(r.getMenuId());
        }
        List<Menu> menuList = menuService.findByIdIn(ids);
        List<MenuNav> navs = new ArrayList<>();
        for (Menu m : menuList) {
            navs.add(new MenuNav(m.getParentId(), m.getMenuId(), m.getMenuName(), m.getModuleName()));
        }
        sessionManager.setMenuNav(navs);
    }

    public String validSession(Model model, String destination, String template, String templateId) {
//        setDummyUser();
        setDummyMenu();
        AppUser appUser = getUser();
        if (appUser != null) {
            model.addAttribute("user", appUser);
            model.addAttribute("menuNavs", getMenuNavList());
            model.addAttribute("template", template);
            model.addAttribute("templateId", templateId);
            return destination;
        }
        return "redirect:";
    }

    public AppUser getUser() {
        return SessionHelper.getAppUser();
    }

    public List<MenuNav> getMenuNavList() {
        return sessionManager.getMenuNav();
    }

    public void signatureModel(BaseModelSignature model, boolean isCreated) {
        SessionHelper.signature(model, isCreated);
    }

}
