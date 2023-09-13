package com.szsc.customermanagement.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.entity.Company;

@Mapper
public interface MapperUtils {
    MapperUtils INSTANCE = Mappers.getMapper(MapperUtils.class);

    @Mapping(source = "company_code", target = "companyCode")
    @Mapping(source = "company_name", target = "companyName")
    @Mapping(source = "company_abbreviation", target = "companyAbbreviation")
    @Mapping(source = "company_type", target = "companyType")
    @Mapping(source = "registered_location", target = "registeredLocation")
    @Mapping(source = "unified_social_credit", target = "unifiedSocialCredit")
    @Mapping(source = "registered_address", target = "registeredAddress")
    @Mapping(source = "registered_phone", target = "registeredPhone")
    @Mapping(source = "company_email", target = "companyEmail")
    @Mapping(source = "establishment_date", target = "establishmentDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "registered_capital", target = "registeredCapital")
    @Mapping(source = "legal_representative_name", target = "legalRepresentativeName")
    @Mapping(source = "legal_representative_phone", target = "legalRepresentativePhone")
    @Mapping(source = "legal_representative_id", target = "legalRepresentativeId")
    @Mapping(source = "industry", target = "industry")
    @Mapping(source = "business_scope", target = "businessScope")
    @Mapping(source = "verified_customer", target = "verifiedCustomer")
    @Mapping(source = "szse_member", target = "szseMember")
    @Mapping(source = "szse_member_code", target = "szseMemberCode")
    @Mapping(source = "szse_member_abbreviation", target = "szseMemberAbbreviation")
    @Mapping(source = "customer_status", target = "customerStatus")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "province", target = "province")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "business_license_number", target = "businessLicenseNumber")
    @Mapping(source = "business_license_expiry", target = "businessLicenseExpiry", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "primary_contact_name", target = "primaryContactName")
    @Mapping(source = "primary_contact_position", target = "primaryContactPosition")
    @Mapping(source = "primary_contact_phone", target = "primaryContactPhone")
    @Mapping(source = "primary_contact_email", target = "primaryContactEmail")
    Company dtoToEntity(CompanyDTO companyDTO);


    @Mapping(source = "companyCode", target = "company_code")
    @Mapping(source = "companyName", target = "company_name")
    @Mapping(source = "companyAbbreviation", target = "company_abbreviation")
    @Mapping(source = "companyType", target = "company_type")
    @Mapping(source = "registeredLocation", target = "registered_location")
    @Mapping(source = "unifiedSocialCredit", target = "unified_social_credit")
    @Mapping(source = "registeredAddress", target = "registered_address")
    @Mapping(source = "registeredPhone", target = "registered_phone")
    @Mapping(source = "companyEmail", target = "company_email")
    @Mapping(source = "establishmentDate", target = "establishment_date", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "registeredCapital", target = "registered_capital")
    @Mapping(source = "legalRepresentativeName", target = "legal_representative_name")
    @Mapping(source = "legalRepresentativePhone", target = "legal_representative_phone")
    @Mapping(source = "legalRepresentativeId", target = "legal_representative_id")
    @Mapping(source = "industry", target = "industry")
    @Mapping(source = "businessScope", target = "business_scope")
    @Mapping(source = "verifiedCustomer", target = "verified_customer")
    @Mapping(source = "szseMember", target = "szse_member")
    @Mapping(source = "szseMemberCode", target = "szse_member_code")
    @Mapping(source = "szseMemberAbbreviation", target = "szse_member_abbreviation")
    @Mapping(source = "customerStatus", target = "customer_status")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "province", target = "province")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "businessLicenseNumber", target = "business_license_number")
    @Mapping(source = "businessLicenseExpiry", target = "business_license_expiry", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "primaryContactName", target = "primary_contact_name")
    @Mapping(source = "primaryContactPosition", target = "primary_contact_position")
    @Mapping(source = "primaryContactPhone", target = "primary_contact_phone")
    @Mapping(source = "primaryContactEmail", target = "primary_contact_email")
    CompanyDTO entityToDto(Company company);
}
