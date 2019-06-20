package com.cise.cms.core.modules.department.service;

import com.cise.cms.core.modules.department.models.Department;
import com.cise.cms.core.modules.department.repo.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private int rowSize = 10;

    @Autowired
    protected DepartmentRepository repository;

    @Override
    public Department createOrUpdate(Department o) {
        return repository.save(o);
    }

    @Override
    public void delete(Department o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Department findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Department> findAll() {
        List<Department> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<Department> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Department> page(String search, int page) {
         return repository.findByDepartmentNameLike("%"+search+"%", PageRequest.of(page, rowSize));
//        return repository.findAll(PageRequest.of(page, rowSize));
    }
}
