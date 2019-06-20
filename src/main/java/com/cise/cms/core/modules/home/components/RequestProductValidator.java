package com.cise.cms.core.modules.home.components;

import com.cise.cms.core.modules.home.models.RequestMV;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RequestProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RequestMV.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof RequestMV) {
            RequestMV requestMV = (RequestMV) target;
            if (null != requestMV.getProductId()) {
                for (String p : requestMV.getProductId()) {
                    if (null == p) {
                        errors.rejectValue("productId", "product.id.empty", "Please make sure all select product not empty");
                        break;
                    } else if (p.isEmpty()){
                        errors.rejectValue("productId", "product.id.empty", "Please make sure all select product not empty");
                        break;
                    }
                }
            } else {
                errors.rejectValue("productId", "product.id.empty", "Please make sure all select product not empty");
            }
            if (null != requestMV.getQty()) {
                for (String p : requestMV.getQty()) {
                    if (null == p) {
                        errors.rejectValue("qty", "qty.empty", "Please make sure all qty input positive value at least 1");
                        break;
                    } else if (p.isEmpty()){
                        errors.rejectValue("qty", "qty.empty", "Please make sure all qty input positive value at least 1");
                        break;
                    }

                }
            } else {
                errors.rejectValue("qty", "qty.empty", "Please make sure all qty input positive value at least 1");
            }
//            if (role.getRoleId() == 0 && role.getRoleName() != null && !role.getRoleName().isEmpty()) {
//                Role tmp = roleService.findByRoleName(role.getRoleName());
//                if (tmp != null) errors.rejectValue("roleName", "role.name.is.exist", "Role already exist");
//            }
        }
    }
}
