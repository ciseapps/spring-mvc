package com.cise.cms.core.modules.home.controllers;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.applications.constants.AppConstants;
import com.cise.cms.core.modules.auth.models.dao.AppUser;
import com.cise.cms.core.modules.home.components.RequestProductValidator;
import com.cise.cms.core.modules.home.models.RequestMV;
import com.cise.cms.core.modules.procurement.models.Procurement;
import com.cise.cms.core.modules.procurement.models.ProcurementDetail;
import com.cise.cms.core.modules.procurement.service.ProcurementService;
import com.cise.cms.core.modules.product.service.ProductService;
import com.cise.cms.core.modules.role.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController extends BaseControllers {

    @Autowired
    ProductService productService;

    @Autowired
    ProcurementService procurementService;

    @Autowired
    RequestProductValidator requestProductValidator;

    @GetMapping("/home")
    public String main(Model model, HttpSession httpSession) {
        model.addAttribute("newReq", procurementService.countRequest());
        model.addAttribute("waitingApproval", procurementService.waitingApproval());
        model.addAttribute("receive", procurementService.receive());
        model.addAttribute("pickupFinish", procurementService.pickupFinish());
        return validSession(model, "index", "contents/dashboard/dashboard", "content");
    }

    @GetMapping("/add_request")
    public String addRequest(Model model, HttpSession httpSession) {
        model.addAttribute("request", new RequestMV());
        model.addAttribute("products", productService.findAll());
        return validSession(model, "index", "contents/dashboard/add_request", "content");
    }

    @PostMapping("/add_request/save_request")
    public String save(Model model, @Valid @ModelAttribute("request") RequestMV o, BindingResult bindingResult) {
        log.debug("procurement save");
        requestProductValidator.validate(o, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("request", o);
            model.addAttribute("products", productService.findAll());
            return validSession(model, "index", "contents/dashboard/add_request", "content");
        }
        Procurement procurement = new Procurement();
        o.castToProcurement(procurement);
        AppUser appUser = getUser();
        procurement.setNikReq(appUser.getExternalId());
        procurement.setRequestDate(new Date());
        procurement.setStatusProc(AppConstants.Proc.newRequest);
        signatureModel(procurement, true);
        procurementService.createOrUpdate(procurement);
        List<ProcurementDetail> pOld = procurementService.findByRequestId(procurement.getRequestId());
        procurementService.delete(pOld);
        List<ProcurementDetail> pNew = o.castToProcurementDetail(procurement, o);
        procurementService.createOrUpdate(pNew);
        return "redirect:/home";
    }

    @GetMapping("/hello")
    public String mainWithParam(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("message", name);
        return "index"; //view
    }

}
