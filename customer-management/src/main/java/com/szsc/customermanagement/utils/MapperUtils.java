package com.szsc.customermanagement.utils;

import com.szsc.customermanagement.domain.CompanyData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.szsc.customermanagement.entity.Company;

@Mapper
public interface MapperUtils {
    MapperUtils INSTANCE = Mappers.getMapper(MapperUtils.class);

    Company dtoToEntity(CompanyData companyData);

    CompanyData entityToDto(Company company);
}
