package com.cise.cms.core.modules.department.repo;

import com.cise.cms.core.modules.department.models.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Page<Department> findAll(Pageable pageable);

    Page<Department> findByDepartmentNameLike(String s, Pageable pageable);
}
