package com.szsc.customermanagement.service;

import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.dto.LocationCountDTO;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface CompanyService {

    // CompanyDTO getCompanyById(int companyId) throws CompanyNotFoundException;

    // List<CompanyDTO> getCompaniesByType(String companyType);

    void createCompany(CompanyDTO companyDTO);

    // void updateCompany(int companyId, CompanyDTO companyDTO) throws CompanyNotFoundException;

    void deleteCompany(String companyCode) throws CompanyNotFoundException;

    Page<CompanyDTO> getPagedCompanies(Pageable pageable);

    Page<CompanyDTO> getCompaniesByRegisteredLocation(String registeredLocation, Pageable pageable);

    void updateCompany(CompanyDTO companyDTO);

    List<CompanyDTO> getCompanyByUnifiedSocialCredit(String unifiedSocialCredit);

    byte[] exportCompanies() throws IOException;
    LocationCountDTO getCompanyLocationCount();

    


}
