package com.szsc.customermanagement.repository;

import com.szsc.customermanagement.entity.CompanyDetailedInformation;
import com.szsc.customermanagement.exception.CompanyDetailedInformationNotFoundException;

import java.util.List;

public interface CompanyDetailedInformationRepository {

    List<CompanyDetailedInformation> findAll();

    CompanyDetailedInformation findById(int companyId) throws CompanyDetailedInformationNotFoundException;

    void save(CompanyDetailedInformation detailedInformation);

    void update(CompanyDetailedInformation detailedInformation) throws CompanyDetailedInformationNotFoundException;

    void delete(int companyId) throws CompanyDetailedInformationNotFoundException;
}