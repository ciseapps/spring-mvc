package com.cise.cms.core.modules.auth.components;

import com.cise.cms.core.modules.auth.models.dao.AppUser;
import com.cise.cms.core.modules.auth.models.view.LoginMV;
import com.cise.cms.core.modules.auth.service.AppUserService;
import com.cise.cms.core.modules.employe.modelview.EmployeeFormMV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AppUserValidator implements Validator {

    @Autowired
    AppUserService appUserService;

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginMV.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof LoginMV) {
            LoginMV o = (LoginMV) target;
            AppUser appUser = appUserService.findByUsernameAndPassword(o.getUsername(), o.getPassword());
            if (appUser == null) {
                errors.rejectValue("username", "username.is.exist", "Incorrect username");
                errors.rejectValue("password", "username.is.exist", "Incorrect password");
            }
        }

    }
}
