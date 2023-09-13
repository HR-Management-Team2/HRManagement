package com.hrmanagement.service;

import com.hrmanagement.dto.request.CreateCompanyRequestDto;
import com.hrmanagement.dto.request.TokenDto;
import com.hrmanagement.dto.request.UpdateCompanyRequestDto;
import com.hrmanagement.dto.response.GetInfoOfCompanyResponseDto;
import com.hrmanagement.exception.CompanyException;
import com.hrmanagement.exception.ErrorType;
import com.hrmanagement.mapper.ISetCompanyMapper;
import com.hrmanagement.repository.ICompanyRepository;
import com.hrmanagement.repository.entity.Company;
import com.hrmanagement.utility.JwtTokenManager;
import com.hrmanagement.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService extends ServiceManager<Company, String> {

    private final ICompanyRepository companyRepository;
    private final ISetCompanyMapper iSetCompanyMapper;
    private final JwtTokenManager jwtTokenService;

    public CompanyService(ICompanyRepository companyRepository, ISetCompanyMapper iSetCompanyMapper,  JwtTokenManager jwtTokenService) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.iSetCompanyMapper = iSetCompanyMapper;
        this.jwtTokenService = jwtTokenService;
    }
    @Transactional
    public Boolean createCompany(CreateCompanyRequestDto dto) {
        Company company = iSetCompanyMapper.toCompany(dto);
        save(company);
        return true;
    }

   /* public List<GetInfoOfCompanyResponseDto> findAllDto(TokenDto dto){
        Optional<Long> id = jwtTokenService.validToken(dto.getToken());
        if(id.isEmpty()){
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        }
        Optional<List<GetInfoOfCompanyResponseDto>> companiesDto = Optional.of( new ArrayList<>());
        for (Company company : findAll()){
            companiesDto.get().add(iSetCompanyMapper.toCompanyResponseDto(company));
        }
        return companiesDto.get();
    }*/

    @Transactional(readOnly = true)
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public Company updateCompany(String taxNumber, UpdateCompanyRequestDto dto){
        Company company = companyRepository.findByTaxNumber(taxNumber)
                .orElseThrow(() -> new CompanyException(ErrorType.COMPANY_NOT_FOUND));
        company.setName(dto.getName());
        company.setTaxNumber(dto.getTaxNumber());
        company.setPhone(dto.getPhone());
        company.setAddress(dto.getAddress());
        company.setEmail(dto.getEmail());
        company.setNumberOfWorkers(dto.getNumberOfWorkers());
        company.setYearOfEstablishment(dto.getYearOfEstablishment());
        return companyRepository.save(company);
    }





}
