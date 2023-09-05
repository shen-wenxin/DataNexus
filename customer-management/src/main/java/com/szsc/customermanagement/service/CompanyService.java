package com.szsc.customermanagement.service;

import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    List<CompanyDTO> getAllCompanies();

    CompanyDTO getCompanyById(int companyId) throws CompanyNotFoundException;

    List<CompanyDTO> getCompaniesByType(String companyType);

    int createCompany(CompanyDTO companyDTO);

    void updateCompany(int companyId, CompanyDTO companyDTO) throws CompanyNotFoundException;

    void deleteCompany(int companyId) throws CompanyNotFoundException;
}
