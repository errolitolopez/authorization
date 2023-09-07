package com.el.authorization.facade;

import com.el.authorization.domain.req.userrolepermission.QueryUserRolePermissionReq;
import com.el.authorization.domain.req.userrolepermission.UserRolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.userrolepermission.UserRolePermissionRsp;

public interface UserRolePermissionFacade {
    Response<Paged<UserRolePermissionRsp>> query(QueryUserRolePermissionReq req);

    Response<Integer> create(UserRolePermissionReq req);

    Response<UserRolePermissionRsp> selectById(Long id);

    Response<Integer> deleteById(Long id);

    Response<Integer> updateById(UserRolePermissionReq req);
}