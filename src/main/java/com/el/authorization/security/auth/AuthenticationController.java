package com.el.authorization.security.auth;

import com.el.authorization.annotation.validation.Valid;
import com.el.authorization.annotation.validation.Validated;
import com.el.authorization.domain.rsp.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Resource
    private AuthenticationService authenticationService;

    @Validated
    @PostMapping("/authenticate")
    public Response authenticate(@Valid @RequestBody LoginReq req) {
        return authenticationService.authenticate(req);
    }
}
