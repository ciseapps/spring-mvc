package com.cise.cms.core.modules.employe.service;

import com.cise.cms.core.modules.employe.models.Employee;
import com.cise.cms.core.modules.employe.repo.EmployeRepository;
import com.cise.cms.core.modules.role.models.Role;
import com.cise.cms.core.modules.role.repo.RoleRepository;
import com.cise.cms.core.modules.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private int rowSize = 10;

    @Autowired
    protected EmployeRepository repository;

    @Override
    public Employee createOrUpdate(Employee o) {
        return repository.save(o);
    }

    @Override
    public void delete(Employee o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Employee findById(long id) {
        // return repository.findById(id).orElse(null);
        return null;
    }

    @Override
    public Employee findByNik(String nik) {
        return repository.findByNik(nik);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<Employee> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Employee> page(String search, int page) {
        String key = "%"+search+"%";
        return repository.findAllByNikLikeOrNamaLike(key, key, PageRequest.of(page, rowSize));
    }
}
