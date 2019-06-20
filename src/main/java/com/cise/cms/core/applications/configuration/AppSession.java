package com.cise.cms.core.applications.configuration;

import com.cise.cms.core.applications.helper.SessionHelper;
import com.cise.cms.core.applications.models.MenuNav;
import com.cise.cms.core.applications.sessions.SessionManager;
import com.cise.cms.core.modules.menu.models.Menu;
import com.cise.cms.core.modules.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppSession {

    @Autowired
    MenuService menuService;

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
    @Scope("singleton")
    public SessionManager sessionManager(){
        SessionManager sessionManager = new SessionManager();
        List<Menu> menuList = menuService.findAll();
        List<MenuNav> navs = new ArrayList<>();
        for(Menu m: menuList){
            navs.add(new MenuNav(m.getParentId(), m.getMenuId(), m.getMenuName(), m.getModuleName()));
        }
        sessionManager.setAppUser(SessionHelper.getAppUser());
        sessionManager.setMenuNav(navs);
        return sessionManager;
    }
}
