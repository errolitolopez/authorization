package com.el.authorization.aspect;

import com.el.authorization.annotation.validation.*;
import com.el.authorization.constant.ResponseStatusEnum;
import com.el.authorization.utils.ErrorUtils;
import com.el.authorization.utils.RegExUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Configuration
public class ValidateAspect {
    private static final Logger logger = LoggerFactory.getLogger(ValidateAspect.class);

    @Pointcut(value = "@annotation(validated)")
    public void callAt(Validated validated) {
    }

    @Around(value = "callAt(validated)", argNames = "pjp,validated")
    public Object around(ProceedingJoinPoint pjp, Validated validated) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        logger.info("ValidateAspect has been invoked in {}.{}", pjp.getTarget().getClass().getSimpleName(), method.getName());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null && pjp.getArgs().length > 0) {
            HttpServletResponse httpServletResponse = ((ServletRequestAttributes) requestAttributes).getResponse();
            if (httpServletResponse != null) {
                Object[] args = pjp.getArgs();

                String[] parameterNames = methodSignature.getParameterNames();

                for (int i = 0; i < method.getParameterCount(); i++) {
                    Annotation[] parameterAnnotation = method.getParameterAnnotations()[i];

                    if (!containsValidAnnotation(parameterAnnotation)) {
                        continue;
                    }

                    Object arg = args[i];
                    String parameterName = parameterNames[i];
                    for (Annotation annotation : parameterAnnotation) {
                        if (annotation instanceof NonNull) {
                            NonNull nonNull = (NonNull) annotation;
                            if (ObjectUtils.isEmpty(arg)) {
                                String message = "The field '" + parameterName + "' is required";
                                if (StringUtils.isNotEmpty(nonNull.message())) {
                                    message = nonNull.message();
                                }
                                return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR, parameterName, message);
                            }
                        }
                    }

                    Class<?> clazz = arg.getClass();
                    List<Object> errors = new ArrayList<>();
                    for (Field field : clazz.getDeclaredFields()) {
                        Annotation[] annotations = field.getAnnotations();
                        if (annotations.length == 0) {
                            continue;
                        }
                        Object fieldValue = executeGetMethod(field, clazz, arg);
                        String fieldName = field.getName();

                        for (Annotation annotation : annotations) {
                            if (isErrorExists(errors, fieldName)) {
                                break;
                            }
                            Map<String, Object> error = new HashMap<>();
                            error.put("field", fieldName);

                            if (annotation instanceof Required) {
                                Required required = (Required) annotation;
                                int min = Math.max(required.min(), 1);
                                int max = Math.max(required.max(), 1);
                                String message = required.message();
                                if (ObjectUtils.isEmpty(fieldValue)) {
                                    error.put("message", "The field '" + fieldName + "' is required");
                                    errors.add(error);
                                } else {
                                    if (fieldValue instanceof CharSequence) {
                                        int len = ((CharSequence) fieldValue).length();
                                        if (len < min || len > max) {
                                            if (min >= max) {
                                                error.put("message", "The field '" + fieldName + "' must be a string with a length of " + min);
                                            } else {
                                                error.put("message", "The field '" + fieldName + "' must be a string with a minimum length of " + min + " and maximum length of " + max);
                                            }
                                            if (StringUtils.isNotBlank(message)) {
                                                error.put("message", message);
                                            }
                                            errors.add(error);
                                        }
                                    } else if (fieldValue.getClass().isArray() || fieldValue instanceof Collection<?> || fieldValue instanceof Map<?, ?>) {
                                        int len = CollectionUtils.size(fieldValue);
                                        if (len < min || len > max) {
                                            if (min >= max) {
                                                error.put("message", "The field '" + fieldName + "' must be a array type with a size of " + min);
                                            } else {
                                                error.put("message", "The field '" + fieldName + "' must be a array type with a minimum size of " + min + " and maximum size of " + max);
                                            }
                                            if (StringUtils.isNotBlank(message)) {
                                                error.put("message", message);
                                            }
                                            errors.add(error);
                                        }
                                    }
                                }
                            } else if (annotation instanceof Pattern) {
                                Pattern pattern = (Pattern) annotation;
                                if (fieldValue instanceof CharSequence) {
                                    if (ObjectUtils.isEmpty(fieldValue) || !RegExUtils.isMatch(pattern.patterns(), fieldValue.toString())) {
                                        error.put("message", pattern.message());
                                        errors.add(error);
                                    }
                                }
                            }
                        }
                    }

                    if (errors.size() > 0) {
                        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return ErrorUtils.buildErrorResponse(ResponseStatusEnum.VALIDATION_ERROR, errors);
                    }
                }
            } else {
                return null;
            }
        }
        return pjp.proceed();
    }

    private boolean isErrorExists(List<Object> errors, String fieldName) {
        for (Object error : errors) {
            Map<String, Object> map = (Map<String, Object>) error;
            if (map.containsKey("field")) {
                String field = map.get("field").toString();
                if (field.equals(fieldName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Object executeGetMethod(Field field, Class<?> clazz, Object arg) {
        Method method;
        Object methodValue = null;
        try {
            method = clazz.getMethod("get" + StringUtils.capitalize(field.getName()));
        } catch (NoSuchMethodException e) {
            logger.error("no method get for field={}", field.getName());
            return null;
        }
        try {
            methodValue = method.invoke(arg);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("invoking of method {} has encountered an error={}", method.getName(), e.getMessage());
        }
        return methodValue;
    }

    private boolean containsValidAnnotation(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof Valid) {
                return true;
            }
        }
        return false;
    }
}
