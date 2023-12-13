package com.el.authorization.security.auth;

import com.el.authorization.constant.ResponseStatusEnum;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.security.jwt.JwtService;
import com.el.authorization.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    public Response authenticate(LoginReq request) {

        UserDetails userDetails;

        try {
            userDetails = userDetailsService.selectUserDetailsByUsername(request.getUsername());
        } catch (UsernameNotFoundException e) {
            return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR,"username", "The field 'username' is incorrect");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(token);
        String jwtToken = jwtService.generateToken(userDetails);
        Response response = new Response();
        LoginRsp loginRsp = new LoginRsp(jwtToken);
        response.setResult(loginRsp);
        return response;
    }
}
