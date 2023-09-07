package com.el.authorization.service;

import com.el.authorization.domain.req.rolepermission.QueryRolePermissionReq;
import com.el.authorization.domain.req.rolepermission.RolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.rolepermission.RolePermissionRsp;

import java.util.List;

public interface RolePermissionService {
    Paged<RolePermissionRsp> query(QueryRolePermissionReq req);

    Integer create(RolePermissionReq req);

    RolePermissionRsp selectById(Long id);

    Integer deleteById(Long id);

    Integer updateById(RolePermissionReq req);

    List<RolePermissionRsp> selectRolePermissionListByRoleId(Long id);

    Integer deleteByPermissionId(Long id);

    Integer deleteByRoleId(Long id);
}
