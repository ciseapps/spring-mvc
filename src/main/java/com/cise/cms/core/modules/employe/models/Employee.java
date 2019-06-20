package com.cise.cms.core.modules.employe.models;

import com.cise.cms.core.applications.base.BaseModelSignature;
import com.cise.cms.core.modules.auth.models.dao.AppUser;
import com.cise.cms.core.modules.department.models.Department;
import com.cise.cms.core.modules.jabatan.models.Jabatan;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "prc_karyawan")
public class Employee extends BaseModelSignature {

    @Id
    private String nik;

    @NotNull(message = "Nama cannot be null")
    @Size(min = 6, max = 30, message = "Nama cannot be null min char 6 and max 30")
    private String nama;

    @NotNull(message = "Divisi cannot be null")
//    @Size(min = 1, max = 30, message = "Divisi cannot be null min char 6 and max 30")
    @Range(min = 0, max = 90)
    private long departmentId;

    @NotNull(message = "Jabatan cannot be null")
    @Range(min = 0, max = 90)
//    @Size(min = 1, max = 30, message = "Jabatan cannot be null min char 4 and max 30")
    private long jabatanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nik", referencedColumnName = "externalId", insertable = false, updatable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departmentId", nullable = false, insertable = false, updatable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jabatanId", insertable = false, updatable = false)
    private Jabatan jabatan;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public long getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(long jabatanId) {
        this.jabatanId = jabatanId;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Jabatan getJabatan() {
        return jabatan;
    }

    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }
}
