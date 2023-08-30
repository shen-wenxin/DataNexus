package com.szsc.customermanagement.service;

import com.szsc.customermanagement.dto.CustomerDto;
import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    void deleteCustomer(Long id);

    // 其他业务方法...
    
}
