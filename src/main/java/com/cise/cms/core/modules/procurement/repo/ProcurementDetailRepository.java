package com.cise.cms.core.modules.procurement.repo;

import com.cise.cms.core.modules.procurement.models.Procurement;
import com.cise.cms.core.modules.procurement.models.ProcurementDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcurementDetailRepository extends CrudRepository<ProcurementDetail, Long> {

    List<ProcurementDetail> findByRequestId(long requestId);

    Page<ProcurementDetail> findAll(Pageable pageable);


}
