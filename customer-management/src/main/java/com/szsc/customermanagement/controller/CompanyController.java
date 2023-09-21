package com.szsc.customermanagement.controller;

import com.szsc.customermanagement.config.AppConfig;
import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.dto.LocationCountDTO;
import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.OutputStream;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final TaskExecutor taskExecutor;

    @Autowired
    public CompanyController(CompanyService companyService, TaskExecutor taskExecutor) {
        this.companyService = companyService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping
    public ResponseEntity<Page<CompanyDTO>> getPagedCompanies(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CompanyDTO> companies = companyService.listPagedCompanies(pageable);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/search_registered_location")
    public ResponseEntity<Page<CompanyDTO>> getCompaniesByRegisteredLocation(
        @RequestParam(defaultValue = AppConfig.CODE_LOCATION_CHINA_MAINLAND) String registeredLocation,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CompanyDTO> companies = companyService.listCompaniesByRegisteredLocation(registeredLocation, pageable);
        return new ResponseEntity<>(companies, HttpStatus.OK);
        
    }

    @GetMapping("/count")
    public ResponseEntity<LocationCountDTO> getCompaniesLocationCount() {
        LocationCountDTO count = companyService.countCompanyLocation();
        return new ResponseEntity<>(count, HttpStatus.OK);
        
    }

    @GetMapping("/export")
    public void exportCompanies(HttpServletResponse response) {
        try {
            //easyExcel中的ExcelWriter类并没有实现AutoCloseable接口，因此无法直接在try-with-resources语句中使用
            List<Company> companies = companyService.exportCompanies();

            // 设置响应头信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = "company_export.xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            // 创建ExcelWriter对象
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), Company.class).build();

            // 创建WriteSheet对象，并设置相关信息
            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();

            // 写入数据到Excel
            excelWriter.write(companies, writeSheet);

            // 关闭ExcelWriter，释放资源
            excelWriter.finish();

        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{unifiedSocialCredit}")
    public ResponseEntity<List<CompanyDTO>> getCompanyByUnifiedSocialCredit(@PathVariable String unifiedSocialCredit) {
        List<CompanyDTO> companies;
        try {
            companies = companyService.getCompanyByUnifiedSocialCredit(unifiedSocialCredit);
        } catch (CompanyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Integer> insertCompany(@RequestBody CompanyDTO companyDTO) throws UnsupportedEncodingException {
        companyService.insertCompany(companyDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateCompany(@RequestBody CompanyDTO companyDTO) {
        try {
            companyService.updateCompany(companyDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CompanyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{companyCode}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String companyCode) {
        try {
            companyService.deleteCompany(companyCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CompanyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    



}