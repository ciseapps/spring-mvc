package com.cise.cms.core.modules.role.service;

import com.cise.cms.core.modules.role.models.Role;
import com.cise.cms.core.modules.role.repo.RoleRepository;
import com.cise.cms.core.modules.sample.repo.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private int rowSize = 10;

    @Autowired
    protected RoleRepository repository;

    @Override
    public Role createOrUpdate(Role o) {
        return repository.save(o);
    }

    @Override
    public void delete(Role o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Role findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        List<Role> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<Role> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Role> page(String search, int page) {
        return repository.findAllByRoleNameLike("%"+search+"%", PageRequest.of(page, rowSize));
    }

    @Override
    public Role findByRoleName(String roleName) {
        return repository.findAllByRoleName(roleName);
    }
}
