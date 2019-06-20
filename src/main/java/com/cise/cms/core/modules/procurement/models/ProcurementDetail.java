package com.cise.cms.core.modules.procurement.models;

import com.cise.cms.core.applications.base.BaseModelSignature;
import com.cise.cms.core.modules.employe.models.Employee;
import com.cise.cms.core.modules.product.models.Product;
import com.cise.cms.core.modules.vendor.models.Vendor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prc_request_detail")
public class ProcurementDetail extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long requestDetailId;

    private long requestId;

    private long productId;

    private long qty;

    private long vendorId;

    private double hargaSatuan;

    private double hargaTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendorId", referencedColumnName = "vendorId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Vendor vendor;

    public long getRequestDetailId() {
        return requestDetailId;
    }

    public void setRequestDetailId(long requestDetailId) {
        this.requestDetailId = requestDetailId;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public double getHargaTotal() {
        return hargaTotal;
    }

    public void setHargaTotal(double hargaTotal) {
        this.hargaTotal = hargaTotal;
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
