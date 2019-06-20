package com.cise.cms.core.modules.product.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.product.models.Product;
import com.cise.cms.core.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProductController extends BaseControllers {

    @Autowired
    ProductService service;

    @GetMapping("product") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("product crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.page((0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.page(search, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/product/content", "content");
    }

    @GetMapping("product/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("product form with id : " + id);
        if (0 == id) {
            model.addAttribute("product", new Product());
        } else {
            Product product = service.findById(id);
            if (null == product) return "redirect:/product";
            model.addAttribute("product", product);
        }
        return validSession(model, "index", "contents/product/form", "content");
    }

    @PostMapping("product/save")
    public String save(Model model, @Valid @ModelAttribute("product") Product o, BindingResult bindingResult) {
        log.debug("product save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", o);
            return validSession(model, "index", "contents/product/form", "content");
        }
        signatureModel(o, o.getProductId() == 0);
        service.createOrUpdate(o);
        return "redirect:/product";
    }

    @PostMapping("product/delete")
    @ResponseBody
    public Product delete(@RequestBody Product o) {
        log.debug("product deleted : " + getUser().getUsername());
        log.debug("product o");
        service.delete(o);
        return o;
    }
}
