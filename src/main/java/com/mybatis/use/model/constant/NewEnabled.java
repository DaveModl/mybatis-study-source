package com.mybatis.use.model.constant;

public enum  NewEnabled {
    ENABLED(1),
    DISABLED(0);

    private final int value;

    NewEnabled(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
