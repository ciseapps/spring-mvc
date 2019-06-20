package com.cise.cms.core.modules.role.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.role.components.RoleValidator;
import com.cise.cms.core.modules.role.models.Role;
import com.cise.cms.core.modules.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class RoleController extends BaseControllers {

    @Autowired
    RoleService service;

    @Autowired
    RoleValidator roleValidator;

    @GetMapping("role") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("sample crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.page((0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.page(search, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/role/content", "content");
    }

    @GetMapping("role/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("sample form with id : " + id);
        if (0 == id) {
            model.addAttribute("role", new Role());
        } else {
            Role role = service.findById(id);
            if (null == role) return "redirect:/role";
            model.addAttribute("role", role);
        }
        return validSession(model, "index", "contents/role/form", "content");
    }

    @PostMapping("role/save")
    public String save(Model model, @Valid Role role, BindingResult bindingResult) {
        log.debug("role save");
        roleValidator.validate(role, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("role", role);
            return validSession(model, "index", "contents/role/form", "content");
        }
        if (role.getRoleId() > 0) {
            Role tmp = service.findById(role.getRoleId());
            tmp.setRoleName(role.getRoleName());
            signatureModel(tmp, false);
            service.createOrUpdate(tmp);
        } else {
            signatureModel(role, true);
            service.createOrUpdate(role);
        }
        return "redirect:/role";
    }

    @PostMapping("role/delete")
    @ResponseBody
    public Role delete(@RequestBody Role role) {
        log.debug("role delete");
        if (Objects.nonNull(role)) service.delete(role);
        return role;
    }
}
