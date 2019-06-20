package com.cise.cms.core.modules.employe.repo;

import com.cise.cms.core.modules.employe.models.Employee;
import com.cise.cms.core.modules.role.models.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeRepository extends CrudRepository<Employee, Long> {

    Employee findByNik(String id);

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findAllByNikLikeOrNamaLike(String key1, String key2, Pageable pageable);
}
