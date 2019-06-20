package com.cise.cms.core.modules.sample.service;

import com.cise.cms.core.modules.sample.models.Sample;
import com.cise.cms.core.modules.sample.repo.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    private int rowSize = 10;

    @Autowired
    protected SampleRepository repository;

    @Override
    public Sample createOrUpdate(Sample o) {
        return repository.save(o);
    }

    @Override
    public void delete(Sample o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Sample findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Sample> findAll() {
        List<Sample> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<Sample> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Sample> page(String search, int page) {
        return repository.findAllByUserLike("%"+search+"%", PageRequest.of(page, rowSize));
    }
}
