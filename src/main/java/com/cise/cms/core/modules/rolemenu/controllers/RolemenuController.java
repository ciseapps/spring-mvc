package com.cise.cms.core.modules.rolemenu.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.menu.service.MenuService;
import com.cise.cms.core.modules.role.service.RoleService;
import com.cise.cms.core.modules.rolemenu.models.dao.Rolemenu;
import com.cise.cms.core.modules.rolemenu.models.view.RolemenuMV;
import com.cise.cms.core.modules.rolemenu.service.RolemenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class RolemenuController extends BaseControllers {

    @Autowired
    RolemenuService service;

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @GetMapping("rolmen") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("rolemenu crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.page((0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.page(search, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/rolemenu/content", "content");
    }

    @GetMapping("rolmen/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("rolemenu form with id : " + id);
        RolemenuMV rolemenuMV = new RolemenuMV();
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("menus", menuService.findAll());
        model.addAttribute("rolemenu", rolemenuMV);
        if (0 < id) {
            Rolemenu rolemenu = service.findById(id);
            if (null == rolemenu) return "redirect:/rolmen";
            rolemenuMV.initFrom(rolemenu);
        }
        return validSession(model, "index", "contents/rolemenu/form", "content");
    }

    @PostMapping("rolmen/save")
    public String save(Model model, @Valid RolemenuMV o, BindingResult bindingResult) {
        log.debug("rolemenu save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("rolemenu", o);
            return validSession(model, "index", "contents/rolemenu/form", "content");
        }
        if (0 == o.getRoleMenuId()) {
            Rolemenu rolemenu = new Rolemenu();
            o.castTo(rolemenu);
            signatureModel(rolemenu, true);
            service.createOrUpdate(rolemenu);
        } else {
            Rolemenu rolemenu = service.findById(o.getRoleMenuId());
            o.castToForUpdate(rolemenu);
            signatureModel(rolemenu, false);
            service.createOrUpdate(rolemenu);
        }
        return "redirect:/rolmen";
    }

    @PostMapping("rolmen/delete")
    @ResponseBody
    public Rolemenu delete(@RequestBody Rolemenu o) {
        log.debug("rolemenu deleted : " + getUser().getUsername());
        log.debug("rolemenu o");
        service.delete(o);
        return o;
    }
}
