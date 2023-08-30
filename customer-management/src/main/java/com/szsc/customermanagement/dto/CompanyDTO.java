package com.szsc.customermanagement.dto;

import java.util.Objects;

public class CompanyDTO {
    private String companyCode;
    private String companyName;
    private String companyAbbreviation;
    private String companyType;

    // Getters and setters

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

    // toString(), equals(), hashCode() methods

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAbbreviation='" + companyAbbreviation + '\'' +
                ", companyType='" + companyType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDTO that = (CompanyDTO) o;
        return Objects.equals(companyCode, that.companyCode) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(companyAbbreviation, that.companyAbbreviation) &&
                Objects.equals(companyType, that.companyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCode, companyName, companyAbbreviation, companyType);
    }
    
}
