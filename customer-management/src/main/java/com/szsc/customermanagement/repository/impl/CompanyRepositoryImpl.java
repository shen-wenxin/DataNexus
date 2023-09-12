package com.szsc.customermanagement.repository.impl;

import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.repository.CompanyRepository;
import com.szsc.customermanagement.utils.LoggingUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.szsc.customermanagement.config.AppConfig;

import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    private final JdbcTemplate jdbcTemplate;

     @Value("${table.company.main}") // 从配置文件中读取表名
    private String tableName;

    @Autowired
    public CompanyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        String countSql = "SELECT COUNT(*) FROM " + tableName;
        int totalElements = jdbcTemplate.queryForObject(countSql, Integer.class);
        String selectSql = "SELECT * FROM " + tableName + " LIMIT ? OFFSET ?";
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int offset = pageNumber * pageSize;
        List<Company> companies = jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Company.class), pageSize, offset);
        return new PageImpl<>(companies, pageable, totalElements);
    }

    @Override
    public Company findById(int companyId) throws CompanyNotFoundException {
        String sql = "SELECT * FROM " + tableName + " WHERE companyId = ?";
        List<Company> companies = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Company.class), companyId);
        if (companies.isEmpty()) {
            throw new CompanyNotFoundException("Company not found with ID: " + companyId);
        }
        return companies.get(0);
    }

    @Override
    public boolean companyCodeChecker(String company_code) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE company_code = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, company_code);
        return count > 0;
    }

    @Override
    public boolean unifiedSocialCreditChecker(String unified_social_credit) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE unified_social_credit = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, unified_social_credit);
        return count > 0;
    }

    @Override
    public List<Company> findByType(String companyType) {
        String sql = "SELECT * FROM " + tableName + " WHERE company_type = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Company.class), companyType);
    }

    @Override
    public void save(Company company) {
        int locationValue = convertRegisteredLocation(company.getRegisteredLocation());
        String sql = "INSERT INTO " + tableName + " (company_code, company_name, company_abbreviation, " +
                "company_type, registered_location, unified_social_credit, registered_address, registered_phone, " +
                "company_email, establishment_date, registered_capital, legal_representative_name, " +
                "legal_representative_phone, legal_representative_id, industry, business_scope, verified_customer, " +
                "szse_member, szse_member_code, szse_member_abbreviation, customer_status, country, province, city, " +
                "business_license_number, business_license_expiry, primary_contact_name, primary_contact_position, " +
                "primary_contact_phone, primary_contact_email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?)";
        jdbcTemplate.update(sql, company.getCompanyCode(), company.getCompanyName(),
                company.getCompanyAbbreviation(), company.getCompanyType(), convertRegisteredLocation(company.getRegisteredLocation()),
                company.getUnifiedSocialCredit(), company.getRegisteredAddress(), company.getRegisteredPhone(),
                company.getCompanyEmail(), company.getEstablishmentDate(), company.getRegisteredCapital(),
                company.getLegalRepresentativeName(), company.getLegalRepresentativePhone(),
                company.getLegalRepresentativeId(), company.getIndustry(), company.getBusinessScope(),
                company.getVerifiedCustomer(), company.getSzseMember(), company.getSzseMemberCode(),
                company.getSzseMemberAbbreviation(), company.getCustomerStatus(), company.getCountry(),
                company.getProvince(), company.getCity(), company.getBusinessLicenseNumber(),
                company.getBusinessLicenseExpiry(), company.getPrimaryContactName(),
                company.getPrimaryContactPosition(), company.getPrimaryContactPhone(),
                company.getPrimaryContactEmail());
    }

    @Override
    public void update(Company company) throws CompanyNotFoundException {
        String sql = "UPDATE " + tableName + " SET company_code = ?, company_name = ?, company_abbreviation = ?, " +
                "company_type = ?, registered_location = ?, unified_social_credit = ?, registered_address = ?, " +
                "registered_phone = ?, company_email = ?, establishment_date = ?, registered_capital = ?, " +
                "legal_representative_name = ?, legal_representative_phone = ?, legal_representative_id = ?, " +
                "industry = ?, business_scope = ?, verified_customer = ?, szse_member = ?, szse_member_code = ?, " +
                "szse_member_abbreviation = ?, customer_status = ?, country = ?, province = ?, city = ?, " +
                "business_license_number = ?, business_license_expiry = ?, primary_contact_name = ?, " +
                "primary_contact_position = ?, primary_contact_phone = ?, primary_contact_email = ? " +
                "WHERE company_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, company.getCompanyCode(), company.getCompanyName(),
                company.getCompanyAbbreviation(), company.getCompanyType(), convertRegisteredLocation(company.getRegisteredLocation()),
                company.getUnifiedSocialCredit(), company.getRegisteredAddress(), company.getRegisteredPhone(),
                company.getCompanyEmail(), company.getEstablishmentDate(), company.getRegisteredCapital(),
                company.getLegalRepresentativeName(), company.getLegalRepresentativePhone(),
                company.getLegalRepresentativeId(), company.getIndustry(), company.getBusinessScope(),
                company.getVerifiedCustomer(), company.getSzseMember(), company.getSzseMemberCode(),
                company.getSzseMemberAbbreviation(), company.getCustomerStatus(), company.getCountry(),
                company.getProvince(), company.getCity(), company.getBusinessLicenseNumber(),
                company.getBusinessLicenseExpiry(), company.getPrimaryContactName(),
                company.getPrimaryContactPosition(), company.getPrimaryContactPhone(),
                company.getPrimaryContactEmail(), company.getCompanyId());
        if (rowsAffected == 0) {
            throw new CompanyNotFoundException("Company not found with ID: " + company.getCompanyId());
        }
    }

    @Override
    public void delete(int companyId) throws CompanyNotFoundException {
        String sql = "DELETE FROM " + tableName + " WHERE company_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, companyId);
        if (rowsAffected == 0) {
            throw new CompanyNotFoundException("Company not found with ID: " + companyId);
        }
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private int convertRegisteredLocation(String registeredLocation) {
        // 大坑 mysql enum的索引从1开始 离谱哈
        switch (registeredLocation) {
            case AppConfig.CODE_LOCATION_CHINA_MAINLAND :
                return 1;
            case AppConfig.CODE_LOCATION_HONG_KONG :
                return 2;
            case AppConfig.CODE_LOCATION_OVERSEAS:
                return 3;
            default:
                throw new IllegalArgumentException("Invalid registered location: " + registeredLocation);
        }
    }

    

}