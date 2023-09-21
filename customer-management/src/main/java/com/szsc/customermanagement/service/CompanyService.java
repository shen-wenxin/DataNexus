package com.szsc.customermanagement.service;

import com.szsc.customermanagement.domain.CompanyData;
import com.szsc.customermanagement.domain.LocationCountData;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface CompanyService {

    // CompanyData getCompanyById(int companyId) throws CompanyNotFoundException;

    // List<CompanyData> getCompaniesByType(String companyType);

    void insertCompany(CompanyData companyData);

    // void updateCompany(int companyId, CompanyData companyDTO) throws CompanyNotFoundException;

    void deleteCompany(String companyCode) throws CompanyNotFoundException;

    Page<CompanyData> listPagedCompanies(Pageable pageable);

    Page<CompanyData> listCompaniesByRegisteredLocation(String registeredLocation, Pageable pageable);

    void updateCompany(CompanyData companyData) throws CompanyNotFoundException;

    List<CompanyData> getCompanyByUnifiedSocialCredit(String unifiedSocialCredit);

    List<Company> exportCompanies() throws IOException;
    LocationCountData countCompanyLocation();

    


}
