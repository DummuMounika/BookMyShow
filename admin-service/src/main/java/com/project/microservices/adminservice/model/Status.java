package com.project.microservices.adminservice.model;

public enum Status {
    AVAILABLE("0"),
    BOOKED("1"),
	PENDING("2");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}

