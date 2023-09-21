package com.szsc.customermanagement.service;

import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.dto.LocationCountDTO;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface CompanyService {

    // CompanyDTO getCompanyById(int companyId) throws CompanyNotFoundException;

    // List<CompanyDTO> getCompaniesByType(String companyType);

    void insertCompany(CompanyDTO companyDTO);

    // void updateCompany(int companyId, CompanyDTO companyDTO) throws CompanyNotFoundException;

    void deleteCompany(String companyCode) throws CompanyNotFoundException;

    Page<CompanyDTO> listPagedCompanies(Pageable pageable);

    Page<CompanyDTO> listCompaniesByRegisteredLocation(String registeredLocation, Pageable pageable);

    void updateCompany(CompanyDTO companyDTO) throws CompanyNotFoundException;

    List<CompanyDTO> getCompanyByUnifiedSocialCredit(String unifiedSocialCredit);

    List<Company> exportCompanies() throws IOException;
    LocationCountDTO countCompanyLocation();

    


}
