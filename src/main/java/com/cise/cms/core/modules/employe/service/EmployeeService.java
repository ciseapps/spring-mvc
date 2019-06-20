package com.cise.cms.core.modules.employe.service;

import com.cise.cms.core.applications.base.BaseService;
import com.cise.cms.core.modules.employe.models.Employee;

public interface EmployeeService extends BaseService<Employee> {

    Employee findByNik(String nik);

}
