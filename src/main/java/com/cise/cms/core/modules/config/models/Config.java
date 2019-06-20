package com.cise.cms.core.modules.config.models;

import com.cise.cms.core.applications.base.BaseModelSignature;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "app_config")
public class Config extends BaseModelSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long configurationId;

    private String configuration;

    private String value;

    private String description;

    public void setConfigurationId(long configurationId) {
        this.configurationId = configurationId;
    }

    public long getConfigurationId(){
        return configurationId;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getConfiguration(){
        return configuration;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
