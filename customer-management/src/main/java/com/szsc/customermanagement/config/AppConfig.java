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

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
