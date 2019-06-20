package com.cise.cms.core.modules.config.repo;

import com.cise.cms.core.modules.config.models.Config;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ConfigRepository extends CrudRepository<Config, Long> {

    Page<Config> findAll(Pageable pageable);

}
