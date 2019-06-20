package com.cise.cms.core.modules.product.repo;

import com.cise.cms.core.modules.product.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByProductNameLike(String s, Pageable pageable);
}
