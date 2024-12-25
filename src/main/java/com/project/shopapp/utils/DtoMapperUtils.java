package com.project.shopapp.utils;

import com.project.shopapp.common.base.PaginationDetails;
import com.project.shopapp.confiuration.exception.BadRequestException;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DtoMapperUtils {

    public <T, V> PaginatedDataResponse makeResponse(Class<T> dtoType, Page<V> values) {
        List<T> responseData = values.stream()
                .map(value -> this.convertToDto(dtoType, value))
                .toList();
        PaginationDetails pagination = new PaginationDetails(values);

        return new PaginatedDataResponse(responseData, pagination);
    }

    public <T, V> PaginatedDataResponse makeResponse(Class<T> dtoType, List<V> values) {
        List<T> responseData = values.stream()
                .map(value -> this.convertToDto(dtoType, value))
                .toList();

        return new PaginatedDataResponse(responseData);
    }

    public  <T, V> PaginatedDataResponse makeResponse(Class<T> dtoType, V value) {
        T responseData = this.convertToDto(dtoType, value);
        return new PaginatedDataResponse(responseData);
    }

    public <T, V> T convertToDto(Class<T> dtoType, V value) {
        try {
            return dtoType.getDeclaredConstructor(value.getClass()).newInstance(value);
        } catch (Exception e) {
            throw new BadRequestException(
                    "Error converting " + value.getClass().getSimpleName() + " to DTO" + e.getMessage()
            );
        }
    }
}
