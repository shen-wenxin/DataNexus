package com.szsc.customermanagement.repository;

import com.szsc.customermanagement.entity.RegisteredLocation;
import com.szsc.customermanagement.exception.RegisteredLocationNotFoundException;

import java.util.List;

public interface RegisteredLocationRepository {
    List<RegisteredLocation> findAll();

    RegisteredLocation findByCompanyId(int companyId) throws RegisteredLocationNotFoundException;

    void save(RegisteredLocation registeredLocation);

    void update(RegisteredLocation registeredLocation) throws RegisteredLocationNotFoundException;

    void delete(RegisteredLocation registeredLocation) throws RegisteredLocationNotFoundException;
}