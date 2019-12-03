package com.epam.preprod.biletska.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {

    CREATED("created"),

    ACCEPTED("accepted"),

    CONFIRMED("confirmed"),

    PREPARING("preparing"),

    SENT("sent"),

    COMPLETED("completed"),

    CANCELED("canceled");

    private static Map<String, OrderStatus> lookup = new HashMap<>();
    private String value;

    static {
        Arrays.stream(OrderStatus.values()).forEach(e -> lookup.put(e.getValue(), e));
    }

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OrderStatus getByValue(String value) {
        return lookup.get(value);
    }
}
