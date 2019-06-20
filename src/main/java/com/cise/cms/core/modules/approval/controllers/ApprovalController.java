package com.cise.cms.core.modules.approval.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.applications.constants.AppConstants;
import com.cise.cms.core.applications.services.FileStorageService;
import com.cise.cms.core.modules.approval.components.ApprovalValidator;
import com.cise.cms.core.modules.auth.models.dao.AppUser;
import com.cise.cms.core.modules.procurement.models.Procurement;
import com.cise.cms.core.modules.procurement.modelview.ProcurementFormMV;
import com.cise.cms.core.modules.procurement.service.ProcurementService;
import com.cise.cms.core.modules.product.models.Product;
import com.cise.cms.core.modules.product.service.ProductService;
import com.cise.cms.core.modules.vendor.models.Vendor;
import com.cise.cms.core.modules.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * approve spv
 * approve head
 * approve receive
 * approve pickup
 */
@Controller
public class ApprovalController extends BaseControllers {

    @Autowired
    ProcurementService service;

    @Autowired
    ProductService productService;

    @Autowired
    VendorService vendorService;

    @Autowired
    ApprovalValidator approvalValidator;

    @Autowired
    private FileStorageService fileStorageService;

    /* dept head */
    @GetMapping("approval/head_dept") //search
    public String content(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "search", required = false) String search) {
        log.debug("procurement crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.pageStatusProc(AppConstants.Proc.newRequest, (0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.pageSearchAndStatusProc(search, AppConstants.Proc.newRequest, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/approval/content_head_dept", "spv");
    }

    /* dept head */
    @GetMapping("approval/form_head_dept")
    public String form(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("procurement form with id : " + id);
        ProcurementFormMV pfmv = new ProcurementFormMV();
        model.addAttribute("procurement", pfmv);
        if (0 == id) {
            // model.addAttribute("procurementDetail", service.findByRequestId(0));
        } else {

            Procurement procurement = service.findById(id);
            if (null == procurement) return "redirect:/procurement";
//            log.debug("product selected : " + procurement.getProductId());
//            Product product = productService.findById(procurement.getProductId());
//            if (product != null) pfmv.setProductName(product.getProductName());
//            if (procurement.getVendorId() != null) {
//                Vendor vendor = vendorService.findById(procurement.getVendorId());
//                if (vendor != null) pfmv.setVendorName(vendor.getVendorName());
//            }
            pfmv.initFrom(procurement);
            //model.addAttribute("procurementDetail", service.findByRequestId(procurement.getRequestId()));
//            model.addAttribute("vendors", vendorService.findByProductId(procurement.getProductId()));

        }
        return validSession(model, "index", "contents/approval/form_approve_head_dept", "content");
    }

    /* dept head */
    @PostMapping("approval/save_head_dept")
    public String save(Model model, @Valid @ModelAttribute("procurement") ProcurementFormMV o, BindingResult bindingResult) {
        log.debug("procurement save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("procurement", o);
            return validSession(model, "index", "contents/approval/form_approve_head_dept", "content");
        }
        Procurement procurement = service.findById(o.getRequestId());
        AppUser appUser = getUser();
        procurement.setStatusProc(AppConstants.Proc.apvKdep);
        procurement.setNikKdep(appUser.getExternalId());
        procurement.setApvKdepDate(new Date());
        signatureModel(procurement, false);
        service.createOrUpdate(procurement);
        return "redirect:/approval/head_dept";
    }

    /* dept procurement */
    @GetMapping("approval/prc_admin") //search
    public String contentProcurement(Model model,
                                     @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                     @RequestParam(value = "search", required = false) String search) {
        log.debug("procurement crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            Page<Procurement> procurementPage = service.pageStatusProc(AppConstants.Proc.apvKdep, (0 == page ? page : page - 1));
            model.addAttribute("page", procurementPage);
        } else {
            Page<Procurement> procurementPage = service.pageSearchAndStatusProc(search, AppConstants.Proc.apvKdep, (0 == page ? page : page - 1));
            model.addAttribute("page", procurementPage);
        }
        return validSession(model, "index", "contents/approval/content_procurement", "procurement");
    }

    /* dept procurement */
    @GetMapping("approval/form_procurement")
    public String formProcurement(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("procurement form with id : " + id);
        if (0 == id) {
            model.addAttribute("procurement", new ProcurementFormMV());
        } else {
            ProcurementFormMV pfmv = new ProcurementFormMV();
            Procurement procurement = service.findById(id);
            if (null == procurement) return "redirect:/procurement";
//            log.debug("product selected : " + procurement.getProductId());
//            Product product = productService.findById(procurement.getProductId());
//            if (product != null) pfmv.setProductName(product.getProductName());
//            if (procurement.getVendorId() != null) {
//                Vendor vendor = vendorService.findById(procurement.getVendorId());
//                if (vendor != null) pfmv.setVendorName(vendor.getVendorName());
//            }
            pfmv.initFrom(procurement);
//            model.addAttribute("vendors", vendorService.findByProductId(procurement.getProductId()));
            model.addAttribute("procurement", pfmv);
        }
        return validSession(model, "index", "contents/approval/form_approve_procurement", "procurement");
    }

    /* dept procurement */
    @PostMapping("approval/save_procurement")
    public String saveProcurement(Model model, @Valid @ModelAttribute("procurement") ProcurementFormMV o, BindingResult bindingResult) {
        log.debug("procurement save");
        approvalValidator.validate(o, bindingResult);
        Procurement procurement = service.findById(o.getRequestId());
        if (bindingResult.hasErrors()) {
//            Product product = productService.findById(procurement.getProductId());
//            if (product != null) o.setProductName(product.getProductName());
//            if (procurement.getVendorId() != null) {
//                Vendor vendor = vendorService.findById(procurement.getVendorId());
//                if (vendor != null) o.setVendorName(vendor.getVendorName());
//            }
            o.initFrom(procurement);
//            model.addAttribute("vendors", vendorService.findByProductId(procurement.getProductId()));
            model.addAttribute("procurement", o);
            return validSession(model, "index", "contents/approval/form_approve_procurement", "procurement");
        }
        o.castToProcurement(procurement);
        procurement.setStatusProc(AppConstants.Proc.apvProc);
        String offerDoc = fileStorageService.storeFile(o.getFileVenReqDoc());
        if (offerDoc != null) procurement.setVenReqDoc(offerDoc);
        AppUser appUser = getUser();
        procurement.setNikProc(appUser.getExternalId());
        procurement.setApvProcDate(new Date());
        service.createOrUpdate(procurement);
        return "redirect:/approval/procurement";
    }

    /* head */
    @GetMapping("approval/head_prc") //search
    public String contentHead(Model model,
                              @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                              @RequestParam(value = "search", required = false) String search) {
        log.debug("procurement crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.pageStatusProc(AppConstants.Proc.apvProc, (0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.pageSearchAndStatusProc(search, AppConstants.Proc.apvProc,(0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/approval/content_head_prc", "head");
    }

    /* head */
    @GetMapping("approval/form_head_prc")
    public String formHead(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("procurement form with id : " + id);
        if (0 == id) {
            model.addAttribute("procurement", new ProcurementFormMV());
        } else {
            ProcurementFormMV pfmv = new ProcurementFormMV();
            Procurement procurement = service.findById(id);
            if (null == procurement) return "redirect:/procurement";
//            log.debug("product selected : " + procurement.getProductId());
//            Product product = productService.findById(procurement.getProductId());
//            if (product != null) pfmv.setProductName(product.getProductName());
//            if (procurement.getVendorId() != null) {
//                Vendor vendor = vendorService.findById(procurement.getVendorId());
//                if (vendor != null) pfmv.setVendorName(vendor.getVendorName());
//            }
            pfmv.initFrom(procurement);
            model.addAttribute("procurement", pfmv);
        }
        return validSession(model, "index", "contents/approval/form_approve_head_prc", "content");
    }

    /* head */
    @PostMapping("approval/save_head_prc")
    public String saveHead(Model model, @Valid @ModelAttribute("procurement") ProcurementFormMV o, BindingResult bindingResult) {
        log.debug("procurement save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("procurement", o);
            return validSession(model, "index", "contents/approval/form_approve_head_prc", "content");
        }
        Procurement procurement = service.findById(o.getRequestId());
        AppUser appUser = getUser();
        procurement.setStatusProc(AppConstants.Proc.apvKdepProc);
        procurement.setNikKdepProc(appUser.getExternalId());
        procurement.setApvKdepProcDate(new Date());
        signatureModel(procurement, false);
        service.createOrUpdate(procurement);
        return "redirect:/approval/head_prc";
    }

    /* receive */
    @GetMapping("approval/receive") //search
    public String contentReceive(Model model,
                                 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                 @RequestParam(value = "search", required = false) String search) {
        log.debug("procurement crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.pageStatusProc(AppConstants.Proc.apvKdepProc, (0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.pageSearchAndStatusProc(search, AppConstants.Proc.apvKdepProc,(0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/approval/content_receive", "receive");
    }

    /* receive */
    @GetMapping("approval/form_receive")
    public String formReceive(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("procurement form with id : " + id);

        if (0 == id) {
            model.addAttribute("procurement", new ProcurementFormMV());
        } else {
            ProcurementFormMV pfmv = new ProcurementFormMV();
            Procurement procurement = service.findById(id);
            if (null == procurement) return "redirect:/procurement";
//            log.debug("product selected : " + procurement.getProductId());
//            Product product = productService.findById(procurement.getProductId());
//            if (product != null) pfmv.setProductName(product.getProductName());
//            if (procurement.getVendorId() != null) {
//                Vendor vendor = vendorService.findById(procurement.getVendorId());
//                if (vendor != null) pfmv.setVendorName(vendor.getVendorName());
//            }
            pfmv.initFrom(procurement);
//            model.addAttribute("vendors", vendorService.findByProductId(procurement.getProductId()));
            model.addAttribute("procurement", pfmv);
        }
        return validSession(model, "index", "contents/approval/form_approve_receive", "content");
    }

    /* receive */
    @PostMapping("approval/save_receive")
    public String saveReceive(Model model, @Valid @ModelAttribute("procurement") ProcurementFormMV o, BindingResult bindingResult) {
        log.debug("procurement save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("procurement", o);
            return validSession(model, "index", "contents/approval/form_receive", "content");
        }
        Procurement procurement = service.findById(o.getRequestId());
        AppUser appUser = getUser();
        procurement.setStatusProc(AppConstants.Proc.apvRcv);
        procurement.setNikReceive(appUser.getExternalId());
        procurement.setReceiveDate(new Date());
        signatureModel(procurement, false);
        service.createOrUpdate(procurement);
        return "redirect:/approval/receive";
    }

    /* receive approve */
    @GetMapping("approval/receive_approve") //search
    public String contentReceiveApprove(Model model,
                                        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                        @RequestParam(value = "search", required = false) String search) {
        log.debug("procurement crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.pageStatusProc(AppConstants.Proc.apvRcv, (0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.pageSearchAndStatusProc(search, AppConstants.Proc.apvRcv,(0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/approval/content_receive_approve", "receive");
    }

    /* receive approve */
    @GetMapping("approval/form_receive_approve")
    public String formReceiveApprove(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("procurement form with id : " + id);

        if (0 == id) {
            model.addAttribute("procurement", new ProcurementFormMV());
        } else {
            ProcurementFormMV pfmv = new ProcurementFormMV();
            Procurement procurement = service.findById(id);
            if (null == procurement) return "redirect:/procurement";
//            log.debug("product selected : " + procurement.getProductId());
//            Product product = productService.findById(procurement.getProductId());
//            if (product != null) pfmv.setProductName(product.getProductName());
//            if (procurement.getVendorId() != null) {
//                Vendor vendor = vendorService.findById(procurement.getVendorId());
//                if (vendor != null) pfmv.setVendorName(vendor.getVendorName());
//            }
            pfmv.initFrom(procurement);
//            model.addAttribute("vendors", vendorService.findByProductId(procurement.getProductId()));
            model.addAttribute("procurement", pfmv);
        }
        return validSession(model, "index", "contents/approval/form_approve_receive_approve", "content");
    }

    /* receive approve */
    @PostMapping("approval/save_receive_approve")
    public String saveReceiveApprove(Model model, @Valid @ModelAttribute("procurement") ProcurementFormMV o, BindingResult bindingResult) {
        log.debug("procurement save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("procurement", o);
            return validSession(model, "index", "contents/approval/form_receive_approve", "content");
        }
        Procurement procurement = service.findById(o.getRequestId());
        AppUser appUser = getUser();
        procurement.setStatusProc(AppConstants.Proc.apvKdepRcv);
        procurement.setNikReceiveApprove(appUser.getExternalId());
        procurement.setApvReceiveDate(new Date());
        signatureModel(procurement, false);
        service.createOrUpdate(procurement);
        return "redirect:/approval/receive_approve";
    }

    /* pickup */
    @GetMapping("approval/pickup") //search
    public String contentPickup(Model model,
                                @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                @RequestParam(value = "search", required = false) String search) {
        log.debug("procurement crud page : " + page + " and search key : " + search);
        model.addAttribute("search", search);
        if (null == search) {
            model.addAttribute("page", service.pageStatusProc(AppConstants.Proc.apvKdepRcv, (0 == page ? page : page - 1)));
        } else {
            model.addAttribute("page", service.pageSearchAndStatusProc(search,AppConstants.Proc.apvKdepRcv, (0 == page ? page : page - 1)));
        }
        return validSession(model, "index", "contents/approval/content_pickup", "pickup");
    }

    /* pickup */
    @GetMapping("approval/form_pickup")
    public String formPickup(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        log.debug("procurement form with id : " + id);

        if (0 == id) {
            model.addAttribute("procurement", new ProcurementFormMV());
        } else {
            ProcurementFormMV pfmv = new ProcurementFormMV();
            Procurement procurement = service.findById(id);
            if (null == procurement) return "redirect:/procurement";
//            log.debug("product selected : " + procurement.getProductId());
//            Product product = productService.findById(procurement.getProductId());
//            if (product != null) pfmv.setProductName(product.getProductName());
//            if (procurement.getVendorId() != null) {
//                Vendor vendor = vendorService.findById(procurement.getVendorId());
//                if (vendor != null) pfmv.setVendorName(vendor.getVendorName());
//            }
            pfmv.initFrom(procurement);
//            model.addAttribute("vendors", vendorService.findByProductId(procurement.getProductId()));
            model.addAttribute("procurement", pfmv);
        }
        return validSession(model, "index", "contents/approval/form_approve_pickup", "content");
    }

    /* pickup */
    @PostMapping("approval/save_pickup")
    public String savePickup(Model model, @Valid @ModelAttribute("procurement") ProcurementFormMV o, BindingResult bindingResult) {
        log.debug("procurement save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("procurement", o);
            return validSession(model, "index", "contents/approval/form_pickup", "content");
        }
        Procurement procurement = service.findById(o.getRequestId());
        AppUser appUser = getUser();
        procurement.setStatusProc(AppConstants.Proc.apvPickup);
        procurement.setNikPickup(appUser.getExternalId());
        procurement.setApvPickupDate(new Date());
        signatureModel(procurement, false);
        service.createOrUpdate(procurement);
        return "redirect:/approval/pickup";
    }

}
