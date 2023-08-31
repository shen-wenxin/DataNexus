package com.szsc.customermanagement.repository.impl;

import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public List<Company> findAll() {
        String sql = "SELECT * FROM " + tableName;
        List<Company> companies = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Company.class));
        return companies;
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
    public List<Company> findByType(String companyType) {
        String sql = "SELECT * FROM " + tableName + " WHERE companyType = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Company.class), companyType);
    }

    @Override
    public void save(Company company) {
        String sql = "INSERT INTO " + tableName + " (company_id, company_code,company_name, company_abbreviation, company_type) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, company.getCompanyId(), company.getCompanyCode(), company.getCompanyName(),
                company.getCompanyAbbreviation(), company.getCompanyType());
    }

    @Override
    public void update(Company company) throws CompanyNotFoundException {
        String sql = "UPDATE " + tableName + " SET companyCode = ?, companyName = ?, companyAbbreviation = ?, " +
                "companyType = ? WHERE companyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, company.getCompanyCode(), company.getCompanyName(),
                company.getCompanyAbbreviation(), company.getCompanyType(), company.getCompanyId());
        if (rowsAffected == 0) {
            throw new CompanyNotFoundException("Company not found with ID: " + company.getCompanyId());
        }
    }

    @Override
    public void delete(int companyId) throws CompanyNotFoundException {
        String sql = "DELETE FROM " + tableName + " WHERE companyId = ?";
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
}