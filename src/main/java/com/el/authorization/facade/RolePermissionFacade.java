package com.el.authorization.facade;

import com.el.authorization.domain.req.rolepermission.QueryRolePermissionReq;
import com.el.authorization.domain.req.rolepermission.RolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.rolepermission.RolePermissionRsp;

public interface RolePermissionFacade {
    Response<Paged<RolePermissionRsp>> query(QueryRolePermissionReq req);

    Response<Integer> create(RolePermissionReq req);

    Response<RolePermissionRsp> selectById(Long id);

    Response<Integer> deleteById(Long id);

    Response<Integer> updateById(RolePermissionReq req);
}