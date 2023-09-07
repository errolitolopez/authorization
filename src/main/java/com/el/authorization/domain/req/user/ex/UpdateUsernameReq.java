package com.el.authorization.domain.req.user.ex;

import com.el.authorization.annotation.validation.Pattern;
import com.el.authorization.annotation.validation.Required;
import com.el.authorization.utils.RegExUtils;

public class UpdateUsernameReq {
    @Required
    private Long id;

    @Required(min = 5, max = 20)
    @Pattern(patterns = RegExUtils.USERNAME_PATTERN, message = "The field 'username' can contain only letters, numbers, _ and .")
    private String username;

    @Required
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
