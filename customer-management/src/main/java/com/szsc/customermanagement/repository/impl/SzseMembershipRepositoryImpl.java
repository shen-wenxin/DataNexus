package com.szsc.customermanagement.repository.impl;

import com.szsc.customermanagement.entity.SzseMembership;
import com.szsc.customermanagement.exception.SzseMembershipNotFoundException;
import com.szsc.customermanagement.repository.SzseMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SzseMembershipRepositoryImpl implements SzseMembershipRepository {

    private final JdbcTemplate jdbcTemplate;

    @Value("${table.szse_membership}") // 从配置文件中读取表名
    private String tableName;

    @Autowired
    public SzseMembershipRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SzseMembership> findAll() {
        String sql = "SELECT * FROM " + tableName;
        List<SzseMembership> szseMemberships = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(SzseMembership.class));
        return szseMemberships;
    }

    @Override
    public SzseMembership findByCompanyId(int companyId) throws SzseMembershipNotFoundException {
        String sql = "SELECT * FROM " + tableName + " WHERE companyId = ?";
        List<SzseMembership> szseMemberships = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(SzseMembership.class), companyId);
        if (szseMemberships.isEmpty()) {
            throw new SzseMembershipNotFoundException("SzseMembership not found with company ID: " + companyId);
        }
        return szseMemberships.get(0);
    }

    @Override
    public void save(SzseMembership szseMembership) {
        String sql = "INSERT INTO " + tableName + " (szseMemberId, companyId, isSzseMember, szseMemberCode, szseMemberAbbreviation) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, szseMembership.getSzseMemberId(), szseMembership.getCompanyId(),
                szseMembership.isSzseMember(), szseMembership.getSzseMemberCode(),
                szseMembership.getSzseMemberAbbreviation());
    }

    @Override
    public void update(SzseMembership szseMembership) throws SzseMembershipNotFoundException {
        String sql = "UPDATE " + tableName + " SET isSzseMember = ?, szseMemberCode = ?, szseMemberAbbreviation = ? " +
                "WHERE companyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, szseMembership.isSzseMember(),
                szseMembership.getSzseMemberCode(), szseMembership.getSzseMemberAbbreviation(),
                szseMembership.getCompanyId());
        if (rowsAffected == 0) {
            throw new SzseMembershipNotFoundException("SzseMembership not found with company ID: "
                    + szseMembership.getCompanyId());
        }
    }

    @Override
    public void delete(SzseMembership szseMembership) throws SzseMembershipNotFoundException {
        String sql = "DELETE FROM " + tableName + " WHERE companyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, szseMembership.getCompanyId());
        if (rowsAffected == 0) {
            throw new SzseMembershipNotFoundException("SzseMembership not found with company ID: "
                    + szseMembership.getCompanyId());
        }
    }
}