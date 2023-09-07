package com.el.authorization.constant;

public enum FlagEnum {
    ENABLED((short) 1), DISABLED((short) 0);

    public final Short VALUE;

    FlagEnum(Short VALUE) {
        this.VALUE = VALUE;
    }
}
