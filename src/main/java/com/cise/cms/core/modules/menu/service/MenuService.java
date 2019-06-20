package com.cise.cms.core.modules.menu.service;

import com.cise.cms.core.applications.base.BaseService;
import com.cise.cms.core.modules.menu.models.Menu;

import java.util.List;

public interface MenuService extends BaseService<Menu> {

    List<Menu> findAllByRootMenu();

    List<Menu> findByIdIn(List<Long> ids);
}
