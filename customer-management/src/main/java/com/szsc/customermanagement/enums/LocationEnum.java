package com.szsc.customermanagement.enums;

public enum LocationEnum {
    CHINA_MAINLAND("Mainland"),
    HONGKONG("HongKong"),
    OVERSEAS("Overseas");

    private final String displayName;

    LocationEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}