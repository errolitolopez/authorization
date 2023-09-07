package com.el.authorization.domain.req.user.ex;

import com.el.authorization.annotation.validation.Pattern;
import com.el.authorization.annotation.validation.Required;
import com.el.authorization.utils.RegExUtils;

import java.util.List;

public class CreateUserReq {
    @Required(min = 5, max = 20)
    @Pattern(patterns = RegExUtils.USERNAME_PATTERN, message = "The field 'username' can contain only letters, numbers, _ and .")
    private String username;

    @Required(min = 8, max = 32)
    @Pattern(patterns = RegExUtils.STRONG_PASSWORD_PATTERN, message = "The field 'password' must contain mix of lowercase, uppercase, numeric and special characters")
    private String password;

    private List<RoleIdPermissionIdsReq> roles;

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

    public List<RoleIdPermissionIdsReq> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleIdPermissionIdsReq> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "CreateUserReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
