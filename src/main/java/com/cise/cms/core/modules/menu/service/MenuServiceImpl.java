package com.cise.cms.core.modules.menu.service;

import com.cise.cms.core.modules.menu.models.Menu;
import com.cise.cms.core.modules.menu.repo.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private int rowSize = 10;

    @Autowired
    protected MenuRepository repository;

    @Override
    public Menu createOrUpdate(Menu o) {
        return repository.save(o);
    }

    @Override
    public void delete(Menu o) {
        repository.delete(o);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Menu findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Menu> findAll() {
        List<Menu> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Page<Menu> page(int page) {
        return repository.findAll(PageRequest.of(page, rowSize));
    }

    @Override
    public Page<Menu> page(String search, int page) {
        return repository.findByMenuNameLikeOrModuleNameLike("%" + search + "%", "%" + search + "%", PageRequest.of(page, rowSize));
    }

    @Override
    public List<Menu> findAllByRootMenu() {
        return repository.findByModuleNameLike("#%");
    }

    @Override
    public List<Menu> findByIdIn(List<Long> ids) {
        return repository.findByMenuIdInOrderBySeqNumberAsc(ids);
    }
}
