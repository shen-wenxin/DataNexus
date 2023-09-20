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

            String companyOldStr = "";
            String remarks = "";
            HistoryRecord record = buildRecord(company_code, AppConfig.TYPE_OPERATION_CREATE,AppConfig.ADMIN_OPERATOR ,AppConfig.TABLE_COMPANY_MAIN, companyOldStr, company.toString(), remarks);
            historyRecordRepository.save(record);
        }

        LoggingUtils.logInfo("Company has been saved in database");
        return;
    }

    @Override
    public void updateCompany(CompanyDTO companyDTO) {
        LoggingUtils.logInfo("Begin to update Company.");

        Company company = MapperUtils.INSTANCE.dtoToEntity(companyDTO);

        String companyCode = company.getCompanyCode();

        Company companyOld = companyRepository.findByCode(companyCode);
        companyRepository.update(company);
        String companyOldStr = companyOld.toString();
        
        String companyNewStr = company.toString();
        String remarks = "";

        HistoryRecord record = buildRecord(companyCode, AppConfig.TYPE_OPERATION_UPDATE, AppConfig.ADMIN_OPERATOR, AppConfig.TABLE_COMPANY_MAIN, companyOldStr, companyNewStr, remarks);

        historyRecordRepository.save(record);

        LoggingUtils.logInfo("Company has been updated in database");
        
        return;
    }
   

    @Override
    public void deleteCompany(String companyCode) throws CompanyNotFoundException {
        Company existingCompany = companyRepository.findByCode(companyCode);

        
        if (existingCompany == null) {
            throw new CompanyNotFoundException("Company not found with ID: " + companyCode);
        }
        System.out.println("existingCompany" + existingCompany.toString());
        String CompanyOldStr = companyRepository.findByCode(companyCode).toString();
        String CompanyNewStr = "";
        String remarks = "";

        HistoryRecord record = buildRecord(companyCode, AppConfig.TYPE_OPERATION_DELETE, AppConfig.ADMIN_OPERATOR, AppConfig.TABLE_COMPANY_MAIN, CompanyOldStr,CompanyNewStr, remarks);


        companyRepository.delete(companyCode);
        historyRecordRepository.save(record);
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
        System.out.println("companies: " + companies);
        // 将分页结果转为CompanyDTO对象
        List<CompanyDTO> companyDTOList = companies
        .stream()
        .map(MapperUtils.INSTANCE::entityToDto)
        .collect(Collectors.toList());

        System.out.println("companyDTOList: " + companyDTOList);

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

            System.out.println("Get in exportCompanies\n\n===>");
            System.out.println(companies.toString());

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

    private HistoryRecord buildRecord(String companyCode, String OperationType, String Operator, String ModifiedField, String OldValue, String NewValue, String Remark) {
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setCompanyCode(companyCode);
        historyRecord.setOperationType(OperationType);
        historyRecord.setOperationTime(LocalDateTime.now());
        historyRecord.setOperator(Operator);
        historyRecord.setModifiedField(ModifiedField);
        historyRecord.setOldValue(OldValue);
        historyRecord.setNewValue(NewValue);
        historyRecord.setRemark(Remark);
        return historyRecord;
    }







    















}