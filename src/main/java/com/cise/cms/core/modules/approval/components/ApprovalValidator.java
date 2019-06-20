package com.cise.cms.core.modules.approval.components;

import com.cise.cms.core.modules.procurement.modelview.ProcurementFormMV;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ApprovalValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProcurementFormMV.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof ProcurementFormMV){
            ProcurementFormMV o = (ProcurementFormMV) target;
//            if(o.getVendorId()==null||o.getVendorId().trim().isEmpty()){
//                errors.rejectValue("vendorId", "vendor.empty", "Please choose vendor");
//            }
        }
    }
}
