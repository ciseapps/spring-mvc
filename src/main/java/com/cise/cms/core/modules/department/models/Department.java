package com.cise.cms.core.modules.department.models;

import com.cise.cms.core.applications.base.BaseModelSignature;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "prc_department")
public class Department extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long departmentId;

    @NotNull(message = "Department cannot be null")
    @Size(min = 6, max = 30, message = "Department cannot be null min char 6 and max 30")
    private String departmentName;

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public long getDepartmentId(){
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
