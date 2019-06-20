package com.cise.cms.core.modules.auth.service;

import com.cise.cms.core.modules.auth.models.dao.AppUser;
import com.cise.cms.core.modules.auth.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private int rowSize = 10;

    @Autowired
    AppUserRepository repository;

    @Override
    public AppUser createOrUpdate(AppUser o) {
        return repository.save(o);
    }

    @Override
    public void delete(AppUser o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public AppUser findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<AppUser> findAll() {
        List<AppUser> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<AppUser> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<AppUser> page(String search, int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public AppUser findByExternalId(String nik) {
        return repository.findByExternalId( nik);
    }

    @Override
    public AppUser findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public AppUser findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }
}
