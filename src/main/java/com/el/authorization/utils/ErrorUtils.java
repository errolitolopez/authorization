package com.el.authorization.utils;

import com.el.authorization.constant.ResponseStatusEnum;
import com.el.authorization.domain.rsp.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ErrorUtils {
    public static Response buildErrorResponse(ResponseStatusEnum responseStatus, List<Object> errors) {
        if (errors == null || errors.size() == 0) {
            return null;
        }
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("errors", errors);

        Response response = new Response();
        response.setStatus(responseStatus.VALUE);
        response.setMessage(responseStatus.MESSAGE);
        response.setResult(error);
        return response;
    }

    public static Response buildErrorResponse(List<Object> errors) {
        if (errors == null || errors.size() == 0) {
            return null;
        }
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("errors", errors);

        Response response = new Response();
        response.setStatus(ResponseStatusEnum.BAD_REQUEST.VALUE);
        response.setMessage(ResponseStatusEnum.BAD_REQUEST.MESSAGE);
        response.setResult(error);
        return response;
    }

    public static Response buildErrorResponse(Object error) {
        return buildErrorResponse(Collections.singletonList(error));
    }

    public static Response buildErrorResponse(ResponseStatusEnum responseStatus, Object error) {
        return buildErrorResponse(responseStatus, Collections.singletonList(error));
    }

    public static Response buildErrorResponse(String field, String message) {
        Map<String, Object> error = new LinkedHashMap<>();
        if (StringUtils.isNotEmpty(field)) {
            error.put("field", field);
        }
        error.put("message", message);
        return buildErrorResponse(Collections.singletonList(error));
    }

    public static Response buildErrorResponse(String message) {
        return buildErrorResponse("", message);
    }

    public static Object createError(String field, String message) {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("field", field);
        error.put("message", message);
        return error;
    }
}
