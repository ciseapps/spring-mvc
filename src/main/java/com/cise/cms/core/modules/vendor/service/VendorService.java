package com.cise.cms.core.modules.vendor.service;

import com.cise.cms.core.applications.base.BaseService;
import com.cise.cms.core.modules.vendor.models.Vendor;
import com.cise.cms.core.modules.vendor.models.VendorPrice;

import java.util.List;

public interface VendorService extends BaseService<Vendor> {

    List<VendorPrice> findByProductId(long productId);

}
