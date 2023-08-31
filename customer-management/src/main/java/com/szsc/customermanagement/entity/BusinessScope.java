package com.szsc.customermanagement.entity;

import java.util.Objects;

public class BusinessScope {

    private int companyId;
    private String scope;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    // toString()
    @Override
    public String toString() {
        return "BusinessScope{" +
                ", companyId=" + companyId +
                ", scope='" + scope + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessScope that = (BusinessScope) o;
        return companyId == that.companyId &&
                Objects.equals(scope, that.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, scope);
    }
}