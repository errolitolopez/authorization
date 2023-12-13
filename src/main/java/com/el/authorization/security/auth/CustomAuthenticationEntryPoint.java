package com.el.authorization.security.auth;

import com.el.authorization.constant.ResponseStatusEnum;
import com.el.authorization.utils.ErrorUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        logger.debug("CustomAuthenticationEntryPoint.commence has encountered an error {}", e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        String error;
        if (e instanceof InsufficientAuthenticationException) {
            error = new Gson().toJson(ErrorUtils.buildErrorResponse(ResponseStatusEnum.FORBIDDEN,"Authorization", "The header 'Authorization' is required"));
        } else {
            error = new Gson().toJson(ErrorUtils.buildErrorResponse(ResponseStatusEnum.FORBIDDEN,"password", "The field 'password' is incorrect"));
        }
        response.getOutputStream().println(error);
    }
}
