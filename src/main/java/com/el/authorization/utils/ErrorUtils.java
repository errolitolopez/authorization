package com.el.authorization.utils;

import com.el.authorization.constant.ResponseStatusEnum;
import com.el.authorization.domain.rsp.Response;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;

public class ErrorUtils {

    public static Response buildErrorResponse(ResponseStatusEnum responseStatus, String name, String message) {
        Response response = new Response();
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("errors", new ArrayList<>());

        if (ObjectUtils.allNotNull(name, message)) {
            errors.put("errors", Collections.singletonList(createError(name, message)));
        }
        response.setResult(errors);
        response.setStatus(responseStatus.VALUE);
        response.setMessage(responseStatus.MESSAGE);
        return response;
    }

    public static Response buildErrorResponse(ResponseStatusEnum responseStatus) {
        return buildErrorResponse(responseStatus, null, null);
    }

    public static Response buildErrorResponse(ResponseStatusEnum responseStatus, List<Object> errors) {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("errors", errors);

        Response response = new Response();
        response.setStatus(responseStatus.VALUE);
        response.setMessage(responseStatus.MESSAGE);
        response.setResult(error);
        return response;
    }

    public static Object createError(String name, String message) {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("name", name);
        error.put("message", message);
        return error;
    }
}
