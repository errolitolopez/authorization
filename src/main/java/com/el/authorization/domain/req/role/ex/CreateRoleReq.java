package com.el.authorization.domain.req.role.ex;

import com.el.authorization.annotation.validation.Required;

import java.util.Set;

public class CreateRoleReq {
    @Required(min = 3, max = 16)
    private String roleCode;

    @Required(min = 3, max = 32)
    private String roleName;

    @Required
    private Set<String> permissions;

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

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
