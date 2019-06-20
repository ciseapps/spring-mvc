package com.cise.cms.core.modules.sample.repo;

import com.cise.cms.core.modules.sample.models.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface SampleRepository extends CrudRepository<Sample, Long> {

    Page<Sample> findAll(Pageable pageable);

    Page<Sample> findAllByUserLike(String user, Pageable pageable);

}
