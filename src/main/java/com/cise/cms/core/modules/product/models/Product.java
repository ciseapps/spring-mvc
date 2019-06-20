package com.cise.cms.core.modules.product.models;

import com.cise.cms.core.applications.base.BaseModelSignature;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "prc_product")
public class Product extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    @NotNull(message = "Products cannot be null")
    @NotBlank(message = "Products is mandatory")
    @Size(min = 6, max = 30, message = "Products cannot be null min char 6 and max 30")
    private String productName;

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getProductId(){
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
