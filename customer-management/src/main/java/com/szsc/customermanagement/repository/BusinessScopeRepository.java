package com.szsc.customermanagement.repository;

import com.szsc.customermanagement.entity.BusinessScope;
import com.szsc.customermanagement.exception.BusinessScopeNotFoundException;

import java.util.List;

public interface BusinessScopeRepository {
    List<BusinessScope> findAll();

    BusinessScope findByCompanyId(int companyId) throws BusinessScopeNotFoundException;

    void save(BusinessScope businessScope);

    void update(BusinessScope businessScope) throws BusinessScopeNotFoundException;

    void delete(BusinessScope businessScope) throws BusinessScopeNotFoundException;
}