package com.venky.Hrms.enums;

public enum Status {
    ACTIVE(0),
    INACTIVE(1);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status fromCode(int code) {
        for (Status status : Status.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + code);
    }
}
