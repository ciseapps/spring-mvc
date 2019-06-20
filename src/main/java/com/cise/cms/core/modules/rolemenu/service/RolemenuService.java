package com.cise.cms.core.modules.rolemenu.service;

import com.cise.cms.core.applications.base.BaseService;
import com.cise.cms.core.modules.rolemenu.models.dao.Rolemenu;

import java.util.List;

public interface RolemenuService extends BaseService<Rolemenu> {

    List<Rolemenu> findMenuByRoleId(int roleId);

}
