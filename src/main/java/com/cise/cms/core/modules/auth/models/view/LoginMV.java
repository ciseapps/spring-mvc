package com.cise.cms.core.modules.auth.models.view;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginMV {

    @NotNull
    @Email(message = "Email should be valid")
    @Size(min = 6, max = 30)
    private String username;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;

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
}
