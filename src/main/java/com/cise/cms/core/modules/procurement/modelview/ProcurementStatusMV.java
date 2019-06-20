package com.cise.cms.core.modules.procurement.modelview;

import com.cise.cms.core.applications.constants.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class ProcurementStatusMV {

    private int status;
    private String Name;

    public ProcurementStatusMV(int status, String name) {
        this.status = status;
        Name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public static List<ProcurementStatusMV> getAllStatus() {
        List<ProcurementStatusMV> statusMVList = new ArrayList<>();
        statusMVList.add(new ProcurementStatusMV(AppConstants.Proc.newRequest, "Request Pending"));
        statusMVList.add(new ProcurementStatusMV(AppConstants.Proc.apvKdep, "New Request"));
        statusMVList.add(new ProcurementStatusMV(AppConstants.Proc.apvProc, "Procurement Pending"));
        statusMVList.add(new ProcurementStatusMV(AppConstants.Proc.apvKdepProc, "Procurement"));
        statusMVList.add(new ProcurementStatusMV(AppConstants.Proc.apvRcv, "W. Receive"));
        statusMVList.add(new ProcurementStatusMV(AppConstants.Proc.apvKdepRcv, "Approve Receive"));
        statusMVList.add(new ProcurementStatusMV(AppConstants.Proc.apvPickup, "Approve Pickup"));
        // statusMVList.add(new ProcurementStatusMV(AppConstants.Proc.finish, "Request Finish"));
        statusMVList.add(new ProcurementStatusMV(AppConstants.Proc.reject, "Request Reject"));
        return statusMVList;
    }
}
