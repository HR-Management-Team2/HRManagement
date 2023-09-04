package com.hrmanagement.mapper;

import com.hrmanagement.dto.request.CreateCompanyRequestDto;
import com.hrmanagement.dto.response.GetInfoOfCompanyResponseDto;
import com.hrmanagement.repository.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class SetCompanyMapper implements ISetCompanyMapper{

    @Override
    public Company toCompany(CreateCompanyRequestDto dto){
        Company company = new Company();
        company.setName(dto.getName());
        company.setTaxNumber(dto.getTaxNumber());
        company.setAddress(dto.getAddress());
        company.setEmail(dto.getEmail());
        company.setPhone(dto.getPhone());
        company.setNumberOfWorkers(dto.getNumberOfWorkers());
        company.setYearOfEstablishment(dto.getYearOfEstablishment());
        return company;
    }

    @Override
    public GetInfoOfCompanyResponseDto toCompanyResponseDto(Company company) {
        GetInfoOfCompanyResponseDto getInfoOfCompanyResponseDto = new GetInfoOfCompanyResponseDto();
        getInfoOfCompanyResponseDto.setName(company.getName());
        getInfoOfCompanyResponseDto.setTaxNumber(company.getTaxNumber());
        getInfoOfCompanyResponseDto.setAddress(company.getAddress());
        getInfoOfCompanyResponseDto.setEmail(company.getEmail());
        getInfoOfCompanyResponseDto.setPhone(company.getPhone());
        getInfoOfCompanyResponseDto.setNumberOfWorkers(company.getNumberOfWorkers());
        getInfoOfCompanyResponseDto.setYearOfEstablishment(company.getYearOfEstablishment());
        return getInfoOfCompanyResponseDto;
    }
}
