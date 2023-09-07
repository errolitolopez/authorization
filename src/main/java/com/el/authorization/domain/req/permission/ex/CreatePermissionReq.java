package com.el.authorization.domain.req.permission.ex;

import com.el.authorization.annotation.validation.Required;

public class CreatePermissionReq {

    @Required
    private Long roleId;

    @Required(min = 3, max = 16)
    private String permissionCode;

    @Required(min = 3, max = 32)
    private String permissionName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
