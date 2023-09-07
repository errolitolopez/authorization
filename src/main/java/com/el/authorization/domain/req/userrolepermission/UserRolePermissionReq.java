package com.el.authorization.domain.req.userrolepermission;

import java.util.Date;

public class UserRolePermissionReq {
    private Long id;

    private Long userId;

    private Long roleId;

    private Long permissionId;

    private Short flag;

    private Date createdDate;

    private String createdBy;

    private Date lastUpdatedDate;

    private String lastUpdatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public Short getFlag() {
        return flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public String toString() {
        return "UserRolePermissionReq{" +
        		"id=" + id +
        		", userId=" + userId +
        		", roleId=" + roleId +
        		", permissionId=" + permissionId +
        		", flag=" + flag +
        		", createdDate=" + createdDate +
        		", createdBy='" + createdBy + '\'' +
        		", lastUpdatedDate=" + lastUpdatedDate +
        		", lastUpdatedBy='" + lastUpdatedBy + '\'' +
        '}';
    }
}