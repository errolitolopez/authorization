package com.el.authorization.domain.req.user.ex;

import com.el.authorization.annotation.validation.Required;

import java.util.List;

public class AddRoleUserReq {
    @Required
    private Long id;

    @Required
    private List<RoleIdPermissionIdsReq> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RoleIdPermissionIdsReq> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleIdPermissionIdsReq> roles) {
        this.roles = roles;
    }
}
