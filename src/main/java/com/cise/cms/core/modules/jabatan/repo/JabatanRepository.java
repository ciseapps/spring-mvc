package com.cise.cms.core.modules.jabatan.repo;

import com.cise.cms.core.modules.jabatan.models.Jabatan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface JabatanRepository extends CrudRepository<Jabatan, Long> {

    Page<Jabatan> findAll(Pageable pageable);

    Page<Jabatan> findByJabatanNameLike(String s, Pageable pageable);
}
