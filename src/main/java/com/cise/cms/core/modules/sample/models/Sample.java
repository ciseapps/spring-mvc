package com.cise.cms.core.modules.sample.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "exm_productivity")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull(message = "User cannot be null")
    @Size(min = 6, max = 30, message = "User cannot be null min char 6 and max 30")
    private String user;
    @NotNull(message = "Product cannot be null")
    @Size(min = 6, max = 30, message = "Product cannot be null min char 6 and max 30")
    private String product;
    private double sale;
    private int status;

    public Sample() {
    }

    public Sample(long id, String user, String product, double sale, int status) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.sale = sale;
        this.status = status;
    }

    public Sample(String user, String product, double sale, int status) {
        this.user = user;
        this.product = product;
        this.sale = sale;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
