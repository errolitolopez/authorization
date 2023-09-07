package com.el.authorization.domain.req.user.ex;

import com.el.authorization.annotation.validation.Pattern;
import com.el.authorization.annotation.validation.Required;
import com.el.authorization.utils.RegExUtils;

public class UpdatePasswordReq {
    @Required
    private Long id;

    @Required
    private String password;

    @Required(min = 8, max = 32)
    @Pattern(patterns = RegExUtils.STRONG_PASSWORD_PATTERN, message = "The field 'newPassword' must contain mix of lowercase, uppercase, numeric and special characters")
    private String newPassword;

    @Required
    private String confirmPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
