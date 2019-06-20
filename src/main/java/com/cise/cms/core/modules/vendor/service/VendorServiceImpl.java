package com.cise.cms.core.modules.vendor.service;

import com.cise.cms.core.modules.vendor.models.Vendor;
import com.cise.cms.core.modules.vendor.models.VendorPrice;
import com.cise.cms.core.modules.vendor.repo.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private int rowSize = 10;

    @Autowired
    protected VendorRepository repository;

    @Override
    public Vendor createOrUpdate(Vendor o) {
        return repository.save(o);
    }

    @Override
    public void delete(Vendor o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Vendor findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Vendor> findAll() {
        List<Vendor> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public List<VendorPrice> findByProductId(long productId) {
        return repository.findByProductId(productId);
    }

    @Override
    public Page<Vendor> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Vendor> page(String search, int page) {
        return repository.findByVendorNameLike("%" + search + "%", PageRequest.of(page, rowSize));
    }


}
