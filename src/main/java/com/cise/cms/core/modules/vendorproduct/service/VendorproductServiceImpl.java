package com.cise.cms.core.modules.vendorproduct.service;

import com.cise.cms.core.modules.vendorproduct.models.VendorProduct;
import com.cise.cms.core.modules.vendorproduct.repo.VendorproductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorproductServiceImpl implements VendorproductService {

    private int rowSize = 10;

    @Autowired
    protected VendorproductRepository repository;

    @Override
    public VendorProduct createOrUpdate(VendorProduct o) {
        return repository.save(o);
    }

    @Override
    public void delete(VendorProduct o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public VendorProduct findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<VendorProduct> findAll() {
        List<VendorProduct> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<VendorProduct> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<VendorProduct> page(String search, int page) {
        String key = "%"+search+"%";
        return repository.findByVendorOrProduct(key, key, PageRequest.of(page, rowSize));
        //return repository.findAll(PageRequest.of(page, rowSize));
    }
}
