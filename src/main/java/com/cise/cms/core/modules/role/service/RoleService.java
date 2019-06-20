package com.cise.cms.core.modules.role.service;

import com.cise.cms.core.applications.base.BaseService;
import com.cise.cms.core.modules.role.models.Role;
import com.cise.cms.core.modules.sample.models.Sample;

public interface RoleService extends BaseService<Role> {

    Role findByRoleName(String roleName);

}
