package com.szsc.customermanagement.service.impl;

import com.szsc.customermanagement.config.AppConfig;
import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.repository.CompanyRepository;
import com.szsc.customermanagement.service.CompanyService;
import com.szsc.customermanagement.utils.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
            .map(company -> ModelMapperUtils.convertToDTO(company, CompanyDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getCompanyById(int companyId) throws CompanyNotFoundException {
        Company company = companyRepository.findById(companyId);
        if (company == null) {
            throw new CompanyNotFoundException("Company not found with ID: " + companyId);
        }
        return ModelMapperUtils.convertToDTO(company, CompanyDTO.class);
    }

    @Override
    public List<CompanyDTO> getCompaniesByType(String companyType) {
        List<Company> companies = companyRepository.findByType(companyType);
    return companies.stream()
            .map(company -> ModelMapperUtils.convertToDTO(company, CompanyDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public int createCompany(CompanyDTO companyDTO) {

        int count = companyRepository.count() + 1;
        String countString = String.format("%07d", count);
        int CompanyId = Integer.parseInt(AppConfig.COMPANYID_PREFIX + countString);

        Company company = new Company();
        company.setCompanyId(CompanyId);
        company.setCompanyCode(companyDTO.getCompanyCode());
        company.setCompanyName(companyDTO.getCompanyName());
        company.setCompanyAbbreviation(companyDTO.getCompanyAbbreviation());
        company.setCompanyType(companyDTO.getCompanyType());

        companyRepository.save(company);

        return CompanyId;
    }

    @Override
    public void updateCompany(int companyId, CompanyDTO companyDTO) throws CompanyNotFoundException {
        Company existingCompany = companyRepository.findById(companyId);
        if (existingCompany == null) {
            throw new CompanyNotFoundException("Company not found with ID: " + companyId);
        }

        Company updatedCompany = ModelMapperUtils.convertToEntity(companyDTO, Company.class);
        updatedCompany.setCompanyId(companyId);
        companyRepository.update(updatedCompany);
    }

    @Override
    public void deleteCompany(int companyId) throws CompanyNotFoundException {
        Company existingCompany = companyRepository.findById(companyId);
        if (existingCompany == null) {
            throw new CompanyNotFoundException("Company not found with ID: " + companyId);
        }

        companyRepository.delete(companyId);
    }
}