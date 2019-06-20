package com.cise.cms.core.modules.rolemenu.service;

import com.cise.cms.core.modules.rolemenu.models.dao.Rolemenu;
import com.cise.cms.core.modules.rolemenu.repo.RolemenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolemenuServiceImpl implements RolemenuService {

    private int rowSize = 10;

    @Autowired
    protected RolemenuRepository repository;

    @Override
    public Rolemenu createOrUpdate(Rolemenu o) {
        return repository.save(o);
    }

    @Override
    public void delete(Rolemenu o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Rolemenu findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Rolemenu> findAll() {
        List<Rolemenu> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<Rolemenu> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Rolemenu> page(String search, int page) {
        String key = "%"+search+"%";
         return repository.findByRoleLikeOrMenuLike(key, key, PageRequest.of(page, rowSize));

    }

    @Override
    public List<Rolemenu> findMenuByRoleId(int roleId) {
        return repository.findByRoleId(roleId);
    }
}
