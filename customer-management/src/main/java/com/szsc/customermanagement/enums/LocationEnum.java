package com.szsc.customermanagement.enums;

public enum LocationEnum {
    CHINA_MAINLAND("中国大陆"),
    HONGKONG("中国香港"),
    OVERSEAS("境外");

    private final String displayName;

    LocationEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}