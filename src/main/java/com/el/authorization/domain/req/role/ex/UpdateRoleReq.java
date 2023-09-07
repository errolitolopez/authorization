package com.el.authorization.domain.req.role.ex;

import com.el.authorization.annotation.validation.Required;

public class UpdateRoleReq {

    @Required
    private Long id;

    @Required(min = 3, max = 16)
    private String roleCode;

    @Required(min = 3, max = 16)
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
