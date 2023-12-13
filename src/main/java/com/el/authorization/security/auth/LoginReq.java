package com.el.authorization.security.auth;

import com.el.authorization.annotation.validation.Required;

public class LoginReq {
    @Required
    private String username;

    @Required
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
