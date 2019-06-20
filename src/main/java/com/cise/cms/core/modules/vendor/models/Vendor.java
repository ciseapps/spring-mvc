package com.cise.cms.core.modules.vendor.models;

import com.cise.cms.core.applications.base.BaseModelSignature;
import com.cise.cms.core.modules.vendorproduct.models.VendorProduct;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "prc_vendor")
public class Vendor extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long vendorId;

    private String vendorName;

    private String alamat;

    private String email;

    private String telp;

    private String fax;

    private String pic;

    private String picTelp;

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPicTelp() {
        return picTelp;
    }

    public void setPicTelp(String picTelp) {
        this.picTelp = picTelp;
    }
}
