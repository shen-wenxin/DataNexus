package com.szsc.customermanagement.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.szsc.customermanagement.enums.LocationEnum;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
