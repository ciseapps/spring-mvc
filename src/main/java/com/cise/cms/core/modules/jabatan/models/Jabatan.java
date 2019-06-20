package com.cise.cms.core.modules.jabatan.models;

import com.cise.cms.core.applications.base.BaseModelSignature;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "prc_jabatan")
public class Jabatan extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jabatanId;

    @NotNull(message = "Jabatan cannot be null")
    @NotBlank(message = "Jabatan cannot be null")
    private String jabatanName;

    public void setJabatanId(long jabatanId) {
        this.jabatanId = jabatanId;
    }

    public long getJabatanId(){
        return jabatanId;
    }

    public String getJabatanName() {
        return jabatanName;
    }

    public void setJabatanName(String jabatanName) {
        this.jabatanName = jabatanName;
    }
}
