package com.szsc.customermanagement.entity;

import java.util.Objects;

public class Company {

    private int companyId;
    private String companyCode;
    private String companyName;
    private String companyAbbreviation;
    private String companyType;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAbbreviation() {
        return companyAbbreviation;
    }

    public void setCompanyAbbreviation(String companyAbbreviation) {
        this.companyAbbreviation = companyAbbreviation;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    // toString()
    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAbbreviation='" + companyAbbreviation + '\'' +
                ", companyType='" + companyType + '\'' +
                '}';
    }

    //equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return companyId == company.companyId &&
                Objects.equals(companyCode, company.companyCode) &&
                Objects.equals(companyName, company.companyName) &&
                Objects.equals(companyAbbreviation, company.companyAbbreviation) &&
                Objects.equals(companyType, company.companyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, companyCode, companyName, companyAbbreviation, companyType);
    }


}
