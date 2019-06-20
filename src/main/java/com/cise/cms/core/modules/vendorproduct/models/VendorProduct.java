package com.cise.cms.core.modules.vendorproduct.models;

import com.cise.cms.core.applications.base.BaseModelSignature;
import com.cise.cms.core.modules.product.models.Product;
import com.cise.cms.core.modules.vendor.models.Vendor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "prc_vendor_product")
public class VendorProduct extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long vendorProductId;

    private long vendorId;

    private long productId;

    private double harga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendorId", referencedColumnName = "vendorId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    Product product;

    public void setVendorProductId(long vendorProductId) {
        this.vendorProductId = vendorProductId;
    }

    public long getVendorProductId(){
        return vendorProductId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public long getVendorId(){
        return vendorId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getProductId(){
        return productId;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public double getHarga(){
        return harga;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
