package com.szsc.customermanagement.service.impl;

import com.szsc.customermanagement.config.AppConfig;
import com.szsc.customermanagement.domain.CompanyData;
import com.szsc.customermanagement.utils.LoggingUtils;
import com.szsc.customermanagement.utils.MapperUtils;
import com.szsc.customermanagement.domain.LocationCountData;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.entity.HistoryRecord;
import com.szsc.customermanagement.exception.CompanyAlreadyExistsException;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.repository.CompanyRepository;
import com.szsc.customermanagement.repository.HistoryRecordRepository;
import com.szsc.customermanagement.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final HistoryRecordRepository historyRecordRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, HistoryRecordRepository historyRecordRepository) {
        this.companyRepository = companyRepository;
        this.historyRecordRepository = historyRecordRepository;
    }

    @Override
    public void insertCompany(CompanyData companyData) {
        LoggingUtils.logInfo("Begin to create Company.");
        Company company = MapperUtils.INSTANCE.dtoToEntity(companyData);
        LoggingUtils.logInfo("company: " + company.toString());

        String companyCode = company.getCompanyCode();

        // check the database if the company code is exist.
        if(companyRepository.companyCodeChecker(companyCode) ||
            companyRepository.unifiedSocialCreditChecker(companyCode)){
            // exist
            LoggingUtils.logError("Company with code " + companyCode + " already exists.", null);
            throw new CompanyAlreadyExistsException("Company with code " + companyCode + " already exists.");

        }else {
            // not exist
            companyRepository.save(company);

            String companyOldStr = "";
            String remarks = "";
            HistoryRecord record = HistoryRecord.builder()
                    .companyCode(companyCode)
                    .operationType(AppConfig.TYPE_OPERATION_CREATE)
                    .operator(AppConfig.ADMIN_OPERATOR)
                    .modifiedField(AppConfig.TABLE_COMPANY_MAIN)
                    .oldValue("")
                    .newValue(company.toString())
                    .remark("").build();
        }

        LoggingUtils.logInfo("Company has been saved in database");
    }

    @Override
    public void updateCompany(CompanyData companyData) throws CompanyNotFoundException{
        LoggingUtils.logInfo("Begin to update Company");

        Company company = MapperUtils.INSTANCE.dtoToEntity(companyData);

        String companyCode = company.getCompanyCode();

        try {
            Company companyOld = companyRepository.findByCode(companyCode);
            companyRepository.update(company);
            String companyOldStr = companyOld.toString();

            String companyNewStr = company.toString();

            HistoryRecord record = HistoryRecord.builder()
                    .companyCode(companyCode)
                    .operationType(AppConfig.TYPE_OPERATION_UPDATE)
                    .operator(AppConfig.ADMIN_OPERATOR)
                    .modifiedField(AppConfig.TABLE_COMPANY_MAIN)
                    .oldValue(companyOldStr)
                    .newValue(companyNewStr)
                    .remark("")
                    .build();
            historyRecordRepository.save(record);

            LoggingUtils.logInfo("Company has been updated in the database");
        } catch (CompanyNotFoundException e) {
            // 处理CompanyNotFoundException异常
            String errorMessage = "Company not found with ID: " + companyCode;
            LoggingUtils.logError(errorMessage, null);
            // 返回错误信息给调用方
            throw new CompanyNotFoundException(errorMessage);
        }
    }
   

    @Override
    public void deleteCompany(String companyCode) throws CompanyNotFoundException {
        Company existingCompany = companyRepository.findByCode(companyCode);

        
        if (existingCompany == null) {
            throw new CompanyNotFoundException("Company not found with ID: " + companyCode);
        }
        try{
            String CompanyOldStr = companyRepository.findByCode(companyCode).toString();

            HistoryRecord record = HistoryRecord.builder()
                    .companyCode(companyCode)
                    .operationType(AppConfig.TYPE_OPERATION_DELETE)
                    .operator(AppConfig.ADMIN_OPERATOR)
                    .modifiedField(AppConfig.TABLE_COMPANY_MAIN)
                    .oldValue(CompanyOldStr)
                    .newValue("")
                    .remark("")
                    .build();


            companyRepository.delete(companyCode);
            historyRecordRepository.save(record);

        } catch (CompanyNotFoundException e) {
            // 处理CompanyNotFoundException异常
            String errorMessage = "Company not found with ID: " + companyCode;
            LoggingUtils.logError(errorMessage, null);
            // 返回错误信息给调用方
            throw new CompanyNotFoundException(errorMessage);
        }


    }







    @Override
    public Page<CompanyData> listPagedCompanies(Pageable pageable) {

        // 执行分页查询操作，获取分页结果
        Page<Company> companyPage = companyRepository.listAllCompanies(pageable);

        LoggingUtils.logInfo(companyPage.toString());
        
        // 将分页结果转为CompanyDTO对象
        List<CompanyData> companyDataList = companyPage.getContent()
            .stream()
            .map(MapperUtils.INSTANCE::entityToDto)
            .collect(Collectors.toList());

        LoggingUtils.logInfo(companyDataList.toString());

        // 创建一个新的Page对象，将CompanyDTO列表和分页信息传入
        return new PageImpl<>(companyDataList, pageable, companyPage.getTotalElements());

    }

    @Override
    public Page<CompanyData> listCompaniesByRegisteredLocation(String registeredLocation, Pageable pageable) {
        Page<Company> companyPage = companyRepository.listCompaniesByRegisteredLocation(pageable, registeredLocation);

        LoggingUtils.logInfo(companyPage.toString());

        // 将分页结果转为CompanyDTO对象
        List<CompanyData> companyDataList = companyPage.getContent()
        .stream()
        .map(MapperUtils.INSTANCE::entityToDto)
        .collect(Collectors.toList());

        LoggingUtils.logInfo(companyDataList.toString());

        // 创建一个新的Page对象，将CompanyDTO列表和分页信息传入
        return new PageImpl<>(companyDataList, pageable, companyPage.getTotalElements());
    }







    @Override
    public List<CompanyData> getCompanyByUnifiedSocialCredit(String unifiedSocialCredit) {
        List<Company> companies = companyRepository.listCompaniesByUnifiedSocialCredit(unifiedSocialCredit);
        // 将分页结果转为CompanyDTO对象
        List<CompanyData> companyDataList = companies
            .stream()
            .map(MapperUtils.INSTANCE::entityToDto)
            .collect(Collectors.toList());

        LoggingUtils.logInfo(companyDataList.toString());

        return companyDataList;
    }

    @Override
    public LocationCountData countCompanyLocation() {

        int mainland = companyRepository.CompanyCount(AppConfig.CODE_LOCATION_CHINA_MAINLAND);
        int hongkong = companyRepository.CompanyCount(AppConfig.CODE_LOCATION_HONG_KONG);
        int overseas = companyRepository.CompanyCount(AppConfig.CODE_LOCATION_OVERSEAS);

        int total = mainland + hongkong + overseas;

        LocationCountData count = new LocationCountData();
        count.setMainland(mainland);
        count.setHongkong(hongkong);
        count.setOverseas(overseas);
        count.setTotal(total);

        return count;
    }

    

    @Override
    public List<Company> exportCompanies() throws IOException {
        int pageSize = 100; // 每页记录数
        int page = 0; // 当前页数
        List<Company> allCompanies = new ArrayList<>();

        boolean hasMoreData = true;
        while (hasMoreData) {
            PageRequest pageable = PageRequest.of(page, pageSize);
            Page<Company> companiesPage = companyRepository.listAllCompanies(pageable);
            List<Company> companies = companiesPage.getContent();

            if (companies.isEmpty()) {
                break; // 没有更多数据，退出循环
            }
            allCompanies.addAll(allCompanies);

            page++;

            // 检查是否有更多页
            hasMoreData = companiesPage.hasNext();
        }
        return allCompanies;


    }







    















}