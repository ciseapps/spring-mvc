package com.cise.cms.core.modules.procurement.service;

import com.cise.cms.core.applications.base.BaseService;
import com.cise.cms.core.modules.procurement.models.Procurement;
import com.cise.cms.core.modules.procurement.models.ProcurementDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProcurementService extends BaseService<Procurement> {

    void createOrUpdate(ProcurementDetail procurementDetail);

    void createOrUpdate(List<ProcurementDetail> procurementDetail);

    void delete(ProcurementDetail procurementDetail);

    void delete(List<ProcurementDetail> procurementDetail);

    List<ProcurementDetail> findByRequestId(long requestId);

    Page<Procurement> pageStatusProc(long statusProc, int page);

    Page<Procurement> pageSearchAndStatusProc(String search, long statusProc, int page);

    long countRequest();

    long waitingApproval();

    long receive();

    long pickupFinish();
}
