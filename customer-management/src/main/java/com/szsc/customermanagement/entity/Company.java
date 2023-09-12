package com.szsc.customermanagement.entity;

import java.math.BigDecimal;

import com.szsc.customermanagement.enums.LocationEnum;

public class Company {

    private int companyId;
    private String companyCode;
    private String companyName;
    private String companyAbbreviation;
    private String companyType;
    private String registeredLocation;
    private String unifiedSocialCredit;
    private String registeredAddress;
    private String registeredPhone;
    private String companyEmail;
    private String establishmentDate;
    private BigDecimal registeredCapital;
    private String legalRepresentativeName;
    private String legalRepresentativePhone;
    private String legalRepresentativeId;
    private String industry;
    private String businessScope;
    private Boolean verifiedCustomer;
    private Boolean szseMember;
    private String szseMemberCode;
    private String szseMemberAbbreviation;
    private String customerStatus;
    private String country;
    private String province;
    private String city;
    private String businessLicenseNumber;
    private String businessLicenseExpiry;
    private String primaryContactName;
    private String primaryContactPosition;
    private String primaryContactPhone;
    private String primaryContactEmail;

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

    public String getRegisteredLocation() {
        return registeredLocation;
    }

    public void setRegisteredLocation(String registeredLocation) {
        this.registeredLocation = registeredLocation;
    }

    public String getUnifiedSocialCredit() {
        return unifiedSocialCredit;
    }

    public void setUnifiedSocialCredit(String unifiedSocialCredit) {
        this.unifiedSocialCredit = unifiedSocialCredit;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getRegisteredPhone() {
        return registeredPhone;
    }

    public void setRegisteredPhone(String registeredPhone) {
        this.registeredPhone = registeredPhone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(String establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getLegalRepresentativeName() {
        return legalRepresentativeName;
    }

    public void setLegalRepresentativeName(String legalRepresentativeName) {
        this.legalRepresentativeName = legalRepresentativeName;
    }

    public String getLegalRepresentativePhone() {
        return legalRepresentativePhone;
    }

    public void setLegalRepresentativePhone(String legalRepresentativePhone) {
        this.legalRepresentativePhone = legalRepresentativePhone;
    }

    public String getLegalRepresentativeId() {
        return legalRepresentativeId;
    }

    public void setLegalRepresentativeId(String legalRepresentativeId) {
        this.legalRepresentativeId = legalRepresentativeId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public boolean getVerifiedCustomer() {
        return verifiedCustomer;
    }

    public void setVerifiedCustomer(boolean verifiedCustomer) {
        this.verifiedCustomer = verifiedCustomer;
    }

    public boolean getSzseMember() {
        return szseMember;
    }

    public void setSzseMember(boolean szseMember) {
        this.szseMember = szseMember;
    }

    public String getSzseMemberCode() {
        return szseMemberCode;
    }

    public void setSzseMemberCode(String szseMemberCode) {
        this.szseMemberCode = szseMemberCode;
    }

    public String getSzseMemberAbbreviation() {
        return szseMemberAbbreviation;
    }

    public void setSzseMemberAbbreviation(String szseMemberAbbreviation) {
        this.szseMemberAbbreviation = szseMemberAbbreviation;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBusinessLicenseNumber() {
        return businessLicenseNumber;
    }

    public void setBusinessLicenseNumber(String businessLicenseNumber) {
        this.businessLicenseNumber = businessLicenseNumber;
    }

    public String getBusinessLicenseExpiry() {
        return businessLicenseExpiry;
    }

    public void setBusinessLicenseExpiry(String businessLicenseExpiry) {
        this.businessLicenseExpiry = businessLicenseExpiry;
    }

    public String getPrimaryContactName() {
        return primaryContactName;
    }

    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }

    public String getPrimaryContactPosition() {
        return primaryContactPosition;
    }

    public void setPrimaryContactPosition(String primaryContactPosition) {
        this.primaryContactPosition = primaryContactPosition;
    }

    public String getPrimaryContactPhone() {
        return primaryContactPhone;
    }

    public void setPrimaryContactPhone(String primaryContactPhone) {
        this.primaryContactPhone = primaryContactPhone;
    }

    public String getPrimaryContactEmail() {
        return primaryContactEmail;
    }

    public void setPrimaryContactEmail(String primaryContactEmail) {
        this.primaryContactEmail = primaryContactEmail;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAbbreviation='" + companyAbbreviation + '\'' +
                ", companyType='" + companyType + '\'' +
                ", registeredLocation='" + registeredLocation + '\'' +
                ", unifiedSocialCredit='" + unifiedSocialCredit + '\'' +
                ", registeredAddress='" + registeredAddress + '\'' +
                ", registeredPhone='" + registeredPhone + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", establishmentDate='" + establishmentDate + '\'' +
                ", registeredCapital=" + registeredCapital +
                ", legalRepresentativeName='" + legalRepresentativeName + '\'' +
                ", legalRepresentativePhone='" + legalRepresentativePhone + '\'' +
                ", legalRepresentativeId='" + legalRepresentativeId + '\'' +
                ", industry='" + industry + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", isVerifiedCustomer=" + verifiedCustomer +
                ", isSzseMember=" + szseMember +
                ", szseMemberCode='" + szseMemberCode + '\'' +
                ", szseMemberAbbreviation='" + szseMemberAbbreviation + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", businessLicenseNumber='" + businessLicenseNumber + '\'' +
                ", businessLicenseExpiry='" + businessLicenseExpiry + '\'' +
                ", primaryContactName='" + primaryContactName + '\'' +
                ", primaryContactPosition='" + primaryContactPosition + '\'' +
                ", primaryContactPhone='" + primaryContactPhone + '\'' +
                ", primaryContactEmail='" + primaryContactEmail + '\'' +
                '}';
    }
}
