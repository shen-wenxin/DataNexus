package com.szsc.customermanagement.controller;

import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyDTO> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable int companyId) throws CompanyNotFoundException {
        CompanyDTO company = companyService.getCompanyById(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/type/{companyType}")
    public ResponseEntity<List<CompanyDTO>> getCompaniesByType(@PathVariable String companyType) {
        List<CompanyDTO> companies = companyService.getCompaniesByType(companyType);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> createCompany(@RequestBody CompanyDTO companyDTO) {
        Integer companyId = companyService.createCompany(companyDTO);
        return new ResponseEntity<>(companyId, HttpStatus.CREATED);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Void> updateCompany(@PathVariable int companyId, @RequestBody CompanyDTO companyDTO)
            throws CompanyNotFoundException {
        companyService.updateCompany(companyId, companyDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable int companyId) throws CompanyNotFoundException {
        companyService.deleteCompany(companyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}