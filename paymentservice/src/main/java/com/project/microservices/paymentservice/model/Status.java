package com.project.microservices.paymentservice.model;

public enum Status {
    AVAILABLE(0, "0", "Available"),
    BOOKED(1, "1", "Booked"),
    PENDING(2, "2", "Pending");

    private final Integer intValue;
    private final String stringValue;
    private final String description;

    Status(Integer intValue, String stringValue, String description) {
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.description = description;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public String toString() {
        return description;
    }

    // Static method to find Status by integer value
    public static Status fromIntValue(Integer value) {
        for (Status status : Status.values()) {
            if (status.intValue.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown integer value: " + value);
    }

    // Static method to find Status by string value
    public static Status fromStringValue(String value) {
        for (Status status : Status.values()) {
            if (status.stringValue.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown string value: " + value);
    }

    // Static method to validate integer values
    public static boolean isValidIntValue(int value) {
        for (Status status : Status.values()) {
            if (status.intValue == value) {
                return true;
            }
        }
        return false;
    }

    // Static method to validate string values
    public static boolean isValidStringValue(String value) {
        for (Status status : Status.values()) {
            if (status.stringValue.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
