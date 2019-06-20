package com.cise.cms.core.modules.vendorproduct.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.modules.product.service.ProductService;
import com.cise.cms.core.modules.vendor.service.VendorService;
import com.cise.cms.core.modules.vendorproduct.models.VendorProduct;
import com.cise.cms.core.modules.vendorproduct.modelview.VendorProductMV;
import com.cise.cms.core.modules.vendorproduct.service.VendorproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class VendorproductController extends BaseControllers {

    @Autowired
    VendorproductService service;

    @Autowired
    VendorService vendorService;

    @Autowired
    ProductService productService;

    @GetMapping("venpro") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("vendorproduct crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.page((0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.page(search, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/vendorproduct/content", "content");
    }

    @GetMapping("venpro/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("vendorproduct form with id : " + id);
        VendorProductMV vp = new VendorProductMV();
        model.addAttribute("vendorproduct", vp);
        model.addAttribute("vendors", vendorService.findAll());
        model.addAttribute("products", productService.findAll());
        if (0 < id) {
            VendorProduct vendorproduct = service.findById(id);
            if (null == vendorproduct) return "redirect:/vendorproduct";
            vp.initFrom(vendorproduct);
            model.addAttribute("vendorproduct", vp);
        }
        return validSession(model, "index", "contents/vendorproduct/form", "content");
    }

    @PostMapping("venpro/save")
    public String save(Model model, @Valid @ModelAttribute("vendorproduct") VendorProductMV o, BindingResult bindingResult) {
        log.debug("vendorproduct save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("vendorproduct", o);
            model.addAttribute("vendors", vendorService.findAll());
            model.addAttribute("products", productService.findAll());
            return validSession(model, "index", "contents/vendorproduct/form", "content");
        }
        if (o.getVendorProductId() == 0) {
            VendorProduct vp = new VendorProduct();
            o.initVendor(vp);
            signatureModel(vp, true);
            service.createOrUpdate(vp);
        } else {
            VendorProduct vp = service.findById(o.getVendorProductId());
            o.initVendor(vp);
            signatureModel(vp, false);
            service.createOrUpdate(vp);
        }
        return "redirect:/venpro";
    }

    @PostMapping("venpro/delete")
    @ResponseBody
    public VendorProduct delete(@RequestBody VendorProduct o) {
        log.debug("vendorproduct deleted : " + getUser().getUsername());
        log.debug("vendorproduct o");
        service.delete(o);
        return o;
    }
}
