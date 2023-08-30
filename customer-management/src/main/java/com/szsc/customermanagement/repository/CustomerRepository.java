package com.szsc.customermanagement.repository;

import com.szsc.customermanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //是数据访问层接口，负责与数据库进行交互。
}
