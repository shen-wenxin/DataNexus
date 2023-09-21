package com.szsc.customermanagement.service.impl;

import com.szsc.customermanagement.config.AppConfig;
import com.szsc.customermanagement.utils.LoggingUtils;
import com.szsc.customermanagement.utils.MapperUtils;
import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.dto.LocationCountDTO;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.entity.HistoryRecord;
import com.szsc.customermanagement.exception.CompanyAlreadyExistsException;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.repository.CompanyRepository;
import com.szsc.customermanagement.repository.HistoryRecordRepository;
import com.szsc.customermanagement.service.CompanyService;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
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
    public void createCompany(CompanyDTO companyDTO) {
        LoggingUtils.logInfo("Begin to create Company.");
        Company company = MapperUtils.INSTANCE.dtoToEntity(companyDTO);
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
    public void updateCompany(CompanyDTO companyDTO) throws CompanyNotFoundException{
        LoggingUtils.logInfo("Begin to update Company");

        Company company = MapperUtils.INSTANCE.dtoToEntity(companyDTO);

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
    public Page<CompanyDTO> listPagedCompanies(Pageable pageable) {

        // 执行分页查询操作，获取分页结果
        Page<Company> companyPage = companyRepository.listAllCompanies(pageable);

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

    @Override
    public Page<CompanyDTO> listCompaniesByRegisteredLocation(String registeredLocation, Pageable pageable) {
        Page<Company> companyPage = companyRepository.listCompaniesByRegisteredLocation(pageable, registeredLocation);

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







    @Override
    public List<CompanyDTO> getCompanyByUnifiedSocialCredit(String unifiedSocialCredit) {
        List<Company> companies = companyRepository.listCompaniesByUnifiedSocialCredit(unifiedSocialCredit);
        // 将分页结果转为CompanyDTO对象
        List<CompanyDTO> companyDTOList = companies
        .stream()
        .map(MapperUtils.INSTANCE::entityToDto)
        .collect(Collectors.toList());

        LoggingUtils.logInfo(companyDTOList.toString());

        return companyDTOList;
    }

    @Override
    public LocationCountDTO getCompanyLocationCount() {

        int mainland = companyRepository.CompanyCount(AppConfig.CODE_LOCATION_CHINA_MAINLAND);
        int hongkong = companyRepository.CompanyCount(AppConfig.CODE_LOCATION_HONG_KONG);
        int overseas = companyRepository.CompanyCount(AppConfig.CODE_LOCATION_OVERSEAS);

        int total = mainland + hongkong + overseas;

        LocationCountDTO count = new LocationCountDTO();
        count.setMainland(mainland);
        count.setHongkong(hongkong);
        count.setOverseas(overseas);
        count.setTotal(total);

        return count;
    }

    

    @Override
    public byte[] exportCompanies() throws IOException {
        int pageSize = 100; // 每页记录数
        int page = 0; // 当前页数

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Workbook workbook = new XSSFWorkbook();

        boolean hasMoreData = true;
        while (hasMoreData) {
            PageRequest pageable = PageRequest.of(page, pageSize);
            Page<Company> companiesPage = companyRepository.listAllCompanies(pageable);
            List<Company> companies = companiesPage.getContent();

            if (companies.isEmpty()) {
                break; // 没有更多数据，退出循环
            }

            Sheet sheet = workbook.createSheet("Companies - Page " + (page + 1));

            // 创建表头
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                "Company ID",
                "Company Code",
                "Company Name",
                "Company Abbreviation",
                "Company Type",
                "Registered Location",
                "Unified Social Credit",
                "Registered Address",
                "Registered Phone",
                "Company Email",
                "Establishment Date",
                "Registered Capital",
                "Legal Representative Name",
                "Legal Representative Phone",
                "Legal Representative ID",
                "Industry",
                "Business Scope",
                "Verified Customer",
                "SZSE Member",
                "SZSE Member Code",
                "SZSE Member Abbreviation",
                "Customer Status",
                "Country",
                "Province",
                "City",
                "Business License Number",
                "Business License Expiry",
                "Primary Contact Name",
                "Primary Contact Position",
                "Primary Contact Phone",
                "Primary Contact Email"
            };
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // 填充数据行
            int rowNum = 1;
            int idx = 1;
            for (Company company : companies) {
                Row dataRow = sheet.createRow(rowNum++);

                dataRow.createCell(0).setCellValue(idx ++);
                dataRow.createCell(1).setCellValue(company.getCompanyCode());
                dataRow.createCell(2).setCellValue(company.getCompanyName());
                dataRow.createCell(3).setCellValue(company.getCompanyAbbreviation());
                dataRow.createCell(4).setCellValue(company.getCompanyType());
                dataRow.createCell(5).setCellValue(company.getRegisteredLocation());
                dataRow.createCell(6).setCellValue(company.getUnifiedSocialCredit());
                dataRow.createCell(7).setCellValue(company.getRegisteredAddress());
                dataRow.createCell(8).setCellValue(company.getRegisteredPhone());
                dataRow.createCell(9).setCellValue(company.getCompanyEmail());
                dataRow.createCell(10).setCellValue(company.getEstablishmentDate());
                dataRow.createCell(11).setCellValue(company.getRegisteredCapital().doubleValue());
                dataRow.createCell(12).setCellValue(company.getLegalRepresentativeName());
                dataRow.createCell(13).setCellValue(company.getLegalRepresentativePhone());
                dataRow.createCell(14).setCellValue(company.getLegalRepresentativeId());
                dataRow.createCell(15).setCellValue(company.getIndustry());
                dataRow.createCell(16).setCellValue(company.getBusinessScope());
                dataRow.createCell(17).setCellValue(company.getVerifiedCustomer());
                dataRow.createCell(18).setCellValue(company.getSzseMember());
                dataRow.createCell(19).setCellValue(company.getSzseMemberCode());
                dataRow.createCell(20).setCellValue(company.getSzseMemberAbbreviation());
                dataRow.createCell(21).setCellValue(company.getCustomerStatus());
                dataRow.createCell(22).setCellValue(company.getCountry());
                dataRow.createCell(23).setCellValue(company.getProvince());
                dataRow.createCell(24).setCellValue(company.getCity());
                dataRow.createCell(25).setCellValue(company.getBusinessLicenseNumber());
                dataRow.createCell(26).setCellValue(company.getBusinessLicenseExpiry());
                dataRow.createCell(27).setCellValue(company.getPrimaryContactName());
                dataRow.createCell(28).setCellValue(company.getPrimaryContactPosition());
                dataRow.createCell(29).setCellValue(company.getPrimaryContactPhone());
                dataRow.createCell(30).setCellValue(company.getPrimaryContactEmail());
            }

            page++;

            // 检查是否有更多页
            hasMoreData = companiesPage.hasNext();
        }

        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }







    















}