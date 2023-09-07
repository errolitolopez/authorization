package com.el.authorization.service;

import com.el.authorization.domain.req.userrolepermission.QueryUserRolePermissionReq;
import com.el.authorization.domain.req.userrolepermission.UserRolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.userrolepermission.UserRolePermissionRsp;

public interface UserRolePermissionService {
    Paged<UserRolePermissionRsp> query(QueryUserRolePermissionReq req);

    Integer create(UserRolePermissionReq req);

    UserRolePermissionRsp selectById(Long id);

    Integer deleteById(Long id);

    Integer updateById(UserRolePermissionReq req);

    Integer deleteByPermissionId(Long id);
}
