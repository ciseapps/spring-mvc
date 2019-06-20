package com.cise.cms.core.modules.home.models;

import com.cise.cms.core.modules.procurement.models.Procurement;
import com.cise.cms.core.modules.procurement.models.ProcurementDetail;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestMV {

    private int requestId;

    private String[] productId;

    private String[] qty;

    @NotNull(message = "Please enter due date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualDate;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String[] getProductId() {
        return productId;
    }

    public void setProductId(String[] productId) {
        this.productId = productId;
    }

    public String[] getQty() {
        return qty;
    }

    public void setQty(String[] qty) {
        this.qty = qty;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public void castToProcurement(Procurement o) {
        if (null != o) {
            o.setDueDate(actualDate);
        }
    }

    public List<ProcurementDetail> castToProcurementDetail(Procurement p, RequestMV o) {
        List<ProcurementDetail> detail = new ArrayList<>();
        if (o != null && o.getProductId() != null && o.getQty() != null) {
            for (int i = 0; i < o.getProductId().length; i++) {
                ProcurementDetail pd = new ProcurementDetail();
                pd.setRequestId(p.getRequestId());
                pd.setProductId(Long.valueOf(o.getProductId()[i]));
                pd.setQty(Long.valueOf(o.getQty()[i]));
                detail.add(pd);
            }
        }
        return detail;
    }
}
