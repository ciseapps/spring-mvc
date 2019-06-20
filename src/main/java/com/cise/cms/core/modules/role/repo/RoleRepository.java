package com.cise.cms.core.modules.role.repo;

import com.cise.cms.core.modules.role.models.Role;
import com.cise.cms.core.modules.sample.models.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findAllByRoleName(String roleName);

    Page<Role> findAll(Pageable pageable);

    Page<Role> findAllByRoleNameLike(String user, Pageable pageable);


}
