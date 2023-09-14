package com.szsc.customermanagement.repository;

import com.szsc.customermanagement.entity.Company;
import com.szsc.customermanagement.exception.CompanyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CompanyRepository {
    Page<Company> findAll(Pageable pageable);

    Page<Company> findByRegisteredLocation(Pageable pageable, String registedLocation);

    Company findById(int companyId) throws CompanyNotFoundException;

    Company findByCode(String companyCode) throws CompanyNotFoundException;


    List<Company> findByType(String companyType);

    List<Company> findByUnifiedSocialCredit(String UnifiedSocialCredit);
    

    void save(Company company);

    void update(Company company) throws CompanyNotFoundException;

    void delete(String companyCode) throws CompanyNotFoundException;

    int count();// count how many company

    boolean companyCodeChecker(String company_code);

    boolean unifiedSocialCreditChecker(String unified_social_credit);

    int CompanyCount(String registeredLocation);
    int convertRegisteredLocation(String registeredLocation);

}
