package com.hrmanagement.mapper;

import com.hrmanagement.dto.response.GetInfoOfCompanyResponseDto;
import com.hrmanagement.repository.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICompanyMapper {
    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);
    GetInfoOfCompanyResponseDto fromInfoOfCompany(final Company company);
}
