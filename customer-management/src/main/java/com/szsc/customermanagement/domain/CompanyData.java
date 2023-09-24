package com.szsc.customermanagement.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyData {
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

}