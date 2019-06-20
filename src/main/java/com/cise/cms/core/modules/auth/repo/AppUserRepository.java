package com.cise.cms.core.modules.auth.repo;

import com.cise.cms.core.modules.auth.models.dao.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    AppUser findByExternalId(String nik);

    AppUser findByUsername(String username);

    AppUser findByUsernameAndPassword(String username, String password);

    Page<AppUser> findAll(Pageable pageable);

}
