package com.szsc.customermanagement.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.szsc.customermanagement.enums.LocationEnum;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private String companyCode;
    private String companyName;
    private String companyAbbreviation;
    private String companyType;
    private String registeredLocation;
    private String unifiedSocialCredit;
    private String registeredAddress;
    private String registeredPhone;
    private String companyEmail;
    private Date establishmentDate;
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
    private Date businessLicenseExpiry;
    private String primaryContactName;
    private String primaryContactPosition;
    private String primaryContactPhone;
    private String primaryContactEmail;

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAbbreviation='" + companyAbbreviation + '\'' +
                ", companyType='" + companyType + '\'' +
                ", registeredLocation='" + registeredLocation + '\'' +
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
                ", verifiedCustomer=" + verifiedCustomer +
                ", szseMember=" + szseMember +
                ", szseMemberCode='" + szseMemberCode + '\'' +
                ", szseMemberAbbreviation='" + szseMemberAbbreviation + '\'' +
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



}
