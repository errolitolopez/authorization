package com.el.authorization.service;

import com.el.authorization.domain.req.permission.PermissionReq;
import com.el.authorization.domain.req.permission.QueryPermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.permission.PermissionRsp;

import java.util.List;

public interface PermissionService {
    Paged<PermissionRsp> query(QueryPermissionReq req);

    Integer create(PermissionReq req);

    PermissionRsp selectById(Long id);

    Integer deleteById(Long id);

    Integer updateById(PermissionReq req);

    PermissionRsp selectByPermissionCode(String permissionCode);

    List<PermissionRsp> selectByPermissionListIds(List<Long> permissionIds);
}