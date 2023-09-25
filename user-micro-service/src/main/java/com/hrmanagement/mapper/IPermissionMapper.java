package com.hrmanagement.mapper;

import com.hrmanagement.dto.response.PermissionResponseDto;
import com.hrmanagement.repository.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPermissionMapper {
    IPermissionMapper INSTANCE = Mappers.getMapper(IPermissionMapper.class);

    PermissionResponseDto fromPermissionToResponseDto(Permission permission);

}
