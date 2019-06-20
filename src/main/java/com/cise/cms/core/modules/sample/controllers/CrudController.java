package com.cise.cms.core.modules.sample.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.sample.models.Sample;
import com.cise.cms.core.modules.sample.service.SampleService;
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
public class CrudController extends BaseControllers {

    @Autowired
    SampleService service;

    @GetMapping("sample_crud") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("sample crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            Page<Sample> samplePage = service.page((0 == page ? page : page - 1));
            model.addAttribute("page", service.page((0 == page ? page : page - 1)));
        } else {
            Page<Sample> samplePage = service.page(search, (0 == page ? page : page - 1));
            model.addAttribute("page", service.page(search, (0 == page ? page : page - 1)));
        }

        return validSession(model, "index", "contents/sample_crud/content", "content");
    }

    @GetMapping("sample_crud/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("sample form with id : " + id);
        if (0 == id) {
            model.addAttribute("sample", new Sample());
        } else {
            Sample sample = service.findById(id);
            if (null == sample) return "redirect:/sample_crud";
            model.addAttribute("sample", sample);
        }
        return validSession(model, "index", "contents/sample_crud/form", "content");
    }

    @PostMapping("sample_crud/save")
    public String save(Model model, @Valid Sample sample, BindingResult bindingResult) {
        log.debug("sample save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("sample", sample);
            return validSession(model, "index", "contents/sample_crud/form", "content");
        }
        service.createOrUpdate(sample);
        return "redirect:/sample_crud";
    }

    public void update() {
    }

    @PostMapping("sample_crud/delete")
    @ResponseBody
    public Sample delete(@RequestBody Sample sample) {
        log.debug("user deleted : "+getUser().getUsername());
        //log.debug("sample delete");
        //service.delete(sample);
        return sample;
    }
}
