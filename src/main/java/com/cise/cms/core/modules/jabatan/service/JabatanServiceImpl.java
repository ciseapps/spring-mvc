package com.cise.cms.core.modules.jabatan.service;

import com.cise.cms.core.modules.jabatan.models.Jabatan;
import com.cise.cms.core.modules.jabatan.repo.JabatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JabatanServiceImpl implements JabatanService {

    private int rowSize = 10;

    @Autowired
    protected JabatanRepository repository;

    @Override
    public Jabatan createOrUpdate(Jabatan o) {
        return repository.save(o);
    }

    @Override
    public void delete(Jabatan o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Jabatan findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Jabatan> findAll() {
        List<Jabatan> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<Jabatan> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Jabatan> page(String search, int page) {
         return repository.findByJabatanNameLike("%"+search+"%", PageRequest.of(page, rowSize));
//        return repository.findAll(PageRequest.of(page, rowSize));
    }
}
