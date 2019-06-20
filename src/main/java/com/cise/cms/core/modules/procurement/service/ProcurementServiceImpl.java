package com.cise.cms.core.modules.procurement.service;

import com.cise.cms.core.applications.constants.AppConstants;
import com.cise.cms.core.modules.procurement.models.Procurement;
import com.cise.cms.core.modules.procurement.models.ProcurementDetail;
import com.cise.cms.core.modules.procurement.repo.ProcurementDetailRepository;
import com.cise.cms.core.modules.procurement.repo.ProcurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcurementServiceImpl implements ProcurementService {

    private int rowSize = 10;

    @Autowired
    protected ProcurementRepository repository;

    @Autowired
    protected ProcurementDetailRepository detailRepository;

    @Override
    public Procurement createOrUpdate(Procurement o) {
        return repository.save(o);
    }

    @Override
    public void delete(Procurement o) {
        List<ProcurementDetail> details = detailRepository.findByRequestId(o.getRequestId());
        detailRepository.deleteAll(details);
        repository.delete(o);

    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Procurement findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Procurement> findAll() {
        List<Procurement> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public void createOrUpdate(ProcurementDetail procurementDetail) {
        detailRepository.save(procurementDetail);
    }

    @Override
    public void createOrUpdate(List<ProcurementDetail> procurementDetail) {
        for (ProcurementDetail pd : procurementDetail) {
            detailRepository.save(pd);
        }
    }

    @Override
    public void delete(ProcurementDetail procurementDetail) {
        detailRepository.delete(procurementDetail);
    }

    @Override
    public void delete(List<ProcurementDetail> procurementDetail) {
        detailRepository.deleteAll(procurementDetail);
    }

    @Override
    public List<ProcurementDetail> findByRequestId(long requestId) {
        return new ArrayList<>(detailRepository.findByRequestId(requestId));
    }

    @Override
    public Page<Procurement> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Procurement> page(String search, int page) {
        String key = "%" + search + "%";
        return repository.findByConditionsPageable(key, key, key, PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Procurement> pageStatusProc(long statusProc, int page) {
        return repository.findByStatusProc(statusProc, PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Procurement> pageSearchAndStatusProc(String search, long statusProc, int page) {
        String key = "%" + search + "%";
        return repository.findByConditionsPageable(statusProc, key, key, key, PageRequest.of(page, rowSize));
    }

    @Override
    public long countRequest() {
        return repository.countByStatus(AppConstants.Proc.newRequest);
    }

    @Override
    public long waitingApproval() {
        long kdep = repository.countByStatus(AppConstants.Proc.apvKdep);
        long kdepProc = repository.countByStatus(AppConstants.Proc.apvKdepProc);
        long kdepRecv = repository.countByStatus(AppConstants.Proc.apvKdepRcv);
        return (kdep + kdepProc + kdepRecv);
    }

    @Override
    public long receive() {
        return repository.countByStatus(AppConstants.Proc.apvRcv);
    }

    @Override
    public long pickupFinish() {
        long pck = repository.countByStatus(AppConstants.Proc.apvPickup);
        return (pck);
    }
}
