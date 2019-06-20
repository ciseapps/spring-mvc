package com.cise.cms.core.modules.menu.repo;

import com.cise.cms.core.modules.menu.models.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends CrudRepository<Menu, Long> {

    List<Menu> findByModuleNameLike(String moduleName);

    List<Menu> findByMenuIdIn(List<Long> ids);

    Page<Menu> findAll(Pageable pageable);

    Page<Menu> findByMenuNameLikeOrModuleNameLike(String menuName, String moduleName, Pageable pageable);

    List<Menu> findByMenuIdInOrderBySeqNumberAsc(List<Long> ids);
}
