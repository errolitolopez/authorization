package com.el.authorization.web.controller;

import com.el.authorization.annotation.validation.NonNull;
import com.el.authorization.annotation.validation.Valid;
import com.el.authorization.annotation.validation.Validated;
import com.el.authorization.domain.req.role.ex.CreateRoleReq;
import com.el.authorization.domain.req.role.ex.UpdateRoleReq;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.facade.RoleFacade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleApiController {

    @Resource
    private RoleFacade roleFacade;

    @Validated
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN_CREATE')")
    public Response<Integer> create(@Valid @RequestBody CreateRoleReq req, HttpServletRequest request, HttpServletResponse response) {
        return roleFacade.create(req);
    }

    @Validated
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN_MODIFY')")
    public Response<Integer> updateById(@Valid @RequestBody UpdateRoleReq req, HttpServletRequest request, HttpServletResponse response) {
        return roleFacade.updateById(req);
    }

    @Validated
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('ADMIN_DELETE')")
    public Response<Integer> deleteById(@Valid @NonNull(message = "The parameter 'id' is required") Long id, HttpServletRequest request, HttpServletResponse response) {
        return roleFacade.deleteById(id);
    }
}
