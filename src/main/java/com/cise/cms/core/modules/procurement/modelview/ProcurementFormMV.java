package com.cise.cms.core.modules.procurement.modelview;

import com.cise.cms.core.applications.helper.StringHelper;
import com.cise.cms.core.modules.employe.models.Employee;
import com.cise.cms.core.modules.procurement.models.Procurement;
import com.cise.cms.core.modules.procurement.models.ProcurementDetail;
import com.cise.cms.core.modules.product.models.Product;
import com.cise.cms.core.modules.vendor.models.Vendor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ProcurementFormMV {

    private long requestId;

    private String dueDate;

    private String departmentName;

    private long qty;

    private double harga;

    private String venReqDoc;

    private MultipartFile fileVenReqDoc;

    private MultipartFile fileInvoiceDoc;

    private String invoiceId;

    private String invoiceDoc;

    private String statusProc;

    private String nikReq;

    private String nikKdep;

    private String nikProc;

    private String nikKdepProc;

    private String nikReceive;

    private String nikReceiveApprove;

    private String nikPickup;

    private String nameReq;

    private String nameKdep;

    private String nameProc;

    private String nameKdepProc;

    private String nameReceive;

    private String nameReceiveApprove;

    private String namePickup;

    private String reqDate;

    private String apvKdepDate;

    private String apvProcDate;

    private String apvKdepProcDate;

    private String receiveDate;

    private String receiveApprovalDate;

    private String apvPickupDate;

    private List<ProcurementDetail> detail = new ArrayList<>();

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
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

    public String getStatusProc() {
        return statusProc;
    }

    public void setStatusProc(String statusProc) {
        this.statusProc = statusProc;
    }

    public String getNikReq() {
        return nikReq;
    }

    public void setNikReq(String nikReq) {
        this.nikReq = nikReq;
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

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getApvKdepProcDate() {
        return apvKdepProcDate;
    }

    public void setApvKdepProcDate(String apvKdepProcDate) {
        this.apvKdepProcDate = apvKdepProcDate;
    }

    public String getReceiveApprovalDate() {
        return receiveApprovalDate;
    }

    public void setReceiveApprovalDate(String receiveApprovalDate) {
        this.receiveApprovalDate = receiveApprovalDate;
    }

    public String getApvKdepDate() {
        return apvKdepDate;
    }

    public void setApvKdepDate(String apvKdepDate) {
        this.apvKdepDate = apvKdepDate;
    }

    public String getApvPickupDate() {
        return apvPickupDate;
    }

    public void setApvPickupDate(String apvPickupDate) {
        this.apvPickupDate = apvPickupDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public MultipartFile getFileVenReqDoc() {
        return fileVenReqDoc;
    }

    public void setFileVenReqDoc(MultipartFile fileVenReqDoc) {
        this.fileVenReqDoc = fileVenReqDoc;
    }

    public MultipartFile getFileInvoiceDoc() {
        return fileInvoiceDoc;
    }

    public void setFileInvoiceDoc(MultipartFile fileInvoiceDoc) {
        this.fileInvoiceDoc = fileInvoiceDoc;
    }

    public String getNameReq() {
        return nameReq;
    }

    public void setNameReq(String nameReq) {
        this.nameReq = nameReq;
    }

    public String getNameKdep() {
        return nameKdep;
    }

    public void setNameKdep(String nameKdep) {
        this.nameKdep = nameKdep;
    }

    public String getNameKdepProc() {
        return nameKdepProc;
    }

    public void setNameKdepProc(String nameKdepProc) {
        this.nameKdepProc = nameKdepProc;
    }

    public String getNameReceive() {
        return nameReceive;
    }

    public void setNameReceive(String nameReceive) {
        this.nameReceive = nameReceive;
    }

    public String getNameReceiveApprove() {
        return nameReceiveApprove;
    }

    public void setNameReceiveApprove(String nameReceiveApprove) {
        this.nameReceiveApprove = nameReceiveApprove;
    }

    public String getNamePickup() {
        return namePickup;
    }

    public void setNamePickup(String namePickup) {
        this.namePickup = namePickup;
    }

    public String getNikProc() {
        return nikProc;
    }

    public void setNikProc(String nikProc) {
        this.nikProc = nikProc;
    }

    public String getNameProc() {
        return nameProc;
    }

    public void setNameProc(String nameProc) {
        this.nameProc = nameProc;
    }

    public String getApvProcDate() {
        return apvProcDate;
    }

    public void setApvProcDate(String apvProcDate) {
        this.apvProcDate = apvProcDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<ProcurementDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<ProcurementDetail> detail) {
        this.detail = detail;
    }

    public void initFrom(Procurement o) {
        if (o != null) {
            this.requestId = o.getRequestId();
            this.harga = o.getHarga();
            this.invoiceId = o.getInvoiceId();
            this.invoiceDoc = o.getInvoiceDoc();
            this.nikKdep = o.getNikKdep();
            this.nikProc = o.getNikProc();
            this.nikKdepProc = o.getNikKdepProc();
            this.nikPickup = o.getNikPickup();
            this.nikReceive = o.getNikReceive();
            this.nikReceiveApprove = o.getNikReceiveApprove();
            this.nikReq = o.getNikReq();
            this.statusProc = String.valueOf(o.getStatusProc());
            this.dueDate = StringHelper.dateToString(o.getRequestDate());
            this.apvKdepDate = StringHelper.dateToString(o.getApvKdepDate());
            this.apvProcDate = StringHelper.dateToString(o.getApvProcDate());
            this.apvKdepProcDate = StringHelper.dateToString(o.getApvKdepProcDate());
            this.receiveDate = StringHelper.dateToString(o.getReceiveDate());
            this.receiveApprovalDate = StringHelper.dateToString(o.getApvReceiveDate());
            this.apvPickupDate = StringHelper.dateToString(o.getApvPickupDate());
            this.detail = o.getDetail();
//            Vendor vendor = o.getVendor();
//            if (vendor != null) {
//                this.vendorName = o.getVendor().getVendorName();
//            }
//            Product product = o.getProduct();
//            if (product != null) {
//                this.productName = o.getProduct().getProductName();
//            }
            Employee e1 = o.getRequestBy();
            if (e1 != null) {
                this.nameReq = e1.getNama();
                if (e1.getDepartment() != null) {
                    this.departmentName = e1.getDepartment().getDepartmentName();
                }
            }
            Employee e2 = o.getHeadDeptEmpl();
            if (e2 != null) {
                this.nameKdep = e2.getNama();
            }
            Employee e3 = o.getHeadProcEmpl();
            if (e3 != null) {
                this.nameKdepProc = e3.getNama();
            }
            Employee e4 = o.getReceiveEmpl();
            if (e4 != null) {
                this.nameReceive = e4.getNama();
            }
            Employee e5 = o.getReceiveApprvEmpl();
            if (e5 != null) {
                this.nameReceiveApprove = e5.getNama();
            }
            Employee e6 = o.getPickupEmpl();
            if (e6 != null) {
                this.namePickup = e6.getNama();
            }
            Employee e7 = o.getProcEmpl();
            if (e7 != null) {
                this.nameProc = e7.getNama();
            }
        }
    }

    public void castToProcurementForRequest(Procurement procurement) {
        if (procurement != null) {
            procurement.setRequestId(requestId);
            procurement.setHarga(harga);
        }
    }

    public void castToProcurement(Procurement procurement) {
        if (procurement != null) {
            procurement.setRequestId(requestId);
            procurement.setHarga(harga);
        }
    }

}
