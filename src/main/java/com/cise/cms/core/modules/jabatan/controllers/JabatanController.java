package com.cise.cms.core.modules.jabatan.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.jabatan.models.Jabatan;
import com.cise.cms.core.modules.jabatan.service.JabatanService;
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
public class JabatanController extends BaseControllers {

    @Autowired
    JabatanService service;

    @GetMapping("jabatan") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("jabatan crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.page((0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.page(search, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/jabatan/content", "content");
    }

    @GetMapping("jabatan/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("jabatan form with id : " + id);
        if (0 == id) {
            model.addAttribute("jabatan", new Jabatan());
        } else {
            Jabatan jabatan = service.findById(id);
            if (null == jabatan) return "redirect:/jabatan";
            model.addAttribute("jabatan", jabatan);
        }
        return validSession(model, "index", "contents/jabatan/form", "content");
    }

    @PostMapping("jabatan/save")
    public String save(Model model, @Valid @ModelAttribute("jabatan") Jabatan o, BindingResult bindingResult) {
        log.debug("jabatan save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("jabatan", o);
            return validSession(model, "index", "contents/jabatan/form", "content");
        }
        if (o.getJabatanId() > 0) {
            Jabatan tmp = service.findById(o.getJabatanId());
            tmp.setJabatanName(o.getJabatanName());
            signatureModel(tmp, false);
            service.createOrUpdate(tmp);
        } else {
            signatureModel(o, true);
            service.createOrUpdate(o);
        }
        return "redirect:/jabatan";
    }

    @PostMapping("jabatan/delete")
    @ResponseBody
    public Jabatan delete(@RequestBody Jabatan o) {
        log.debug("jabatan deleted : "+getUser().getUsername());
        log.debug("jabatan o");
        service.delete(o);
        return o;
    }
}
