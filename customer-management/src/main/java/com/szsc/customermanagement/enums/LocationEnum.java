package com.szsc.customermanagement.enums;

public enum LocationEnum {
    CHINA_MAINLAND("chinaMainland"),
    HONGKONG("HongKong"),
    OVERSEAS("overseas");

    private final String displayName;

    LocationEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}