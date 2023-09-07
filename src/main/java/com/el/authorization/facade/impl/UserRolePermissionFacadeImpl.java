package com.el.authorization.facade.impl;

import com.el.authorization.domain.req.userrolepermission.QueryUserRolePermissionReq;
import com.el.authorization.domain.req.userrolepermission.UserRolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.userrolepermission.UserRolePermissionRsp;
import com.el.authorization.facade.UserRolePermissionFacade;
import com.el.authorization.service.UserRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRolePermissionFacadeImpl implements UserRolePermissionFacade {
    
    @Resource
    private UserRolePermissionService userRolePermissionService;

    @Override
    public Response<Paged<UserRolePermissionRsp>> query(QueryUserRolePermissionReq req) {
        Response response = new Response();
        response.setResult(userRolePermissionService.query(req));
        return response;
    }

    @Override
    public Response<Integer> create(UserRolePermissionReq req) {
        Response response = new Response();
        response.setResult(userRolePermissionService.create(req));
        return response;
    }

    @Override
    public Response<UserRolePermissionRsp> selectById(Long id) {
        Response response = new Response();
        response.setResult(userRolePermissionService.selectById(id));
        return response;
    }

    @Override
    public Response<Integer> deleteById(Long id) {
        Response response = new Response();
        response.setResult(userRolePermissionService.deleteById(id));
        return response;
    }

    @Override
    public Response<Integer> updateById(UserRolePermissionReq req) {
        Response response = new Response();
        response.setResult(userRolePermissionService.updateById(req));
        return response;
    }
}