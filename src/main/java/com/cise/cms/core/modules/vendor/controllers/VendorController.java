package com.cise.cms.core.modules.vendor.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.vendor.models.Vendor;
import com.cise.cms.core.modules.vendor.service.VendorService;
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
public class VendorController extends BaseControllers {

    @Autowired
    VendorService service;

    @GetMapping("vendor") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("vendor crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.page((0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.page(search, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/vendor/content", "content");
    }

    @GetMapping("vendor/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("vendor form with id : " + id);
        if (0 == id) {
            model.addAttribute("vendor", new Vendor());
        } else {
            Vendor vendor = service.findById(id);
            if (null == vendor) return "redirect:/vendor";
            model.addAttribute("vendor", vendor);
        }
        return validSession(model, "index", "contents/vendor/form", "content");
    }

    @PostMapping("vendor/save")
    public String save(Model model, @Valid Vendor o, BindingResult bindingResult) {
        log.debug("vendor save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("vendor", o);
            return validSession(model, "index", "contents/vendor/form", "content");
        }
        signatureModel(o, o.getVendorId() == 0);
        service.createOrUpdate(o);
        return "redirect:/vendor";
    }

    @PostMapping("vendor/delete")
    @ResponseBody
    public Vendor delete(@RequestBody Vendor o) {
        log.debug("vendor deleted : " + getUser().getUsername());
        log.debug("vendor o");
        service.delete(o);
        return o;
    }
}
