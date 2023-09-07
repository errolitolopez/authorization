package com.el.authorization.service;

import com.el.authorization.domain.req.role.QueryRoleReq;
import com.el.authorization.domain.req.role.RoleReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.role.RoleRsp;

public interface RoleService {
    Paged<RoleRsp> query(QueryRoleReq req);

    Integer create(RoleReq req);

    RoleRsp selectById(Long id);

    Integer deleteById(Long id);

    Integer updateById(RoleReq req);

    RoleRsp selectByRoleCode(String roleCode);
}