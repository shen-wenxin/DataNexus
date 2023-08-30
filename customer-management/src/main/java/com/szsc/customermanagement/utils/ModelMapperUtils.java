package com.szsc.customermanagement.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtils {
    private static ModelMapper modelMapper;

    @Autowired
    public ModelMapperUtils(ModelMapper modelMapper) {
        ModelMapperUtils.modelMapper = modelMapper;
    }

    public static <T, D> D convertToDTO(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <D, T> T convertToEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
    
}
