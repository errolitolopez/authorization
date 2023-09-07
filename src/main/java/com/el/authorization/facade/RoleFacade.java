package com.el.authorization.facade;

import com.el.authorization.domain.req.role.QueryRoleReq;
import com.el.authorization.domain.req.role.ex.CreateRoleReq;
import com.el.authorization.domain.req.role.ex.UpdateRoleReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.role.RoleRsp;

public interface RoleFacade {
    Response<Paged<RoleRsp>> query(QueryRoleReq req);

    Response<Integer> create(CreateRoleReq req);

    Response<RoleRsp> selectById(Long id);

    Response<Integer> deleteById(Long id);

    Response<Integer> updateById(UpdateRoleReq req);
}