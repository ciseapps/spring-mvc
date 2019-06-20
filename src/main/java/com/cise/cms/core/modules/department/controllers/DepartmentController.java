package com.cise.cms.core.modules.department.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.department.models.Department;
import com.cise.cms.core.modules.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class DepartmentController extends BaseControllers {

    @Autowired
    DepartmentService service;

    @GetMapping("department") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("department crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.page((0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.page(search, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/department/content", "content");
    }

    @GetMapping("department/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("department form with id : " + id);
        if (0 == id) {
            model.addAttribute("department", new Department());
        } else {
            Department department = service.findById(id);
            if (null == department) return "redirect:/department";
            model.addAttribute("department", department);
        }
        return validSession(model, "index", "contents/department/form", "content");
    }

    @PostMapping("department/save")
    public String save(Model model, @Valid @ModelAttribute("department") Department o, BindingResult bindingResult) {
        log.debug("department save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("department", o);
            return validSession(model, "index", "contents/department/form", "content");
        }
        if (o.getDepartmentId() > 0) {
            Department tmp = service.findById(o.getDepartmentId());
            tmp.setDepartmentName(o.getDepartmentName());
            signatureModel(tmp, false);
            service.createOrUpdate(tmp);
        } else {
            signatureModel(o, true);
            service.createOrUpdate(o);
        }
        return "redirect:/department";
    }

    @PostMapping("department/delete")
    @ResponseBody
    public Department delete(@RequestBody Department o) {
        log.debug("department deleted : " + getUser().getUsername());
        log.debug("department o");
        service.delete(o);
        return o;
    }
}
