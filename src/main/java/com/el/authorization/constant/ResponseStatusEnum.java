package com.el.authorization.constant;

public enum ResponseStatusEnum {
    SUCCESS(100, "Success"),
    FAILED(500, "Failed"),
    BAD_REQUEST(400, "Bad request");

    public final Integer VALUE;
    public final String MESSAGE;

    ResponseStatusEnum(Integer value, String message) {
        VALUE = value;
        MESSAGE = message;
    }
}
