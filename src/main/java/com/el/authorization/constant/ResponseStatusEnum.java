package com.el.authorization.constant;

public enum ResponseStatusEnum {
    SUCCESS(100, "Success"),
    FAILED(500, "Failed"),
    BAD_REQUEST(400, "Bad request"),
    FORBIDDEN(403, "Forbidden"),
    UNABLE_TO_ACCESS_RESOURCE(403, "You don't have permission to access this resource"),
    VALIDATION_ERROR(400, "Validation error");

    public final Integer VALUE;
    public final String MESSAGE;

    ResponseStatusEnum(Integer value, String message) {
        VALUE = value;
        MESSAGE = message;
    }
}
