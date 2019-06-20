package com.cise.cms.core.modules.role.components;

import com.cise.cms.core.modules.employe.modelview.EmployeeFormMV;
import com.cise.cms.core.modules.role.models.Role;
import com.cise.cms.core.modules.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {

    @Autowired
    RoleService roleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Role.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof Role) {
            Role role = (Role) target;
            if (role.getRoleId() == 0 && role.getRoleName() != null && !role.getRoleName().isEmpty()) {
                Role tmp = roleService.findByRoleName(role.getRoleName());
                if (tmp != null) errors.rejectValue("roleName", "role.name.is.exist", "Role already exist");
            }
        }
    }
}
