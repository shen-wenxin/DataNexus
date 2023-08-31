package com.szsc.customermanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public static final String COMPANYID_PREFIX = "110"; 

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
