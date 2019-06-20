package com.cise.cms.core.modules.config.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.config.models.Config;
import com.cise.cms.core.modules.config.service.ConfigService;
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
public class ConfigController extends BaseControllers {

    @Autowired
    ConfigService service;

    @GetMapping("config") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("config crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.page((0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.page(search, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/config/content", "content");
    }

    @GetMapping("config/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("config form with id : " + id);
        if (0 == id) {
            model.addAttribute("config", new Config());
        } else {
            Config config = service.findById(id);
            if (null == config) return "redirect:/config";
            model.addAttribute("config", config);
        }
        return validSession(model, "index", "contents/config/form", "content");
    }

    @PostMapping("config/save")
    public String save(Model model, @Valid Config o, BindingResult bindingResult) {
        log.debug("config save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("config", o);
            return validSession(model, "index", "contents/config/form", "content");
        }
        service.createOrUpdate(o);
        return "redirect:/config";
    }

    @PostMapping("config/delete")
    @ResponseBody
    public Config delete(@RequestBody Config o) {
        log.debug("config deleted : "+getUser().getUsername());
        log.debug("config o");
        service.delete(o);
        return o;
    }
}
