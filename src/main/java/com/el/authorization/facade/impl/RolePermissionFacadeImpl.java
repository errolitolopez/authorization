package com.el.authorization.facade.impl;

import com.el.authorization.domain.req.rolepermission.QueryRolePermissionReq;
import com.el.authorization.domain.req.rolepermission.RolePermissionReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.rolepermission.RolePermissionRsp;
import com.el.authorization.facade.RolePermissionFacade;
import com.el.authorization.service.RolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RolePermissionFacadeImpl implements RolePermissionFacade {
    
    @Resource
    private RolePermissionService rolePermissionService;

    @Override
    public Response<Paged<RolePermissionRsp>> query(QueryRolePermissionReq req) {
        Response response = new Response();
        response.setResult(rolePermissionService.query(req));
        return response;
    }

    @Override
    public Response<Integer> create(RolePermissionReq req) {
        Response response = new Response();
        response.setResult(rolePermissionService.create(req));
        return response;
    }

    @Override
    public Response<RolePermissionRsp> selectById(Long id) {
        Response response = new Response();
        response.setResult(rolePermissionService.selectById(id));
        return response;
    }

    @Override
    public Response<Integer> deleteById(Long id) {
        Response response = new Response();
        response.setResult(rolePermissionService.deleteById(id));
        return response;
    }

    @Override
    public Response<Integer> updateById(RolePermissionReq req) {
        Response response = new Response();
        response.setResult(rolePermissionService.updateById(req));
        return response;
    }
}