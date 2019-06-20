package com.cise.cms.core.modules.vendorproduct.repo;

import com.cise.cms.core.modules.vendorproduct.models.VendorProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VendorproductRepository extends CrudRepository<VendorProduct, Long> {

    Page<VendorProduct> findAll(Pageable pageable);

    @Query(
            value = "select a.* from prc_vendor_product a left join prc_vendor b on a.vendor_id = b.vendor_id left join prc_product c on a.product_id = c.product_id where b.vendor_name like ?1 or c.product_name like ?2 \n-- #pageable\n",
            countQuery = "select count(1) from prc_vendor_product a left join prc_vendor b on a.vendor_id = b.vendor_id left join prc_product c on a.product_id = c.product_id where b.vendor_name like ?1 or c.product_name like ?2",
            nativeQuery = true)
    Page<VendorProduct> findByVendorOrProduct(String key, String key1, Pageable pageable);

}
