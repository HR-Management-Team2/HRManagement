package com.hrmanagement.mapper;

import com.hrmanagement.dto.request.CreateCompanyRequestDto;
import com.hrmanagement.dto.response.GetInfoOfCompanyResponseDto;
import com.hrmanagement.repository.entity.Company;

public interface ISetCompanyMapper {
    Company toCompany(CreateCompanyRequestDto dto);
    GetInfoOfCompanyResponseDto toCompanyResponseDto(Company company);
}
