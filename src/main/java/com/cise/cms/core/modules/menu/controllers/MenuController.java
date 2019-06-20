package com.cise.cms.core.modules.menu.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.menu.models.Menu;
import com.cise.cms.core.modules.menu.modelview.MenuMV;
import com.cise.cms.core.modules.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController extends BaseControllers {

    @Autowired
    MenuService service;

    @GetMapping("menu") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("menu crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.page((0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.page(search, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/menu/content", "content");
    }

    @GetMapping("menu/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("menu form with id : " + id);
        MenuMV menuMV = new MenuMV();
        model.addAttribute("menuParent", service.findAllByRootMenu());
        if (0 == id) {
            model.addAttribute("menu", menuMV);
        } else {
            Menu menu = service.findById(id);
            if (null == menu) return "redirect:/menu";
            menuMV.initFrom(menu);
            model.addAttribute("menu", menuMV);
        }
        return validSession(model, "index", "contents/menu/form", "content");
    }

    @PostMapping("menu/save")
    public String save(Model model, @Valid MenuMV o, BindingResult bindingResult) {
        log.debug("menu save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("menu", o);
            return validSession(model, "index", "contents/menu/form", "content");
        }
        if (0 == o.getMenuId()) {
            Menu menu = new Menu();
            o.castToMenu(menu);
            service.createOrUpdate(menu);
        } else {
            Menu menu = service.findById(o.getMenuId());
            o.castToMenuForUpdate(menu);
            service.createOrUpdate(menu);
        }

        return "redirect:/menu";
    }

    @PostMapping("menu/delete")
    @ResponseBody
    public Menu delete(@RequestBody Menu o) {
        log.debug("menu deleted : " + getUser().getUsername());
        log.debug("menu o");
        service.delete(o);
        return o;
    }
}
