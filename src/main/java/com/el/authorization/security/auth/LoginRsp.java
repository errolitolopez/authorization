package com.el.authorization.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRsp {
    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LoginRsp(String accessToken) {
        this.accessToken = accessToken;
    }
}
