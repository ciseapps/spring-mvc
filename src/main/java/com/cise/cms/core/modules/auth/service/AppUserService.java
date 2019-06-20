package com.cise.cms.core.modules.auth.service;

import com.cise.cms.core.applications.base.BaseService;
import com.cise.cms.core.modules.auth.models.dao.AppUser;

public interface AppUserService extends BaseService<AppUser> {

    AppUser findByExternalId(String nik);

    AppUser findByUsername(String username);

    AppUser findByUsernameAndPassword(String username, String password);
}
