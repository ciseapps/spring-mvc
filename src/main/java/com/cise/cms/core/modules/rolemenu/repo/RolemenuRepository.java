package com.cise.cms.core.modules.rolemenu.repo;

import com.cise.cms.core.modules.rolemenu.models.dao.Rolemenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RolemenuRepository extends CrudRepository<Rolemenu, Long> {

    List<Rolemenu> findByRoleId(long roleId);

    Page<Rolemenu> findAll(Pageable pageable);

    @Query(
            value = "select a.* from app_role_menu a left join app_menu b on a.menu_id = b.menu_id left join app_role c on a.role_id = c.role_id where b.menu_name like ?1 or c.role_name like ?2 \n-- #pageable\n",
            countQuery = "select count(1) from app_role_menu a left join app_menu b on a.menu_id = b.menu_id left join app_role c on a.role_id = c.role_id where b.menu_name like ?1 or c.role_name like ?2",
            nativeQuery = true)
    Page<Rolemenu> findByRoleLikeOrMenuLike(String key1, String key2, Pageable pageable);


}