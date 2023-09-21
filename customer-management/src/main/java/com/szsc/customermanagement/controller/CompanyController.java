package com.szsc.customermanagement.controller;

import com.szsc.customermanagement.config.AppConfig;
import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.dto.LocationCountDTO;
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
        LocationCountDTO count = companyService.getCompanyLocationCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
        
    }

    @GetMapping("/export")
    public void exportCompanies(HttpServletResponse response) {
        try (OutputStream outputStream = response.getOutputStream()) {
            byte[] excelData = companyService.exportCompanies();

            // Set response headers
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=companies.xlsx");

            // Write excel data to response
            outputStream.write(excelData);
            outputStream.flush();
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
    } 

    @GetMapping("/{unifiedSocialCredit}")
    public ResponseEntity<List<CompanyDTO>> getCompanyByUnifiedSocialCredit(@PathVariable String unifiedSocialCredit) {
        try {
            System.out.println("unifiedSocialCredit: " + unifiedSocialCredit);
            List<CompanyDTO> companies = companyService.getCompanyByUnifiedSocialCredit(unifiedSocialCredit);
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<Integer> createCompany(@RequestBody CompanyDTO companyDTO) throws UnsupportedEncodingException {
        companyService.createCompany(companyDTO);
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