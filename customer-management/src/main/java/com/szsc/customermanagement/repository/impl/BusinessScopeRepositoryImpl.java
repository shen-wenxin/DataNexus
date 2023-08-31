package com.szsc.customermanagement.repository.impl;

import com.szsc.customermanagement.entity.BusinessScope;
import com.szsc.customermanagement.exception.BusinessScopeNotFoundException;
import com.szsc.customermanagement.repository.BusinessScopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessScopeRepositoryImpl implements BusinessScopeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Value("${table.business_scope}") // 从配置文件中读取表名
    private String tableName;

    @Autowired
    public BusinessScopeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BusinessScope> findAll() {
        String sql = "SELECT * FROM " + tableName;
        List<BusinessScope> businessScopes = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(BusinessScope.class));
        return businessScopes;
    }

    @Override
    public BusinessScope findByCompanyId(int companyId) throws BusinessScopeNotFoundException {
        String sql = "SELECT * FROM " + tableName + " WHERE companyId = ?";
        List<BusinessScope> businessScopes = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(BusinessScope.class), companyId);
        if (businessScopes.isEmpty()) {
            throw new BusinessScopeNotFoundException("Business scope not found with company ID: " + companyId);
        }
        return businessScopes.get(0);
    }

    @Override
    public void save(BusinessScope businessScope) {
        String sql = "INSERT INTO " + tableName + " (companyId, scope) VALUES (?, ?)";
        jdbcTemplate.update(sql, businessScope.getCompanyId(), businessScope.getScope());
    }

    @Override
    public void update(BusinessScope businessScope) throws BusinessScopeNotFoundException {
        String sql = "UPDATE " + tableName + " SET scope = ? WHERE companyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, businessScope.getScope(), businessScope.getCompanyId());
        if (rowsAffected == 0) {
            throw new BusinessScopeNotFoundException("Business scope not found with company ID: "
                    + businessScope.getCompanyId());
        }
    }

    @Override
    public void delete(BusinessScope businessScope) throws BusinessScopeNotFoundException {
        String sql = "DELETE FROM " + tableName + " WHERE companyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, businessScope.getCompanyId());
        if (rowsAffected == 0) {
            throw new BusinessScopeNotFoundException("Business scope not found with company ID: "
                    + businessScope.getCompanyId());
        }
    }
}