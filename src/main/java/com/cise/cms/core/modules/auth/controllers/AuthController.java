package com.cise.cms.core.modules.auth.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.applications.models.MenuNav;
import com.cise.cms.core.modules.auth.components.AppUserValidator;
import com.cise.cms.core.modules.auth.models.dao.AppUser;
import com.cise.cms.core.modules.auth.models.view.LoginMV;
import com.cise.cms.core.modules.auth.service.AppUserService;
import com.cise.cms.core.modules.menu.models.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController extends BaseControllers {

    @Autowired
    AppUserValidator validator;

    @Autowired
    AppUserService userService;

    @GetMapping("/")
    public String main(Model model) {
        AppUser appUser = getUser();
        if (appUser == null) {
            model.addAttribute("loginMV", new LoginMV());
            return "login";
        } else {
            return "redirect:/home";
        }
    }

    @PostMapping("/auth")
    public String login(HttpSession session, @Valid LoginMV loginMV, BindingResult bindingResult) {
        validator.validate(loginMV, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }
//        AppUser appUser = new AppUser();
//        appUser.setUsername(loginMV.getUsername());
//        appUser.setExternalId("NIK0001-001");
//        appUser.setUsername("zuliadin@gmail.com");
//        appUser.setRoleId(1);
//        Objects.requireNonNull(getHttpSession()).setAttribute("userLogin", appUser);
//        List<Menu> menuList = menuService.findAll();
//        List<MenuNav> navs = new ArrayList<>();
//        for(Menu m: menuList){
//            navs.add(new MenuNav(m.getParentId(), m.getMenuId(), m.getMenuName(), m.getModuleName()));
//        }
        AppUser appUser = userService.findByUsernameAndPassword(loginMV.getUsername(), loginMV.getPassword());
        if (appUser != null) {
            List<Menu> menuList = menuService.findAll();
            List<MenuNav> navs = new ArrayList<>();
            for (Menu m : menuList) {
                navs.add(new MenuNav(m.getParentId(), m.getMenuId(), m.getMenuName(), m.getModuleName()));
            }
            sessionManager.setAppUser(appUser);
            sessionManager.setMenuNav(navs);
        }
        session.setAttribute("userLogin", appUser);
        return "redirect:home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userLogin");
        return "redirect:";
    }

}
