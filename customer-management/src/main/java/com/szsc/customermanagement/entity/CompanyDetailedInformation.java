package com.szsc.customermanagement.entity;
import java.util.Date;
import java.util.Objects;

public class CompanyDetailedInformation {
    private int companyId;
    private String unifiedSocialCredit;
    private String registeredAddress;
    private String registeredPhone;
    private String companyEmail;
    private Date establishmentDate;
    private double registeredCapital;
    private String legalRepresentativeName;
    private String legalRepresentativePhone;
    private String legalRepresentativeId;
    private String industry;
    private String businessScope;
    private boolean isVerifiedCustomer;
    private String customerStatus;
    private String country;
    private String province;
    private String city;
    private String businessLicenseNumber;
    private Date businessLicenseExpiry;
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

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public double getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(double registeredCapital) {
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

    public boolean isVerifiedCustomer() {
        return isVerifiedCustomer;
    }

    public void setVerifiedCustomer(boolean verifiedCustomer) {
        isVerifiedCustomer = verifiedCustomer;
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

    public Date getBusinessLicenseExpiry() {
        return businessLicenseExpiry;
    }

    public void setBusinessLicenseExpiry(Date businessLicenseExpiry) {
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

    // toString()
    @Override
    public String toString() {
        return "CompanyDetailedInformation{" +
                "companyId=" + companyId +
                ", unifiedSocialCredit='" + unifiedSocialCredit + '\'' +
                ", registeredAddress='" + registeredAddress + '\'' +
                ", registeredPhone='" + registeredPhone + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", establishmentDate=" + establishmentDate +
                ", registeredCapital=" + registeredCapital +
                ", legalRepresentativeName='" + legalRepresentativeName + '\'' +
                ", legalRepresentativePhone='" + legalRepresentativePhone + '\'' +
                ", legalRepresentativeId='" + legalRepresentativeId + '\'' +
                ", industry='" + industry + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", isVerifiedCustomer=" + isVerifiedCustomer +
                ", customerStatus='" + customerStatus + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", businessLicenseNumber='" + businessLicenseNumber + '\'' +
                ", businessLicenseExpiry=" + businessLicenseExpiry +
                ", primaryContactName='" + primaryContactName + '\'' +
                ", primaryContactPosition='" + primaryContactPosition + '\'' +
                ", primaryContactPhone='" + primaryContactPhone + '\'' +
                ", primaryContactEmail='" + primaryContactEmail + '\'' +
                '}';
    }

    // equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDetailedInformation that = (CompanyDetailedInformation) o;
        return companyId == that.companyId &&
                Double.compare(that.registeredCapital, registeredCapital) == 0 &&
                isVerifiedCustomer == that.isVerifiedCustomer &&
                Objects.equals(unifiedSocialCredit, that.unifiedSocialCredit) &&
                Objects.equals(registeredAddress, that.registeredAddress) &&
                Objects.equals(registeredPhone, that.registeredPhone) &&
                Objects.equals(companyEmail, that.companyEmail) &&
                Objects.equals(establishmentDate, that.establishmentDate) &&
                Objects.equals(legalRepresentativeName, that.legalRepresentativeName) &&
                Objects.equals(legalRepresentativePhone, that.legalRepresentativePhone) &&
                Objects.equals(legalRepresentativeId, that.legalRepresentativeId) &&
                Objects.equals(industry, that.industry) &&
                Objects.equals(businessScope, that.businessScope) &&
                Objects.equals(customerStatus, that.customerStatus) &&
                Objects.equals(country, that.country) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(businessLicenseNumber, that.businessLicenseNumber) &&
                Objects.equals(businessLicenseExpiry, that.businessLicenseExpiry) &&
                Objects.equals(primaryContactName, that.primaryContactName) &&
                Objects.equals(primaryContactPosition, that.primaryContactPosition) &&
                Objects.equals(primaryContactPhone, that.primaryContactPhone) &&
                Objects.equals(primaryContactEmail, that.primaryContactEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, unifiedSocialCredit, registeredAddress, registeredPhone, companyEmail, establishmentDate, registeredCapital, legalRepresentativeName, legalRepresentativePhone, legalRepresentativeId, industry, businessScope, isVerifiedCustomer, customerStatus, country, province, city, businessLicenseNumber, businessLicenseExpiry, primaryContactName, primaryContactPosition, primaryContactPhone, primaryContactEmail);
    }
    


}
