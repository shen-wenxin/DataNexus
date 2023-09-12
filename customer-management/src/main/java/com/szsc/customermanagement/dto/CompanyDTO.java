package com.szsc.customermanagement.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.szsc.customermanagement.enums.LocationEnum;

public class CompanyDTO {
    private String company_code;
    private String company_name;
    private String company_abbreviation;
    private String company_type;
    private String registered_location;
    private String unified_social_credit;
    private String registered_address;
    private String registered_phone;
    private String company_email;
    private Date establishment_date;
    private BigDecimal registered_capital;
    private String legal_representative_name;
    private String legal_representative_phone;
    private String legal_representative_id;
    private String industry;
    private String business_scope;
    private Boolean is_verified_customer;
    private Boolean is_szse_member;
    private String szse_member_code;
    private String szse_member_abbreviation;
    private String customer_status;
    private String country;
    private String province;
    private String city;
    private String business_license_number;
    private Date business_license_expiry;
    private String primary_contact_name;
    private String primary_contact_position;
    private String primary_contact_phone;
    private String primary_contact_email;

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_abbreviation() {
        return company_abbreviation;
    }

    public void setCompany_abbreviation(String company_abbreviation) {
        this.company_abbreviation = company_abbreviation;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String getRegistered_location() {
        return registered_location;
    }

    public void setRegistered_location(String registered_location) {
        this.registered_location = registered_location;
    }

    public String getUnified_social_credit() {
        return unified_social_credit;
    }

    public void setUnified_social_credit(String unified_social_credit) {
        this.unified_social_credit = unified_social_credit;
    }

    public String getRegistered_address() {
        return registered_address;
    }

    public void setRegistered_address(String registered_address) {
        this.registered_address = registered_address;
    }

    public String getRegistered_phone() {
        return registered_phone;
    }

    public void setRegistered_phone(String registered_phone) {
        this.registered_phone = registered_phone;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public Date getEstablishment_date() {
        return establishment_date;
    }

    public void setEstablishment_date(Date establishment_date) {
        this.establishment_date = establishment_date;
    }

    public BigDecimal getRegistered_capital() {
        return registered_capital;
    }

    public void setRegistered_capital(BigDecimal registered_capital) {
        this.registered_capital = registered_capital;
    }

    public String getLegal_representative_name() {
        return legal_representative_name;
    }

    public void setLegal_representative_name(String legal_representative_name) {
        this.legal_representative_name = legal_representative_name;
    }

    public String getLegal_representative_phone() {
        return legal_representative_phone;
    }

    public void setLegal_representative_phone(String legal_representative_phone) {
        this.legal_representative_phone = legal_representative_phone;
    }

    public String getLegal_representative_id() {
        return legal_representative_id;
    }

    public void setLegal_representative_id(String legal_representative_id) {
        this.legal_representative_id = legal_representative_id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusiness_scope() {
        return business_scope;
    }

    public void setBusiness_scope(String business_scope) {
        this.business_scope = business_scope;
    }

    public Boolean getIs_verified_customer() {
        return is_verified_customer;
    }

    public void setIs_verified_customer(Boolean is_verified_customer) {
        this.is_verified_customer = is_verified_customer;
    }

    public Boolean getIs_szse_member() {
        return is_szse_member;
    }

    public void setIs_szse_member(Boolean is_szse_member) {
        this.is_szse_member = is_szse_member;
    }

    public String getSzse_member_code() {
        return szse_member_code;
    }

    public void setSzse_member_code(String szse_member_code) {
        this.szse_member_code = szse_member_code;
    }

    public String getSzse_member_abbreviation() {
        return szse_member_abbreviation;
    }

    public void setSzse_member_abbreviation(String szse_member_abbreviation) {
        this.szse_member_abbreviation = szse_member_abbreviation;
    }

    public String getCustomer_status() {
        return customer_status;
    }

    public void setCustomer_status(String customer_status) {
        this.customer_status = customer_status;
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

    public String getBusiness_license_number() {
        return business_license_number;
    }

    public void setBusiness_license_number(String business_license_number) {
        this.business_license_number = business_license_number;
    }

    public Date getBusiness_license_expiry() {
        return business_license_expiry;
    }

    public void setBusiness_license_expiry(Date business_license_expiry) {
        this.business_license_expiry = business_license_expiry;
    }

    public String getPrimary_contact_name() {
        return primary_contact_name;
    }

    public void setPrimary_contact_name(String primary_contact_name) {
        this.primary_contact_name = primary_contact_name;
    }

    public String getPrimary_contact_position() {
        return primary_contact_position;
    }

    public void setPrimary_contact_position(String primary_contact_position) {
        this.primary_contact_position = primary_contact_position;
    }

    public String getPrimary_contact_phone() {
        return primary_contact_phone;
    }

    public void setPrimary_contact_phone(String primary_contact_phone) {
        this.primary_contact_phone = primary_contact_phone;
    }

    public String getPrimary_contact_email() {
        return primary_contact_email;
    }

    public void setPrimary_contact_email(String primary_contact_email) {
        this.primary_contact_email = primary_contact_email;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "company_code='" + company_code + '\'' +
                ", company_name='" + company_name + '\'' +
                ", company_abbreviation='" + company_abbreviation + '\'' +
                ", company_type='" + company_type + '\'' +
                ", registered_location='" + registered_location + '\'' +
                ", unified_social_credit='" + unified_social_credit + '\'' +
                ", registered_address='" + registered_address + '\'' +
                ", registered_phone='" + registered_phone + '\'' +
                ", company_email='" + company_email + '\'' +
                ", establishment_date=" + establishment_date +
                ", registered_capital=" + registered_capital +
                ", legal_representative_name='" + legal_representative_name + '\'' +
                ", legal_representative_phone='" + legal_representative_phone + '\'' +
                ", legal_representative_id='" + legal_representative_id + '\'' +
                ", industry='" + industry + '\'' +
                ", business_scope='" + business_scope + '\'' +
                ", is_verified_customer=" + is_verified_customer +
                ", is_szse_member=" + is_szse_member +
                ", szse_member_code='" + szse_member_code + '\'' +
                ", szse_member_abbreviation='" + szse_member_abbreviation + '\'' +
                ", customer_status='" + customer_status + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", business_license_number='" + business_license_number + '\'' +
                ", business_license_expiry=" + business_license_expiry +
                ", primary_contact_name='" + primary_contact_name + '\'' +
                ", primary_contact_position='" + primary_contact_position + '\'' +
                ", primary_contact_phone='" + primary_contact_phone + '\'' +
                ", primary_contact_email='" + primary_contact_email + '\'' +
                '}';
    }

    
    
}
