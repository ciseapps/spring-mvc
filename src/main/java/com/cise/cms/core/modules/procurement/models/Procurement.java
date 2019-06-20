package com.cise.cms.core.modules.procurement.models;

import com.cise.cms.core.applications.base.BaseModelSignature;
import com.cise.cms.core.modules.employe.models.Employee;
import com.cise.cms.core.modules.product.models.Product;
import com.cise.cms.core.modules.vendor.models.Vendor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prc_request")
public class Procurement extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long requestId;

    private Date dueDate;

    private double harga;

    private String venReqDoc;

    private String invoiceId;

    private String invoiceDoc;

    private long statusProc;

    private String nikReq;

    private String nikKdep;

    private String nikProc;

    private String nikKdepProc;

    private String nikReceive;

    private String nikReceiveApprove;

    private String nikPickup;

    private Date requestDate;

    private Date apvKdepDate;

    private Date apvProcDate;

    private Date apvKdepProcDate;

    private Date receiveDate;

    private Date apvReceiveDate;

    private Date apvPickupDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestId", referencedColumnName = "requestId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ProcurementDetail> detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nikReq", referencedColumnName = "nik", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Employee requestBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nikKdep", referencedColumnName = "nik", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Employee headDeptEmpl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nikProc", referencedColumnName = "nik", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Employee procEmpl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nikKdepProc", referencedColumnName = "nik", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Employee headProcEmpl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nikReceive", referencedColumnName = "nik", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Employee receiveEmpl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nikReceiveApprove", referencedColumnName = "nik", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Employee receiveApprvEmpl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nikPickup", referencedColumnName = "nik", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Employee pickupEmpl;


    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getVenReqDoc() {
        return venReqDoc;
    }

    public void setVenReqDoc(String venReqDoc) {
        this.venReqDoc = venReqDoc;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceDoc() {
        return invoiceDoc;
    }

    public void setInvoiceDoc(String invoiceDoc) {
        this.invoiceDoc = invoiceDoc;
    }

    public long getStatusProc() {
        return statusProc;
    }

    public void setStatusProc(long statusProc) {
        this.statusProc = statusProc;
    }

    public String getNikKdep() {
        return nikKdep;
    }

    public void setNikKdep(String nikKdep) {
        this.nikKdep = nikKdep;
    }

    public String getNikPickup() {
        return nikPickup;
    }

    public void setNikPickup(String nikPickup) {
        this.nikPickup = nikPickup;
    }

    public String getNikReceive() {
        return nikReceive;
    }

    public void setNikReceive(String nikReceive) {
        this.nikReceive = nikReceive;
    }

    public Date getApvKdepDate() {
        return apvKdepDate;
    }

    public void setApvKdepDate(Date apvKdepDate) {
        this.apvKdepDate = apvKdepDate;
    }

    public Date getApvPickupDate() {
        return apvPickupDate;
    }

    public void setApvPickupDate(Date apvPickupDate) {
        this.apvPickupDate = apvPickupDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getNikReq() {
        return nikReq;
    }

    public void setNikReq(String nikReq) {
        this.nikReq = nikReq;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getNikKdepProc() {
        return nikKdepProc;
    }

    public void setNikKdepProc(String nikKdepProc) {
        this.nikKdepProc = nikKdepProc;
    }

    public String getNikReceiveApprove() {
        return nikReceiveApprove;
    }

    public void setNikReceiveApprove(String nikReceiveApprove) {
        this.nikReceiveApprove = nikReceiveApprove;
    }

    public Employee getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(Employee requestBy) {
        this.requestBy = requestBy;
    }

    public Employee getHeadDeptEmpl() {
        return headDeptEmpl;
    }

    public void setHeadDeptEmpl(Employee headDeptEmpl) {
        this.headDeptEmpl = headDeptEmpl;
    }

    public Employee getHeadProcEmpl() {
        return headProcEmpl;
    }

    public void setHeadProcEmpl(Employee headProcEmpl) {
        this.headProcEmpl = headProcEmpl;
    }

    public Employee getReceiveEmpl() {
        return receiveEmpl;
    }

    public void setReceiveEmpl(Employee receiveEmpl) {
        this.receiveEmpl = receiveEmpl;
    }

    public Employee getReceiveApprvEmpl() {
        return receiveApprvEmpl;
    }

    public void setReceiveApprvEmpl(Employee receiveApprvEmpl) {
        this.receiveApprvEmpl = receiveApprvEmpl;
    }

    public Employee getPickupEmpl() {
        return pickupEmpl;
    }

    public void setPickupEmpl(Employee pickupEmpl) {
        this.pickupEmpl = pickupEmpl;
    }

    public Date getApvKdepProcDate() {
        return apvKdepProcDate;
    }

    public void setApvKdepProcDate(Date apvKdepProcDate) {
        this.apvKdepProcDate = apvKdepProcDate;
    }

    public String getNikProc() {
        return nikProc;
    }

    public void setNikProc(String nikProc) {
        this.nikProc = nikProc;
    }

    public Date getApvReceiveDate() {
        return apvReceiveDate;
    }

    public void setApvReceiveDate(Date apvReceiveDate) {
        this.apvReceiveDate = apvReceiveDate;
    }

    public Employee getProcEmpl() {
        return procEmpl;
    }

    public void setProcEmpl(Employee procEmpl) {
        this.procEmpl = procEmpl;
    }

    public Date getApvProcDate() {
        return apvProcDate;
    }

    public void setApvProcDate(Date apvProcDate) {
        this.apvProcDate = apvProcDate;
    }

    public List<ProcurementDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<ProcurementDetail> detail) {
        this.detail = detail;
    }
}
