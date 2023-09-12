package com.szsc.customermanagement.service.impl;

import com.szsc.customermanagement.config.AppConfig;
import com.szsc.customermanagement.utils.LoggingUtils;
import com.szsc.customermanagement.utils.MapperUtils;
import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyAlreadyExistsException;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.repository.CompanyRepository;
import com.szsc.customermanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public void createCompany(CompanyDTO companyDTO) {
        LoggingUtils.logInfo("Begin to create Company.");
        Company company = MapperUtils.INSTANCE.dtoToEntity(companyDTO);
        LoggingUtils.logInfo("company: " + company.toString());

        String company_code = company.getCompanyCode();

        // check the database if the company code is exist.
        if(companyRepository.companyCodeChecker(company_code) ||
            companyRepository.unifiedSocialCreditChecker(company_code)){
            // exist
            LoggingUtils.logError("Company with code " + company_code + " already exists.", null);
            throw new CompanyAlreadyExistsException("Company with code " + company_code + " already exists.");

        }else {
            // not exist
            companyRepository.save(company);
        }
        LoggingUtils.logInfo("Company has been saved in database");
        return;
    }
   

    @Override
    public void deleteCompany(int companyId) throws CompanyNotFoundException {
        Company existingCompany = companyRepository.findById(companyId);
        if (existingCompany == null) {
            throw new CompanyNotFoundException("Company not found with ID: " + companyId);
        }

        companyRepository.delete(companyId);
    }







    @Override
    public Page<CompanyDTO> getPagedCompanies(Pageable pageable) {

        // 执行分页查询操作，获取分页结果
        Page<Company> companyPage = companyRepository.findAll(pageable);

        LoggingUtils.logInfo(companyPage.toString());
        
        // 将分页结果转为CompanyDTO对象
        List<CompanyDTO> companyDTOList = companyPage.getContent()
        .stream()
        .map(MapperUtils.INSTANCE::entityToDto)
        .collect(Collectors.toList());

        LoggingUtils.logInfo(companyDTOList.toString());

        // 创建一个新的Page对象，将CompanyDTO列表和分页信息传入
        return new PageImpl<>(companyDTOList, pageable, companyPage.getTotalElements());

    }








}