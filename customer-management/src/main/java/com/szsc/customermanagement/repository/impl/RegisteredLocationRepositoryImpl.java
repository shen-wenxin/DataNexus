package com.szsc.customermanagement.repository.impl;

import com.szsc.customermanagement.entity.RegisteredLocation;
import com.szsc.customermanagement.exception.RegisteredLocationNotFoundException;
import com.szsc.customermanagement.repository.RegisteredLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisteredLocationRepositoryImpl implements RegisteredLocationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Value("${table.registered_location}") 
    private String tableName;

    @Autowired
    public RegisteredLocationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RegisteredLocation> findAll() {
        String sql = "SELECT * FROM " + tableName;
        List<RegisteredLocation> registeredLocations = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(RegisteredLocation.class));
        return registeredLocations;
    }

    @Override
    public RegisteredLocation findByCompanyId(int companyId) throws RegisteredLocationNotFoundException {
        String sql = "SELECT * FROM " + tableName + " WHERE companyId = ?";
        List<RegisteredLocation> registeredLocations = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(RegisteredLocation.class), companyId);
        if (registeredLocations.isEmpty()) {
            throw new RegisteredLocationNotFoundException("Registered location not found with company ID: " + companyId);
        }
        return registeredLocations.get(0);
    }

    @Override
    public void save(RegisteredLocation registeredLocation) {
        String sql = "INSERT INTO " + tableName + " (companyId, location) VALUES (?, ?)";
        jdbcTemplate.update(sql, registeredLocation.getCompanyId(),
                registeredLocation.getLocation().getDisplayName());
    }

    @Override
    public void update(RegisteredLocation registeredLocation) throws RegisteredLocationNotFoundException {
        String sql = "UPDATE " + tableName + " SET location = ? WHERE companyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, registeredLocation.getLocation().getDisplayName(),
                registeredLocation.getCompanyId());
        if (rowsAffected == 0) {
            throw new RegisteredLocationNotFoundException("Registered location not found with company ID: "
                    + registeredLocation.getCompanyId());
        }
    }

    @Override
    public void delete(RegisteredLocation registeredLocation) throws RegisteredLocationNotFoundException {
        String sql = "DELETE FROM " + tableName + " WHERE companyId = ?";
        int rowsAffected = jdbcTemplate.update(sql, registeredLocation.getCompanyId());
        if (rowsAffected == 0) {
            throw new RegisteredLocationNotFoundException("Registered location not found with company ID: "
                    + registeredLocation.getCompanyId());
        }
    }
}