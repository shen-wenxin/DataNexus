package com.szsc.customermanagement.service.impl;

import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.repository.CompanyRepository;
import com.szsc.customermanagement.service.CompanyService;
import com.szsc.customermanagement.utils.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void createCompany(CompanyDTO companyDTO) {
        Company company = ModelMapperUtils.convertToEntity(companyDTO, Company.class);
        companyRepository.save(company);
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