package com.venky.Hrms.enums;

public enum LeaveStatus {
    PENDING(0),
    APPROVE(1),
    REJECTED(2);

    private final int code;

    LeaveStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static LeaveStatus fromCode(int code) {
        for (LeaveStatus status : LeaveStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + code);
    }
}
