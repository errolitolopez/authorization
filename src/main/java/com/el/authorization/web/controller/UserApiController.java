package com.el.authorization.web.controller;

import com.el.authorization.annotation.validation.Valid;
import com.el.authorization.annotation.validation.Validated;
import com.el.authorization.domain.req.user.ex.CreateUserReq;
import com.el.authorization.domain.req.user.ex.UpdatePasswordReq;
import com.el.authorization.domain.req.user.ex.UpdateUsernameReq;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.facade.UserFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserApiController {

    @Resource
    private UserFacade userFacade;

    @Validated
    @PostMapping("/create")
    public Response<Integer> create(@Valid @RequestBody CreateUserReq req, HttpServletRequest request, HttpServletResponse response) {
        return userFacade.createUser(req);
    }

    @Validated
    @PostMapping("/update/username")
    public Response<Integer> updateUsername(@Valid @RequestBody UpdateUsernameReq req, HttpServletRequest request, HttpServletResponse response) {
        return userFacade.updateUsername(req);
    }

    @Validated
    @PostMapping("/update/password")
    public Response<Integer> updatePassword(@Valid @RequestBody UpdatePasswordReq req, HttpServletRequest request, HttpServletResponse response) {
        return userFacade.updatePassword(req);
    }
}
