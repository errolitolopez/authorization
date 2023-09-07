package com.el.authorization.web.controller;

import com.el.authorization.annotation.validation.NonNull;
import com.el.authorization.annotation.validation.Valid;
import com.el.authorization.annotation.validation.Validated;
import com.el.authorization.domain.req.permission.ex.CreatePermissionReq;
import com.el.authorization.domain.req.permission.ex.UpdatePermissionReq;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.facade.PermissionFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/permission")
public class PermissionApiController {

    @Resource
    private PermissionFacade permissionFacade;

    @Validated
    @PostMapping("/create")
    public Response<Integer> create(@Valid @RequestBody CreatePermissionReq req, HttpServletRequest request, HttpServletResponse response) {
        return permissionFacade.create(req);
    }

    @Validated
    @PostMapping("/update/code")
    public Response<Integer> update(@Valid @RequestBody UpdatePermissionReq req, HttpServletRequest request, HttpServletResponse response) {
        return permissionFacade.updateById(req);
    }

    @Validated
    @PostMapping("/delete")
    public Response<Integer> deleteById(@Valid @NonNull(message = "The parameter 'id' is required") Long id, HttpServletRequest request, HttpServletResponse response) {
        return permissionFacade.deleteById(id);
    }
}
