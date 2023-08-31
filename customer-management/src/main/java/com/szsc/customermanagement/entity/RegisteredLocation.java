package com.szsc.customermanagement.entity;

import java.util.Objects;

import com.szsc.customermanagement.enums.LocationEnum;

public class RegisteredLocation {
    private int companyId;
    private LocationEnum location;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public LocationEnum getLocation() {
        return location;
    }

    public void setLocation(LocationEnum location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "RegisteredLocation{" +
                "companyId=" + companyId +
                ", location=" + location.getDisplayName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredLocation that = (RegisteredLocation) o;
        return companyId == that.companyId &&
                location == that.location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, location);
    }
}