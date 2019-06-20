package com.cise.cms.core.modules.employe.components;

import com.cise.cms.core.applications.helper.StringHelper;
import com.cise.cms.core.modules.auth.models.dao.AppUser;
import com.cise.cms.core.modules.auth.service.AppUserService;
import com.cise.cms.core.modules.employe.modelview.EmployeeFormMV;
import com.cise.cms.core.modules.employe.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class EmployeeValidator implements Validator {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AppUserService appUserService;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeFormMV.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof EmployeeFormMV) {
            EmployeeFormMV o = (EmployeeFormMV) target;
            if (o.getId() == 0 && o.getNik() != null && null != employeeService.findByNik(o.getNik())) {
                errors.rejectValue("nik", "nik.is.exist", "Nik already exist");
            }
            if (o.getId() == 0 && o.getUsername() != null && null != appUserService.findByUsername(o.getUsername())) {
                errors.rejectValue("username", "username.is.exist", "Email already exist");
            }
            if (o.getId() == 0 && o.getUsername() != null && !StringHelper.isValidEmail(o.getUsername())){
                errors.rejectValue("username", "username.is.exist", "Email not valid");
            }
            if (o.getId() == 0 && o.getPassword() == null) {
                errors.rejectValue("password", "password.is.empty", "Password cannot be empty");
            } else if (o.getId() == 0 && o.getPassword() != null && o.getPassword().isEmpty()) {
                errors.rejectValue("password", "password.is.empty", "Password cannot be empty");
            }
            if (o.getId() == 0 && o.getPassword() != null && !o.getPassword().equals(o.getPasswordConfirm())) {
                errors.rejectValue("passwordConfirm", "password.equal", "Password confirm not match");
            }
        }

    }
}
