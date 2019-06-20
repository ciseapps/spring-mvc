package com.cise.cms.core.modules.employe.modelview;

import com.cise.cms.core.modules.auth.models.dao.AppUser;
import com.cise.cms.core.modules.employe.models.Employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeFormMV {

    int id;

    @NotNull(message = "NIK cannot be null")
    @Size(min = 6, max = 30, message = "NIK cannot be empty min char 6 and max 30")
    String nik = "";

    @NotNull(message = "Nama cannot be null")
    @NotBlank(message = "Nama cannot be empty")
    @Size(min = 6, max = 30, message = "Name cannot be empty min char 6 and max 30")
    String nama;

    @NotNull(message = "Department cannot be null")
    @NotBlank(message = "Department cannot be empty")
    String departmentId;

    @NotNull(message = "Jabatan cannot be null")
    @NotBlank(message = "Jabatan cannot be empty")
    String jabatanId;

    String appUserId;

    String roleId;

    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be empty")
    String username;

    String password;

    String passwordConfirm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(String jabatanId) {
        this.jabatanId = jabatanId;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Employee initEmployee(Employee employee) {
        if (id == 0) employee.setNik(nik);
        employee.setNama(nama);
        employee.setDepartmentId(Long.valueOf(departmentId));
        employee.setJabatanId(Long.valueOf(jabatanId));
        return employee;
    }

    public AppUser initAppUser(AppUser appUser) {
        if (id == 0) {
            appUser.setUsername(username);
            appUser.setExternalId(nik);
            appUser.setPassword(password);
        }
        appUser.setRoleId(Integer.valueOf(roleId));
        return appUser;
    }

    public void initFrom(Employee employee, AppUser appUser) {
        if (employee != null) {
            this.id = 1;
            this.nik = String.valueOf(employee.getNik());
            this.nama = employee.getNama();
            this.departmentId = String.valueOf(employee.getDepartmentId());
            this.jabatanId = String.valueOf(employee.getJabatanId());
        }
        if (appUser != null) {
            this.username = appUser.getUsername();
        }

    }

}
