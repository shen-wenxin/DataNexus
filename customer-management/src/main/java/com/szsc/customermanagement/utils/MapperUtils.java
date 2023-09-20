package com.szsc.customermanagement.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.szsc.customermanagement.dto.CompanyDTO;
import com.szsc.customermanagement.entity.Company;

@Mapper
public interface MapperUtils {
    MapperUtils INSTANCE = Mappers.getMapper(MapperUtils.class);

    Company dtoToEntity(CompanyDTO companyDTO);

    CompanyDTO entityToDto(Company company);
}
