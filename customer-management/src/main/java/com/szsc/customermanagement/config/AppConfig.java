package com.szsc.customermanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public static final String COMPANYID_PREFIX = "110";
    public static final String CODE_LOCATION_CHINA_MAINLAND = "0";
    public static final String CODE_LOCATION_HONG_KONG = "1";
    public static final String CODE_LOCATION_OVERSEAS = "2";
    public static final Integer INDEX_LOCATION_CHINA_MAINLAND = 1;
    public static final Integer INDEX_LOCATION_HONG_KONG = 2;
    public static final Integer INDEX_LOCATION_OVERSEAS = 3;
    public static final String CODE_OPERATION_CREATE = "0";
    public static final String CODE_OPERATION_UPDATE = "1";
    public static final String CODE_OPERATION_DELETE = "2";
    public static final String TYPE_OPERATION_CREATE = "create";
    public static final String TYPE_OPERATION_UPDATE = "update";
    public static final String TYPE_OPERATION_DELETE = "delete";
    public static final String ADMIN_OPERATOR = "admin";
    public static final String TABLE_COMPANY_MAIN = "company_information";
    public static final String FILE_NAME_XLSX_COMPANY = "company_records.xlsx";
    public static final String FILE_NAME_XLSX_RECORDS = "history_records.xlsx";

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
