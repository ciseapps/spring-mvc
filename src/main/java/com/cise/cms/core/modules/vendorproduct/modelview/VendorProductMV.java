package com.cise.cms.core.modules.vendorproduct.modelview;

import com.cise.cms.core.modules.vendor.models.Vendor;
import com.cise.cms.core.modules.vendorproduct.models.VendorProduct;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VendorProductMV {

    long vendorProductId;

    @NotNull(message = "Vendor cannot be null")
    @NotBlank(message = "Vendor is mandatory")
    @Size(min = 1, max = 30, message = "Vendor cannot be null min char 3 and max 30")
    String vendorId;

    @NotNull(message = "Product cannot be null")
    @NotBlank(message = "Product is mandatory")
    @Size(min = 1, max = 30, message = "Products cannot be null min char 3 and max 30")
    String productId;

    double harga;

    public long getVendorProductId() {
        return vendorProductId;
    }

    public void setVendorProductId(long vendorProductId) {
        this.vendorProductId = vendorProductId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void initVendor(VendorProduct vendorProduct) {
        vendorProduct.setVendorId(Long.valueOf(vendorId));
        vendorProduct.setProductId(Long.valueOf(productId));
        vendorProduct.setHarga(harga);
    }

    public void initFrom(VendorProduct vendorProduct){
        this.vendorProductId = vendorProduct.getVendorProductId();
        this.vendorId = String.valueOf(vendorProduct.getVendorId());
        this.productId = String.valueOf(vendorProduct.getProductId());
        this.harga = vendorProduct.getHarga();
    }
}
