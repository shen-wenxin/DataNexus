package com.szsc.customermanagement.repository.impl;

import com.szsc.customermanagement.entity.CompanyDetailedInformation;
import com.szsc.customermanagement.exception.CompanyDetailedInformationNotFoundException;
import com.szsc.customermanagement.repository.CompanyDetailedInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDetailedInformationRepositoryImpl implements CompanyDetailedInformationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Value("${table.company.detailed}") // 从配置文件中读取表名
    private String tableName;

    @Autowired
    public CompanyDetailedInformationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CompanyDetailedInformation> findAll() {
        String sql = "SELECT * FROM " + tableName;
        List<CompanyDetailedInformation> detailedInformations = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(CompanyDetailedInformation.class));
        return detailedInformations;
    }

    @Override
    public CompanyDetailedInformation findById(int companyId) throws CompanyDetailedInformationNotFoundException {
        String sql = "SELECT * FROM " + tableName + " WHERE companyId = ?";
        List<CompanyDetailedInformation> detailedInformations = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(CompanyDetailedInformation.class), companyId);
        if (detailedInformations.isEmpty()) {
            throw new CompanyDetailedInformationNotFoundException("Detailed information not found with ID: " + companyId);
        }
        return detailedInformations.get(0);
    }

    @Override
    public void save(CompanyDetailedInformation detailedInformation) {
        String sql = "INSERT INTO " + tableName +
                " (companyId, unifiedSocialCredit, registeredAddress, registeredPhone, companyEmail, establishmentDate, " +
                "registeredCapital, legalRepresentativeName, legalRepresentativePhone, legalRepresentativeId, " +
                "industry, businessScope, isVerifiedCustomer, customerStatus, country, province, city, " +
                "businessLicenseNumber, businessLicenseExpiry, primaryContactName, primaryContactPosition, " +
                "primaryContactPhone, primaryContactEmail) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, detailedInformation.getCompanyId(), detailedInformation.getUnifiedSocialCredit(),
                detailedInformation.getRegisteredAddress(), detailedInformation.getRegisteredPhone(),
                detailedInformation.getCompanyEmail(), detailedInformation.getEstablishmentDate(),
                detailedInformation.getRegisteredCapital(), detailedInformation.getLegalRepresentativeName(),
                detailedInformation.getLegalRepresentativePhone(), detailedInformation.getLegalRepresentativeId(),
                detailedInformation.getIndustry(), detailedInformation.getBusinessScope(),
                detailedInformation.isVerifiedCustomer(), detailedInformation.getCustomerStatus(),
                detailedInformation.getCountry(), detailedInformation.getProvince(), detailedInformation.getCity(),
                detailedInformation.getBusinessLicenseNumber(), detailedInformation.getBusinessLicenseExpiry(),
                detailedInformation.getPrimaryContactName(), detailedInformation.getPrimaryContactPosition(),
                detailedInformation.getPrimaryContactPhone(), detailedInformation.getPrimaryContactEmail());
    }

    @Override
    public void update(CompanyDetailedInformation detailedInformation)
            throws CompanyDetailedInformationNotFoundException {
        String sql = "UPDATE " + tableName + " SET unifiedSocialCredit = ?, registeredAddress = ?, " +
                "registeredPhone = ?, companyEmail = ?, establishmentDate = ?, registeredCapital = ?, " +
                "legalRepresentativeName = ?, legalRepresentativePhone = ?, legalRepresentativeId = ?, " +
                "industry = ?, businessScope = ?, isVerifiedCustomer = ?, customerStatus = ?, country = ?, " +
                "province = ?, city = ?, businessLicenseNumber = ?, businessLicenseExpiry = ?, " +
                "primaryContactName = ?, primaryContactPosition = ?, primaryContactPhone = ?, " +
                "primaryContactEmail = ? WHERE companyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, detailedInformation.getUnifiedSocialCredit(),
                detailedInformation.getRegisteredAddress(), detailedInformation.getRegisteredPhone(),
                detailedInformation.getCompanyEmail(), detailedInformation.getEstablishmentDate(),
                detailedInformation.getRegisteredCapital(), detailedInformation.getLegalRepresentativeName(),
                detailedInformation.getLegalRepresentativePhone(), detailedInformation.getLegalRepresentativeId(),
                detailedInformation.getIndustry(), detailedInformation.getBusinessScope(),
                detailedInformation.isVerifiedCustomer(), detailedInformation.getCustomerStatus(),
                detailedInformation.getCountry(), detailedInformation.getProvince(), detailedInformation.getCity(),
                detailedInformation.getBusinessLicenseNumber(), detailedInformation.getBusinessLicenseExpiry(),
                detailedInformation.getPrimaryContactName(), detailedInformation.getPrimaryContactPosition(),
                detailedInformation.getPrimaryContactPhone(), detailedInformation.getPrimaryContactEmail(),
                detailedInformation.getCompanyId());
        if (rowsAffected == 0) {
            throw new CompanyDetailedInformationNotFoundException("Detailed information not found with ID: "
                    + detailedInformation.getCompanyId());
        }
    }

    @Override
    public void delete(int companyId) throws CompanyDetailedInformationNotFoundException {
        String sql = "DELETE FROM " + tableName + " WHERE companyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, companyId);
        if (rowsAffected == 0) {
            throw new CompanyDetailedInformationNotFoundException("Detailed information not found with ID: " + companyId);
        }
    }
}