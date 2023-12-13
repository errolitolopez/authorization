package com.el.authorization.security.jwt;

import com.el.authorization.constant.ResponseStatusEnum;
import com.el.authorization.utils.ErrorUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtExceptionHandlerFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtExceptionHandlerFilter.class);

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof SignatureException) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                String error = new Gson().toJson(ErrorUtils.buildErrorResponse(ResponseStatusEnum.FORBIDDEN, "Authorization", "The header 'Authorization' is invalid"));
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getOutputStream().println(error);
                return;
            }
            throw e;
        }
    }
}
