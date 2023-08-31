package com.szsc.customermanagement.repository;

import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;

import java.util.List;

public interface CompanyRepository {
    List<Company> findAll();

    Company findById(int companyId) throws CompanyNotFoundException;

    List<Company> findByType(String companyType);

    void save(Company company);

    void update(Company company) throws CompanyNotFoundException;

    void delete(int companyId) throws CompanyNotFoundException;

    int count();// count how many company

}
