package com.szsc.customermanagement.controller;

import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import com.szsc.customermanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<Page<CompanyDTO>> getPagedCompanies(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CompanyDTO> companies = companyService.getPagedCompanies(pageable);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> createCompany(@RequestBody CompanyDTO companyDTO) throws UnsupportedEncodingException {
        companyService.createCompany(companyDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}