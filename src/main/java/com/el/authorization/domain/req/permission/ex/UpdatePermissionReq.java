package com.el.authorization.domain.req.permission.ex;

import com.el.authorization.annotation.validation.Required;

public class UpdatePermissionReq {
    @Required
    private Long roleId;

    @Required
    private Long permissionId;

    @Required(min = 3, max = 16)
    private String permissionCode;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
