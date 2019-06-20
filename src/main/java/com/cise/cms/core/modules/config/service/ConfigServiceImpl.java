package com.cise.cms.core.modules.config.service;

import com.cise.cms.core.modules.config.models.Config;
import com.cise.cms.core.modules.config.repo.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    private int rowSize = 10;

    @Autowired
    protected ConfigRepository repository;

    @Override
    public Config createOrUpdate(Config o) {
        return repository.save(o);
    }

    @Override
    public void delete(Config o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Config findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Config> findAll() {
        List<Config> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<Config> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Config> page(String search, int page) {
        // return repository.findAll("%"+search+"%", PageRequest.of(page, rowSize));
        return repository.findAll(PageRequest.of(page, rowSize));
    }
}
