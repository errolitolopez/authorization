package com.el.authorization.facade;

import com.el.authorization.domain.req.permission.QueryPermissionReq;
import com.el.authorization.domain.req.permission.ex.CreatePermissionReq;
import com.el.authorization.domain.req.permission.ex.UpdatePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.permission.PermissionRsp;

public interface PermissionFacade {
    Response<Paged<PermissionRsp>> query(QueryPermissionReq req);

    Response<Integer> create(CreatePermissionReq req);

    Response<PermissionRsp> selectById(Long id);

    Response<Integer> deleteById(Long id);

    Response<Integer> updateById(UpdatePermissionReq req);
}