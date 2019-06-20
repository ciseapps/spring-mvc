package com.cise.cms.core.modules.product.service;

import com.cise.cms.core.modules.product.models.Product;
import com.cise.cms.core.modules.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private int rowSize = 10;

    @Autowired
    protected ProductRepository repository;

    @Override
    public Product createOrUpdate(Product o) {
        return repository.save(o);
    }

    @Override
    public void delete(Product o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Product findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        List<Product> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<Product> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Product> page(String search, int page) {
         return repository.findByProductNameLike("%"+search+"%", PageRequest.of(page, rowSize));
//        return repository.findAll(PageRequest.of(page, rowSize));
    }
}
