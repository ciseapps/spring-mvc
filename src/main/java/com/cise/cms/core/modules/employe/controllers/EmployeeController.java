package com.cise.cms.core.modules.employe.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.auth.models.dao.AppUser;
import com.cise.cms.core.modules.auth.service.AppUserService;
import com.cise.cms.core.modules.department.service.DepartmentService;
import com.cise.cms.core.modules.employe.components.EmployeeValidator;
import com.cise.cms.core.modules.employe.models.Employee;
import com.cise.cms.core.modules.employe.modelview.EmployeeFormMV;
import com.cise.cms.core.modules.employe.service.EmployeeService;
import com.cise.cms.core.modules.jabatan.service.JabatanService;
import com.cise.cms.core.modules.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class EmployeeController extends BaseControllers {

    @Autowired
    EmployeeValidator employeeValidator;

    @Autowired
    EmployeeService service;

    @Autowired
    AppUserService appUserService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    RoleService roleService;

    @Autowired
    JabatanService jabatanService;

    @GetMapping("employee") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("employee page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            Page<Employee> employees = service.page((0 == page ? page : page - 1));
            model.addAttribute("page", employees);
        } else {
            Page<Employee> employees = service.page(search, (0 == page ? page : page - 1));
            model.addAttribute("page", employees);
        }
        return validSession(model, "index", "contents/employee/content", "content");
    }

    @GetMapping("employee/form")
    public String form(Model model, @RequestParam(value = "nik", required = false, defaultValue = "") String nik) {
        log.debug("employee form with nik : " + nik);
        EmployeeFormMV mv = new EmployeeFormMV();
        model.addAttribute("mv", mv);
        model.addAttribute("departmentList", departmentService.findAll());
        model.addAttribute("jabatanList", jabatanService.findAll());
        model.addAttribute("roleList", roleService.findAll());
        if (!"".equalsIgnoreCase(nik)) {
            Employee o = service.findByNik(nik);
            if (null == o) return "redirect:/employee";
            AppUser appUser = appUserService.findByExternalId(nik);
            mv.initFrom(o, appUser);
        }
        return validSession(model, "index", "contents/employee/form", "content");
    }

    @PostMapping("employee/save")
    public String save(Model model, @Valid @ModelAttribute("mv") EmployeeFormMV o, BindingResult bindingResult) {
        employeeValidator.validate(o, bindingResult);
        log.debug("employee save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("mv", o);
            model.addAttribute("departmentList", departmentService.findAll());
            model.addAttribute("jabatanList", jabatanService.findAll());
            model.addAttribute("roleList", roleService.findAll());
            return validSession(model, "index", "contents/employee/form", "content");
        }
        if (o.getId() == 0) {
            Employee employee = o.initEmployee(new Employee());
            signatureModel(employee, true);
            AppUser appUser = o.initAppUser(new AppUser());
            appUser.setExternalId(o.getNik());
            signatureModel(appUser, true);
            appUserService.createOrUpdate(appUser);
            service.createOrUpdate(employee);
        } else {
            Employee e = service.findByNik(o.getNik());
            signatureModel(e, false);
            o.initEmployee(e);
            service.createOrUpdate(e);
            AppUser appUser = appUserService.findByExternalId(e.getNik());
            o.initAppUser(appUser);
            signatureModel(appUser, false);
            appUserService.createOrUpdate(appUser);
        }
        return "redirect:/employee";
    }

    @PostMapping("employee/delete")
    @ResponseBody
    public Employee delete(@RequestBody Employee o) {
        log.debug("employee delete");
        if (Objects.nonNull(o)) {
            service.delete(o);
            AppUser appUser = appUserService.findByExternalId(o.getNik());
            if (Objects.nonNull(appUser)) appUserService.delete(appUser);
        }

        return o;
    }
}
