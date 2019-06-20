package com.cise.cms.core.modules.vendor.repo;

import com.cise.cms.core.modules.vendor.models.Vendor;
import com.cise.cms.core.modules.vendor.models.VendorPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends CrudRepository<Vendor, Long> {

    @Query(value = "SELECT a.*, a.vendor_id as vendorId, a.vendor_name as vendorName, a.pic_telp as picTelp, b.harga from prc_vendor a LEFT JOIN prc_vendor_product b on a.vendor_id = b.vendor_id LEFT JOIN prc_product c on b.product_id = c.product_id WHERE c.product_id = :productId", nativeQuery = true)
    List<VendorPrice> findByProductId(@Param("productId") long productId);

    Page<Vendor> findAll(Pageable pageable);

    Page<Vendor> findByVendorNameLike(String s, Pageable pageable);
}
