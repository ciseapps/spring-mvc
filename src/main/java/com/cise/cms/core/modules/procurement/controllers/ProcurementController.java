package com.cise.cms.core.modules.procurement.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.applications.constants.AppConstants;
import com.cise.cms.core.applications.services.FileStorageService;
import com.cise.cms.core.modules.procurement.models.Procurement;
import com.cise.cms.core.modules.procurement.models.ProcurementDetail;
import com.cise.cms.core.modules.procurement.modelview.ProcurementFormMV;
import com.cise.cms.core.modules.procurement.modelview.ProcurementStatusMV;
import com.cise.cms.core.modules.procurement.service.ProcurementService;
import com.cise.cms.core.modules.vendor.models.VendorPrice;
import com.cise.cms.core.modules.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProcurementController extends BaseControllers {

    @Autowired
    ProcurementService service;

    @Autowired
    VendorService vendorService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("procurement") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "status", required = false, defaultValue = "2") int status,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("procurement crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        model.addAttribute("status", ProcurementStatusMV.getAllStatus());
        model.addAttribute("statusSelected", status);
        if (null == search) {
            model.addAttribute("page", service.pageStatusProc(status, (0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.pageSearchAndStatusProc(search, status, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/procurement/content", "content");
    }

    @GetMapping("procurement/form")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("procurement form with id : " + id);
        ProcurementFormMV pfmv = new ProcurementFormMV();
        model.addAttribute("procurement", pfmv);
        if (0 < id) {
            Procurement procurement = service.findById(id);
            if (null == procurement) return "redirect:/procurement";
            log.debug("status : " + procurement.getStatusProc());
            pfmv.initFrom(procurement);
            model.addAttribute("procurement", pfmv);
        }
        return validSession(model, "index", "contents/procurement/form_add_vendor", "content");
    }

    @PostMapping("procurement/save")
    public String save(Model model, @Valid @ModelAttribute("procurement") ProcurementFormMV o, BindingResult bindingResult) {
        log.debug("procurement save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("procurement", o);
            return validSession(model, "index", "contents/procurement/form", "content");
        }
        return "redirect:/procurement";
    }

    @PostMapping("procurement/delete")
    @ResponseBody
    public Procurement delete(@RequestBody Procurement o) {
        log.debug("procurement deleted : " + getUser().getUsername());
        log.debug("procurement o");
        service.delete(o);
        return o;
    }

    @PostMapping("procurement/reject")
    @ResponseBody
    public Procurement reject(@RequestBody Procurement o) {
        log.info("reject request : " + o.getRequestId());
        Procurement tmp = service.findById(o.getRequestId());
        if (tmp != null) {
            tmp.setStatusProc(AppConstants.Proc.reject);
            signatureModel(tmp, false);
            service.createOrUpdate(tmp);
        }
        return o;
    }

    @PostMapping("procurement/load_vendor_by_product")
    @ResponseBody
    public List<VendorPrice> loadVendorByProductId(@RequestBody ProcurementDetail pfmv) {
        log.debug("info product : " + pfmv.getProductId());
        return vendorService.findByProductId(Long.valueOf(pfmv.getProductId()));
    }
}
